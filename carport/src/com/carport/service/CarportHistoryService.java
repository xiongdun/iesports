/**
 * 
 */
package com.carport.service;

import java.util.List;

import com.carport.bean.CarportHistory;
import com.carport.model.CarportHistoryModel;
import com.github.pagehelper.PageInfo;

/**
 * 描述：抢车位功能管理
 * 
 * @author zhangyijie
 * @created 2016年12月2日 上午8:58:35
 * @since
 */
public interface CarportHistoryService {

	/**
	 * 描述：查询所有抢车位记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午1:41:36
	 * @since
	 * @return
	 */
	public List<CarportHistory> queryAllCarportHistory();

	/**
	 * 描述：按user_id查询抢车位历史记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:50:40
	 * @since
	 * @param user_id
	 * @return
	 */
	public List<CarportHistory> queryCarportHistoryByUserId(String user_id);

	/**
	 * 描述：按组织机构查询抢车位历史纪录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:51:49
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<CarportHistory> queryCarportHistoryByOrgId(String org_id);

	/**
	 * 描述：按时间查询抢车位历史记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:51:53
	 * @since
	 * @param date
	 * @return
	 */
	public List<CarportHistory> queryCarportHistoryByDate(String datetime);

	/**
	 * 描述：按id查询抢车位历史纪录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:51:58
	 * @since
	 * @param carportHistory_id
	 * @return
	 */
	public CarportHistory queryCarportHistoryByChId(String carportHistory_id);

	/**
	 * 描述：新增抢车位记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:52:03
	 * @since
	 * @param carportHistory
	 * @return
	 */
	public boolean addCarportHistoryInfo(CarportHistory carportHistory);

	/**
	 * 描述：批量新增抢车位记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午5:23:29
	 * @since
	 * @param carportHistories
	 * @return
	 */
	public List<Boolean> batchAddCarportHistoryInfo(
			List<CarportHistory> carportHistories);

	/**
	 * 描述：按user_id 和时间查询抢车位记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:52:07
	 * @since
	 * @param ch
	 * @return
	 */
	public CarportHistory queryCarportHistoryByUserIdAndDate(
			CarportHistory carportHistory);

	/**
	 * 描述：按cphis_id 删除
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:35:04
	 * @since
	 * @param cphis_id
	 * @return
	 */
	public boolean deleteCarportHistoryByCphisId(String cphis_id);
	
	/**
	 * 描述：分页查询抢车位历史
	 * @author xiongdun
	 * @created 2016年12月19日 下午4:25:59
	 * @since 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<CarportHistoryModel> queryCarportHisByPage(Integer pageNo, Integer pageSize);
}
