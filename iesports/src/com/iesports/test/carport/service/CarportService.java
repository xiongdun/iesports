/**
 * 
 */
package com.iesports.test.carport.service;

import java.util.List;

import com.iesports.test.carport.bean.Carport;

/**
 * ��������λ����
 * 
 * @author zhangyijie
 * @created 2016��12��2�� ����8:58:35
 * @since
 */
public interface CarportService {

	/**
	 * ��������ѯ���г�λ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:45:25
	 * @since
	 * @return
	 */
	public List<Carport> queryAllCarport();

	/**
	 * ������������Id��ѯ��λ��Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:51:03
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<Carport> getCarportByApartmentId(String apartment_id);

	/**
	 * ����������֯������ѯ��λ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:51:20
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Carport> getCarportByOrgId(String org_id);

	/**
	 * ����������֯������statusΪ1��ѯ��λ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:51:49
	 * @since
	 * @param carport
	 * @return
	 */
	public List<Carport> getCarportByOrgIdAndStatus1(Carport carport);

	/**
	 * ����������λ�Ų�ѯ��λ��Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����7:27:14
	 * @since
	 * @param carport_no
	 * @return
	 */
	public List<Carport> getCarportByCarportNo(String carport_no);

	/**
	 * �������������복λ���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����4:30:18
	 * @since
	 * @param carports
	 * @return
	 */
	public List<Boolean> batchInsertCarports(List<Carport> carports);

	/**
	 * ����������֯�����ź͵���ʱ���ѯ��λ��Ϣ
	 * @param carport
	 * @return
	 */
	public List<Carport> getCarportByNowDate(Carport carport);
	/**
	 * �������޸ĳ�λ״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:52:03
	 * @since
	 * @param carport_id
	 * @return
	 */
	public boolean modifyCarportStatus1ByCarportId(String carport_id);

	/**
	 * �������޸ĳ�λ״̬Ϊ0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:52:16
	 * @since
	 * @param carport_id
	 * @return
	 */
	public boolean modifyCarportStatus0ByCarportId(String carport_id);

	/**
	 * ���������복λ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����3:09:22
	 * @since
	 * @param carport
	 * @return
	 */
	public boolean addCarportInfo(Carport carport);

	/**
	 * ��������carport_idɾ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:45:00
	 * @since
	 * @param carport_id
	 * @return
	 */
	public boolean deleteCarportByCarportId(String carport_id);
}
