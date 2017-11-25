/**
 * 
 */
package com.carport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carport.bean.CarportHistory;
import com.carport.model.CarportHistoryModel;

/**
 * ����������λ��ʷӳ��
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����5:.47:09
 * @since
 */
public interface CarportHistoryMapper {

	/**
	 * ��������������λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:17:15
	 * @since
	 * @param ch
	 * @return
	 */
	public int insertCarportHistory(CarportHistory ch);

	/**
	 * 
	 * ��������ѯ��������λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:16:46
	 * @since
	 * @return
	 */
	public List<CarportHistory> selectAllCarportHistory();

	/**
	 * ��������ʱ���ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:18:37
	 * @since
	 * @param date
	 * @return ����there is no getter ����������Ϊdatetime������ʵ������ԣ�
	 *         �������������@Param("datetime")�����ڽ���������
	 */
	public List<CarportHistory> selectCarportHistoryByDate(
			@Param("datetime") String datetime);

	/**
	 * ����������֯����id��ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:19:26
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<CarportHistory> selectCaportHistoryByOrgId(
			@Param("org_id") String org_id);

	/**
	 * ��������user_id��ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:26:19
	 * @since
	 * @param user_id
	 * @return
	 */
	public List<CarportHistory> selectCarportHistoryByUserId(@Param("user_id") String user_id);

	/**
	 * ��������ID��ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:16:30
	 * @since
	 * @param id
	 * @return
	 */
	public CarportHistory selectCarportHistoryById(@Param("cphis_id") String cphis_id);

	/**
	 * ���������û�Id��ʱ���ѯ����λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����7:32:16
	 * @since
	 * @param ch
	 * @return
	 */
	public List<CarportHistory> selectCarportHistoryByUserIdAndDate(
			CarportHistory ch);

	/**
	 * ��������cphis_id ɾ������λ��ʷ��¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:31:09
	 * @since
	 * @param cphis_id
	 * @return
	 */
	public int deleteCarportHistoryByCphisId(@Param("cphis_id") String cphis_id);
	
	/**
	 * ��������ҳ��ѯ��λ��ʷ��¼
	 * @author xiongdun
	 * @created 2016��12��20�� ����10:16:47
	 * @since 
	 * @return
	 */
	public List<CarportHistoryModel> selectCarportHisByPage();

}
