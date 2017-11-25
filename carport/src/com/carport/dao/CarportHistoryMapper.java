/**
 * 
 */
package com.carport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carport.bean.CarportHistory;
import com.carport.model.CarportHistoryModel;

/**
 * 描述：抢车位历史映射
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午5:.47:09
 * @since
 */
public interface CarportHistoryMapper {

	/**
	 * 描述：插入抢车位历史记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:17:15
	 * @since
	 * @param ch
	 * @return
	 */
	public int insertCarportHistory(CarportHistory ch);

	/**
	 * 
	 * 描述：查询所有抢车位历史记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:16:46
	 * @since
	 * @return
	 */
	public List<CarportHistory> selectAllCarportHistory();

	/**
	 * 描述：按时间查询抢车位历史纪录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:18:37
	 * @since
	 * @param date
	 * @return 出现there is no getter 的问题是因为datetime这个单词的特殊性，
	 *         所以在这里加入@Param("datetime")，用于解决这个问题
	 */
	public List<CarportHistory> selectCarportHistoryByDate(
			@Param("datetime") String datetime);

	/**
	 * 描述：按组织机构id查询抢车位历史记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:19:26
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<CarportHistory> selectCaportHistoryByOrgId(
			@Param("org_id") String org_id);

	/**
	 * 描述：按user_id查询抢车位历史纪录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:26:19
	 * @since
	 * @param user_id
	 * @return
	 */
	public List<CarportHistory> selectCarportHistoryByUserId(@Param("user_id") String user_id);

	/**
	 * 描述：按ID查询抢车位历史记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:16:30
	 * @since
	 * @param id
	 * @return
	 */
	public CarportHistory selectCarportHistoryById(@Param("cphis_id") String cphis_id);

	/**
	 * 描述：按用户Id和时间查询抢车位历史纪录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:32:16
	 * @since
	 * @param ch
	 * @return
	 */
	public List<CarportHistory> selectCarportHistoryByUserIdAndDate(
			CarportHistory ch);

	/**
	 * 描述：按cphis_id 删除抢车位历史纪录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:31:09
	 * @since
	 * @param cphis_id
	 * @return
	 */
	public int deleteCarportHistoryByCphisId(@Param("cphis_id") String cphis_id);
	
	/**
	 * 描述：分页查询车位历史记录
	 * @author xiongdun
	 * @created 2016年12月20日 上午10:16:47
	 * @since 
	 * @return
	 */
	public List<CarportHistoryModel> selectCarportHisByPage();

}
