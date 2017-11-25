/**
 * 
 */
package com.iesports.test.carport.dao;

import java.util.List;

import com.iesports.test.carport.bean.Carport;

/**
 * ��������λӳ��
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����5:47:09
 * @since
 */
public interface CarportMapper {

	/**
	 * ���������복λ������
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:25:54
	 * @since
	 * @param carport
	 * @return
	 */
	public int insertCarport(Carport carport);

	/**
	 * ��������ѯ���еĳ�λ��Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:29:02
	 * @since
	 * @return
	 */
	public List<Carport> selectAllCarport();

	/**
	 * ����������λ�Ų�ѯ��λ��Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:30:12
	 * @since
	 * @param carport_no
	 * @return
	 */
	public List<Carport> selectCarportByCarportNo(String carport_no);

	/**
	 * ����������֯�����Ų�ѯ��λ��Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:44:19
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Carport> selectCarportByOrgId(String org_id);

	/**
	 * ����������֯�����ź͵���ʱ���ѯ��λ��Ϣ
	 * @param carport
	 * @return
	 */
	public List<Carport> selectCarportByNowDate(Carport carport);
	/**
	 * ����������֯������statusΪ1��ѯ��λ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����2:55:39
	 * @since
	 * @param carport
	 * @return
	 */
	public List<Carport> selectCarportByOrgIdAndStatus1(Carport carport);

	/**
	 * ������������Id��ѯ��λ��Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:44:24
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<Carport> selectCarportByApartmentId(String apartment_id);

	/**
	 * �������޸ĳ�λ״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:28:54
	 * @since
	 * @param carport_no
	 * @return
	 */
	public int updateCarportStatusTo1(String carport_id);

	/**
	 * �������޸ĳ�λ״̬Ϊ0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:47:21
	 * @since
	 * @param carport_no
	 * @return
	 */
	public int updateCarportStatusTo0(String carport_id);

	/**
	 * ��������carport_idɾ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:46:07
	 * @since
	 * @param carport_id
	 * @return
	 */
	public int deleteCarportByCarportId(String carport_id);
}
