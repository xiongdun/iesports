/**
 * 
 */
package com.carport.service.dispatcher;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.carport.bean.Apartment;
import com.carport.bean.Carport;
import com.carport.bean.Organization;
import com.carport.model.OrgCarportResult;
import com.carport.service.ApartmentService;
import com.carport.service.CarportService;
import com.carport.service.OrganizationService;
import com.carport.service.UserService;
import com.carport.service.impl.ApartmentServiceImpl;
import com.carport.util.Configuration;
import com.carport.util.DateUtils;

/**
 * ��������ʼ����λ��
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����9:07:01
 * @since
 */
public class InitCarport {

	private static Logger logger = Logger.getLogger(InitCarport.class);

	private String[] carport_NumArr = null;

	
	private ApartmentServiceImpl implService = new ApartmentServiceImpl();
	
	@Autowired
	private ApartmentService apService;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private CarportService carportService;
	@Autowired
	private UserService userService;

	private String apartment_no = Configuration.getString("start.init.apartment.number");

	// private static Map<String, Integer[]> aparmentCarportMap = new
	// HashMap<String, Integer[]>();

	// private static List<Apartment> apartments = new ArrayList<Apartment>();

	/**
	 * ��ʼ����λ���
	 */
	public InitCarport() {
		
		// �˴�ע�������ڲ����Ժ�����չʹ��--�ȱ���
		// aparmentCarportMap = apService.getAllApartmentCarport();
		// apartments = apService.queryAllApartment();
		implService.getApartmentCarportByApNo(apartment_no);
		carport_NumArr = apService.getApartmentCarportByApNo(apartment_no);
		String carportStr = "";
		for (int i = 0; i < carport_NumArr.length; i++) {
			carportStr += carport_NumArr[i] + ":";
		}
		logger.info("��λ��ʼ���ɹ�!" + carportStr + "��" + carport_NumArr.length
				+ "��λ��");
	}

	/**
	 * ��������ʼ����λ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����4:16:33
	 * @since
	 */
	public void initCarportData() {

		try {
			// ��ȡ��֯������Ϣ
			List<Organization> organizations = getOrganizations();

			Apartment apartment = apService.getApartmentInfoByApNo(apartment_no);
			String apartment_id = apartment.getApartment_id();
			// ʵ���ܳ�λ��
			int carportSum = carport_NumArr.length;

			// �������ŷֱ�����ĳ�λ��
			List<OrgCarportResult> orgCarportResults = countCarportNumsForOrg(
					organizations, carportSum, apartment_id);

			// ���賵λ��
			int needCarportSum = countNeedCarportSum(orgCarportResults);

			// ����λ���쳣
			orgCarportResults = comparerOrgCarportResults(orgCarportResults,
					carportSum, needCarportSum);

			// ��ʼ����
			initTable(orgCarportResults, organizations);

		} catch (Exception e) {
			logger.error("����λ�쳣���쳣ԭ��", e);
		} finally {
			// ��ճ�λ�Ż���
			carport_NumArr = null;
		}
	}

	/**
	 * ��������ʼ����λ��
	 * 
	 * @author xiongdun
	 * @created 2016��12��6�� ����11:41:07
	 * @since
	 * @param orgCarportResults
	 *            ������
	 * @param organizations
	 *            ��֯������Ϣ
	 */
	private void initTable(List<OrgCarportResult> orgCarportResults,
			List<Organization> organizations) {

		List<Carport> carportList = new ArrayList<Carport>();
		// ��������
		int index = 0;
		for (int i = 0; i < organizations.size(); i++) {
			Organization organization = organizations.get(i);
			int num = orgCarportResults.get(i).getNeed_carportNum();
			for (int j = 0; j < num; j++) {
				Carport carport = new Carport();
				String carport_no = carport_NumArr[index++];// ��ȡ�����г�λ���
				carport.setCarport_no(carport_no);
				carport.setOrg_id(organization.getOrg_id());
				carport.setStatus("1");
				carport.setDatetime(DateUtils.getDatetime());
				carport.setApartment_id("13");
				carportList.add(carport);
			}
		}

		// �������복λ��
		if (carportList != null && carportList.size() > 0) {
			carportService.batchInsertCarports(carportList);
		}

	}

