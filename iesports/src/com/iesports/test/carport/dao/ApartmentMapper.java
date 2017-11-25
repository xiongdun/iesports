/**
 * 
 */
package com.iesports.test.carport.dao;

import java.util.List;

import com.iesports.test.carport.bean.Apartment;

/**
 * ����������λ��ʷӳ��
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����5:47:09
 * @since
 */
public interface ApartmentMapper {

	/**
	 * ���������벿��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����1:45:16
	 * @since
	 * @param apartment
	 * @return
	 */
	public int insertApartment(Apartment apartment);

	/**
	 * ��������ѯ���в���
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����1:45:25
	 * @since
	 * @return
	 */
	public List<Apartment> selectAllApartment();

	/**
	 * ��������Id��ѯ����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����1:45:35
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public Apartment selectApartmentById(String apartment_id);

	/**
	 * �����������ű�Ų�ѯ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����12:30:31
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public List<Apartment> selectApartmentByApNo(String apartment_no);

	/**
	 * �������޸Ĳ�����Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:06:52
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public int updateApartmentInfoByApId(Apartment apartment);

	/**
	 * ��������ID�޸Ĳ���״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����1:45:47
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public int updateApartmentStatusTo1ById(String apartment_id);

	/**
	 * ��������ID�޸Ĳ���״̬Ϊ0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����1:52:50
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public int updateApartmentStatusTo0ById(String apartment_id);

	/**
	 * ��������apartment_id ɾ��������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:02:07
	 * @since
	 * @param aparment_id
	 * @return
	 */
	public int deleteApartmentInfoByApId(String apartment_id);
}
