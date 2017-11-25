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
 * 描述：初始化车位表
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午9:07:01
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
	 * 初始化车位编号
	 */
	public InitCarport() {
		
		// 此处注释是用于部门以后做拓展使用--先保留
		// aparmentCarportMap = apService.getAllApartmentCarport();
		// apartments = apService.queryAllApartment();
		implService.getApartmentCarportByApNo(apartment_no);
		carport_NumArr = apService.getApartmentCarportByApNo(apartment_no);
		String carportStr = "";
		for (int i = 0; i < carport_NumArr.length; i++) {
			carportStr += carport_NumArr[i] + ":";
		}
		logger.info("车位初始化成功!" + carportStr + "共" + carport_NumArr.length
				+ "车位！");
	}

	/**
	 * 描述：初始化车位表
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 下午4:16:33
	 * @since
	 */
	public void initCarportData() {

		try {
			// 获取组织机构信息
			List<Organization> organizations = getOrganizations();

			Apartment apartment = apService.getApartmentInfoByApNo(apartment_no);
			String apartment_id = apartment.getApartment_id();
			// 实际总车位数
			int carportSum = carport_NumArr.length;

			// 各个部门分别所需的车位数
			List<OrgCarportResult> orgCarportResults = countCarportNumsForOrg(
					organizations, carportSum, apartment_id);

			// 总需车位数
			int needCarportSum = countNeedCarportSum(orgCarportResults);

			// 处理车位数异常
			orgCarportResults = comparerOrgCarportResults(orgCarportResults,
					carportSum, needCarportSum);

			// 初始化表
			initTable(orgCarportResults, organizations);

		} catch (Exception e) {
			logger.error("处理车位异常，异常原因：", e);
		} finally {
			// 清空车位号缓存
			carport_NumArr = null;
		}
	}

	/**
	 * 描述：初始化车位表
	 * 
	 * @author xiongdun
	 * @created 2016年12月6日 上午11:41:07
	 * @since
	 * @param orgCarportResults
	 *            计算结果
	 * @param organizations
	 *            组织机构信息
	 */
	private void initTable(List<OrgCarportResult> orgCarportResults,
			List<Organization> organizations) {

		List<Carport> carportList = new ArrayList<Carport>();
		// 遍历集合
		int index = 0;
		for (int i = 0; i < organizations.size(); i++) {
			Organization organization = organizations.get(i);
			int num = orgCarportResults.get(i).getNeed_carportNum();
			for (int j = 0; j < num; j++) {
				Carport carport = new Carport();
				String carport_no = carport_NumArr[index++];// 获取数组中车位编号
				carport.setCarport_no(carport_no);
				carport.setOrg_id(organization.getOrg_id());
				carport.setStatus("1");
				carport.setDatetime(DateUtils.getDatetime());
				carport.setApartment_id("13");
				carportList.add(carport);
			}
		}

		// 批量插入车位表
		if (carportList != null && carportList.size() > 0) {
			carportService.batchInsertCarports(carportList);
		}

	}

	/**
	 * 描述：处理计算结果出现多于或少于实际车位数值的情况
	 * 
	 * @author zhangyijie
	 * @created 2016年12月6日 上午11:02:06
	 * @since
	 * @param orgCarportResults
	 *            待处理结果
	 * @param carportSum
	 *            实际有的车位数
	 * @param needCarportSum
	 *            计算后的所需车位数
	 * @return 计算车位最后正确结果
	 */
	private List<OrgCarportResult> comparerOrgCarportResults(List<OrgCarportResult> orgCarportResults, int carportSum, int needCarportSum) {

		int index = getMaxOrgCarportNumIndex(orgCarportResults);

		int num = carportSum - needCarportSum;
		// num 大于零 说明实际车位多则在所需车位上加
		// num 小于零，则相反
		// 所以此处不需要所判断
		// 实际车位比所需车位多 则在最大车位数上加否则减
		orgCarportResults.get(index).setNeed_carportNum(orgCarportResults.get(index).getNeed_carportNum() + (num));

		return orgCarportResults;
	}

	/**
	 * 描述：获取集合中部门所需车位最大数的集合下标
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 下午6:23:33
	 * @since
	 * @param orgCarportResults
	 * @return 最大车位数下标
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
	 * 描述：获取组织机构
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:41:15
	 * @since
	 * @return 组织机构信息集合
	 */
	private List<Organization> getOrganizations() {
		// 进行数据库查询组织机构信息
		List<Organization> organizations = orgService.getOrganizationByApartmentNo(apartment_no);
		return organizations;
	}

	/**
	 * 描述：计算各个部门所需的车位数
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 下午6:50:21
	 * @since
	 * @param organizations
	 *            组织机构信息集合
	 * @param carportSum
	 *            实际车位数
	 * @param apartment_id 部门Id
	 * @return 计算结果集合
	 */
	private List<OrgCarportResult> countCarportNumsForOrg(
			List<Organization> organizations, int carportSum, String apartment_id) {

		List<OrgCarportResult> orgCarportResults = new ArrayList<OrgCarportResult>();
		// 然后计算总人数
		double totalNum = userService.getUserInfoByApId(apartment_id).size();
		for (Organization organization : organizations) {
			// 先查询各个部门id 然后再根据部门Id
			String org_id = organization.getOrg_id();
			// 查询各个部门下的员工数
			int userNum = userService.getUserInfoByOrgId(org_id).size();
			// 计算各部门人数和总人数的比例
			double ratio = userNum / totalNum;
			// 计算比例之后 进行四舍五入运算 * 总车位数
			int needNum = this.doubleToInt(ratio, carportSum);
			// 将值存在计算结果对象中
			OrgCarportResult orgCarportResult = new OrgCarportResult();
			orgCarportResult.setOrg_id(organization.getOrg_id());
			orgCarportResult.setOrg_name(organization.getOrg_name());
			orgCarportResult.setStatus(organization.getStatus());
			orgCarportResult.setApartment_id(organization.getApartment_id());
			orgCarportResult.setNeed_carportNum(needNum);
			orgCarportResult.setCount_ratio(ratio);

			// 存入list返回
			orgCarportResults.add(orgCarportResult);
		}
		// 如需存库可以在此处进行批量存库TODO

		return orgCarportResults;
	}

	/**
	 * 描述：计算部门比例数值总和
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 下午5:07:41
	 * @since
	 * @param 计算后的车位信息值
	 * @return 部门所需车位总和
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
	 * 描述：四舍五入计算该机构需要的车位数
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 下午5:02:21
	 * @since
	 * @param ratio
	 *            计算结果
	 * @param carportSum
	 *            实际的车位数
	 * @return 四舍五入计算后的结果
	 */
	private int doubleToInt(double ratio, int carportSum) {
		// 四舍五入
		DecimalFormat df = new DecimalFormat("#");
		String trans = df.format(ratio * carportSum);
		int result = 0;
		if (!trans.isEmpty()) {
			result = Integer.parseInt(trans);
		}
		// 最少都需要 1 个车位
		if (result <= 0) {
			result = 1;
		}
		return result;
	}

}
