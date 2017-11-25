/**
 * 
 */
package com.carport.service;

import java.util.List;
import java.util.Map;

import com.carport.bean.Apartment;
import com.github.pagehelper.PageInfo;

/**
 * ���������Ź���
 * 
 * @author zhangyijie
 * @created 2016��12��2�� ����8:58:35
 * @since
 */
public interface ApartmentService {

	/**
	 * ��������ѯ���в�����Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����11:12:40
	 * @since
	 * @return
	 */
	public List<Apartment> queryAllApartment();

	/**
	 * ��������ѯ״̬Ϊ1�Ĳ���
	 * @author xiongdun
	 * @created 2016��12��15�� ����5:00:38
	 * @since 
	 * @return
	 */
	public List<Apartment> queryApartmentStatus1();
	/**
	 * ��������ȡ�������ŵĳ�λ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����11:19:27
	 * @since
	 * @return
	 */
	public Map<String, Integer[]> getAllApartmentCarport();

	/**
	 * ��������ѯָ���������еĳ�λ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����12:27:39
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public String[] getApartmentCarportByApNo(String apartment_no);

	/**
	 * ���������ű�Ų�ѯ������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����1:31:14
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public Apartment getApartmentInfoByApNo(String apartment_no);

	/**
	 * ��������Id��ѯ������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����1:34:45
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public Apartment getApartmentInfoById(String apartment_id);

	/**
	 * ������id��ѯ��λ���
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����12:58:30
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public String[] getApartmentCarportByApId(String apartment_id);

	/**
	 * ��������Ӳ�����Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����3:56:29
	 * @since
	 * @param apartment
	 * @return
	 */
	public boolean addApartmentInfo(Apartment apartment);

	/**
	 * �������޸Ĳ�����Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����3:57:26
	 * @since
	 * @param apartment
	 * @return
	 */
	public boolean modifyApartmentInfoByApId(Apartment apartment);

	/**
	 * ��������apartment_id�޸Ĳ���״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:14:31
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public boolean modfiyApartmentStatusTo1ByApId(String apartment_id);

	/**
	 * ��������apartment_id�޸Ĳ���״̬Ϊ0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:14:53
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public boolean modfiyApartmentStatusTo0ByApId(String apartment_id);

	/**
	 * ��������apartment_id ɾ��������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:00:14
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public boolean deleteApartmentInfoByApId(String apartment_id);
	
	/**
	 * ��������ҳ��ѯ������Ϣ
	 * @author zhangyijia
	 * @created 2016��12��19�� ����4:00:57
	 * @since 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<Apartment> queryApartmentByPage(Integer pageNo, Integer pageSize);

}
