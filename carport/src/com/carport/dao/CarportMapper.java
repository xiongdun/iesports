/**
 * 
 */
package com.carport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carport.bean.Carport;
import com.carport.model.CarportModel;

/**
 * 描述：车位映射
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午5:47:09
 * @since
 */
public interface CarportMapper {

	/**
	 * 描述：插入车位表数据
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:25:54
	 * @since
	 * @param carport
	 * @return
	 */
	public int insertCarport(Carport carport);

	/**
	 * 描述：查询所有的车位信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:29:02
	 * @since
	 * @return
	 */
	public List<Carport> selectAllCarport();

	/**
	 * 描述：按车位号查询车位信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:30:12
	 * @since
	 * @param carport_no
	 * @return
	 */
	public List<Carport> selectCarportByCarportNo(@Param("carport_no") String carport_no);

	/**
	 * 描述：按组织机构号查询车位信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:44:19
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Carport> selectCarportByOrgId(@Param("org_id") String org_id);

	/**
	 * 描述：按组织机构号和当天时间查询车位信息
	 * @param carport
	 * @return
	 */
	public List<Carport> selectCarportByNowDate(Carport carport);
	/**
	 * 描述：按组织机构和status为1查询车位
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午2:55:39
	 * @since
	 * @param carport
	 * @return
	 */
	public List<Carport> selectCarportByOrgIdAndStatus1(Carport carport);

	/**
	 * 描述：按部门Id查询车位信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:44:24
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<Carport> selectCarportByApartmentId(@Param("apartment_id") String apartment_id);

	/**
	 * 描述：修改车位状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:28:54
	 * @since
	 * @param carport_no
	 * @return
	 */
	public int updateCarportStatusTo1(@Param("carport_id") String carport_id);

	/**
	 * 描述：修改车位状态为0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午7:47:21
	 * @since
	 * @param carport_no
	 * @return
	 */
	public int updateCarportStatusTo0(@Param("carport_id") String carport_id);

	/**
	 * 描述：按carport_id删除
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:46:07
	 * @since
	 * @param carport_id
	 * @return
	 */
	public int deleteCarportByCarportId(@Param("carport_id") String carport_id);
	
	/**
	 * 描述：内链接查询车位
	 * @author zhangyijie
	 * @created 2016年12月19日 下午8:22:15
	 * @since 
	 * @return
	 */
	public List<CarportModel> selectCarportByPage();
}
