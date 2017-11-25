/**
 * 
 */
package com.iesports.test.carport.service;

import java.util.List;

import com.iesports.test.carport.bean.Carport;

/**
 * 描述：车位管理
 * 
 * @author zhangyijie
 * @created 2016年12月2日 上午8:58:35
 * @since
 */
public interface CarportService {

	/**
	 * 描述：查询所有车位
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:45:25
	 * @since
	 * @return
	 */
	public List<Carport> queryAllCarport();

	/**
	 * 描述：按部门Id查询车位信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:51:03
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<Carport> getCarportByApartmentId(String apartment_id);

	/**
	 * 描述：按组织机构查询车位
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:51:20
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Carport> getCarportByOrgId(String org_id);

	/**
	 * 描述：按组织机构和status为1查询车位
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:51:49
	 * @since
	 * @param carport
	 * @return
	 */
	public List<Carport> getCarportByOrgIdAndStatus1(Carport carport);

	/**
	 * 描述：按车位号查询车位信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午7:27:14
	 * @since
	 * @param carport_no
	 * @return
	 */
	public List<Carport> getCarportByCarportNo(String carport_no);

	/**
	 * 描述：批量插入车位表记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午4:30:18
	 * @since
	 * @param carports
	 * @return
	 */
	public List<Boolean> batchInsertCarports(List<Carport> carports);

	/**
	 * 描述：按组织机构号和当天时间查询车位信息
	 * @param carport
	 * @return
	 */
	public List<Carport> getCarportByNowDate(Carport carport);
	/**
	 * 描述：修改车位状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:52:03
	 * @since
	 * @param carport_id
	 * @return
	 */
	public boolean modifyCarportStatus1ByCarportId(String carport_id);

	/**
	 * 描述：修改车位状态为0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:52:16
	 * @since
	 * @param carport_id
	 * @return
	 */
	public boolean modifyCarportStatus0ByCarportId(String carport_id);

	/**
	 * 描述：插入车位记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午3:09:22
	 * @since
	 * @param carport
	 * @return
	 */
	public boolean addCarportInfo(Carport carport);

	/**
	 * 描述：按carport_id删除
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:45:00
	 * @since
	 * @param carport_id
	 * @return
	 */
	public boolean deleteCarportByCarportId(String carport_id);
}