	/**
	 * ������������������ֶ��ڻ�����ʵ�ʳ�λ��ֵ�����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��6�� ����11:02:06
	 * @since
	 * @param orgCarportResults
	 *            ��������
	 * @param carportSum
	 *            ʵ���еĳ�λ��
	 * @param needCarportSum
	 *            ���������賵λ��
	 * @return ���㳵λ�����ȷ���
	 */
	private List<OrgCarportResult> comparerOrgCarportResults(List<OrgCarportResult> orgCarportResults, int carportSum, int needCarportSum) {

		int index = getMaxOrgCarportNumIndex(orgCarportResults);

		int num = carportSum - needCarportSum;
		// num ������ ˵��ʵ�ʳ�λ���������賵λ�ϼ�
		// num С���㣬���෴
		// ���Դ˴�����Ҫ���ж�
		// ʵ�ʳ�λ�����賵λ�� �������λ���ϼӷ����
		orgCarportResults.get(index).setNeed_carportNum(orgCarportResults.get(index).getNeed_carportNum() + (num));

		return orgCarportResults;
	}

	/**
	 * ��������ȡ�����в������賵λ������ļ����±�
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����6:23:33
	 * @since
	 * @param orgCarportResults
	 * @return ���λ���±�
	 */
	private int getMaxOrgCarportNumIndex(
			List<OrgCarportResult> orgCarportResults) {

		int maxNum = orgCarportResults.get(0).getNeed_carportNum();
		int index = 0;
		for (int i = 0; i < orgCarportResults.size(); i++) {
			OrgCarportResult orgCarportResult = orgCarportResults.get(i);
			int org_needNum = orgCarportResult.getNeed_carportNum();
			if (org_needNum > maxNum) {
				maxNum = org_needNum;
				index = i;
			}
		}
		return index;
	}

	/**
	 * ��������ȡ��֯����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:41:15
	 * @since
	 * @return ��֯������Ϣ����
	 */
	private List<Organization> getOrganizations() {
		// �������ݿ��ѯ��֯������Ϣ
		List<Organization> organizations = orgService.getOrganizationByApartmentNo(apartment_no);
		return organizations;
	}

	/**
	 * ���������������������ĳ�λ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����6:50:21
	 * @since
	 * @param organizations
	 *            ��֯������Ϣ����
	 * @param carportSum
	 *            ʵ�ʳ�λ��
	 * @param apartment_id ����Id
	 * @return ����������
	 */
	private List<OrgCarportResult> countCarportNumsForOrg(
			List<Organization> organizations, int carportSum, String apartment_id) {

		List<OrgCarportResult> orgCarportResults = new ArrayList<OrgCarportResult>();
		// Ȼ�����������
		double totalNum = userService.getUserInfoByApId(apartment_id).size();
		for (Organization organization : organizations) {
			// �Ȳ�ѯ��������id Ȼ���ٸ��ݲ���Id
			String org_id = organization.getOrg_id();
			// ��ѯ���������µ�Ա����
			int userNum = userService.getUserInfoByOrgId(org_id).size();
			// ����������������������ı���
			double ratio = userNum / totalNum;
			// �������֮�� ���������������� * �ܳ�λ��
			int needNum = this.doubleToInt(ratio, carportSum);
			// ��ֵ���ڼ�����������
			OrgCarportResult orgCarportResult = new OrgCarportResult();
			orgCarportResult.setOrg_id(organization.getOrg_id());
			orgCarportResult.setOrg_name(organization.getOrg_name());
			orgCarportResult.setStatus(organization.getStatus());
			orgCarportResult.setApartment_id(organization.getApartment_id());
			orgCarportResult.setNeed_carportNum(needNum);
			orgCarportResult.setCount_ratio(ratio);

			// ����list����
			orgCarportResults.add(orgCarportResult);
		}
		// ����������ڴ˴������������TODO

		return orgCarportResults;
	}

	/**
	 * ���������㲿�ű�����ֵ�ܺ�
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����5:07:41
	 * @since
	 * @param �����ĳ�λ��Ϣֵ
	 * @return �������賵λ�ܺ�
	 */
	private int countNeedCarportSum(List<OrgCarportResult> orgCarportResults) {
		int sum = 0;
		for (OrgCarportResult orgCarportResult : orgCarportResults) {
			int org_needNum = orgCarportResult.getNeed_carportNum();
			sum += org_needNum;
		}
		return sum;
	}

	/**
	 * �����������������û�����Ҫ�ĳ�λ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����5:02:21
	 * @since
	 * @param ratio
	 *            ������
	 * @param carportSum
	 *            ʵ�ʵĳ�λ��
	 * @return ������������Ľ��
	 */
	private int doubleToInt(double ratio, int carportSum) {
		// ��������
		DecimalFormat df = new DecimalFormat("#");
		String trans = df.format(ratio * carportSum);
		int result = 0;
		if (!trans.isEmpty()) {
			result = Integer.parseInt(trans);
		}
		// ���ٶ���Ҫ 1 ����λ
		if (result <= 0) {
			result = 1;
		}
		return result;
	}

}
