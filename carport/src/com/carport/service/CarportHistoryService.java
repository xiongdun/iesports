/**
 * 
 */
package com.carport.service;

import java.util.List;

import com.carport.bean.CarportHistory;
import com.carport.model.CarportHistoryModel;
import com.github.pagehelper.PageInfo;

/**
 * ����������λ���ܹ���
 * 
 * @author zhangyijie
 * @created 2016��12��2�� ����8:58:35
 * @since
 */
public interface CarportHistoryService {

	/**
	 * ��������ѯ��������λ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����1:41:36
	 * @since
	 * @return
	 */
	public List<CarportHistory> queryAllCarportHistory();

	/**
	 * ��������user_id��ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:50:40
	 * @since
	 * @param user_id
	 * @return
	 */
	public List<CarportHistory> queryCarportHistoryByUserId(String user_id);

	/**
	 * ����������֯������ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:51:49
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<CarportHistory> queryCarportHistoryByOrgId(String org_id);

	/**
	 * ��������ʱ���ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:51:53
	 * @since
	 * @param date
	 * @return
	 */
	public List<CarportHistory> queryCarportHistoryByDate(String datetime);

	/**
	 * ��������id��ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:51:58
	 * @since
	 * @param carportHistory_id
	 * @return
	 */
	public CarportHistory queryCarportHistoryByChId(String carportHistory_id);

	/**
	 * ��������������λ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:52:03
	 * @since
	 * @param carportHistory
	 * @return
	 */
	public boolean addCarportHistoryInfo(CarportHistory carportHistory);

	/**
	 * ������������������λ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����5:23:29
	 * @since
	 * @param carportHistories
	 * @return
	 */
	public List<Boolean> batchAddCarportHistoryInfo(
			List<CarportHistory> carportHistories);

	/**
	 * ��������user_id ��ʱ���ѯ����λ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����4:52:07
	 * @since
	 * @param ch
	 * @return
	 */
	public CarportHistory queryCarportHistoryByUserIdAndDate(
			CarportHistory carportHistory);

	/**
	 * ��������cphis_id ɾ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:35:04
	 * @since
	 * @param cphis_id
	 * @return
	 */
	public boolean deleteCarportHistoryByCphisId(String cphis_id);
	
	/**
	 * ��������ҳ��ѯ����λ��ʷ
	 * @author xiongdun
	 * @created 2016��12��19�� ����4:25:59
	 * @since 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<CarportHistoryModel> queryCarportHisByPage(Integer pageNo, Integer pageSize);
}
