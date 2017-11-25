/**
 * 
 */
package com.iesports.test.carport.dao;

import java.util.List;

import com.iesports.test.carport.bean.Apartment;

/**
 * 描述：抢车位历史映射
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午5:47:09
 * @since
 */
public interface ApartmentMapper {

	/**
	 * 描述：插入部门
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:45:16
	 * @since
	 * @param apartment
	 * @return
	 */
	public int insertApartment(Apartment apartment);

	/**
	 * 描述：查询所有部门
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:45:25
	 * @since
	 * @return
	 */
	public List<Apartment> selectAllApartment();

	/**
	 * 描述：按Id查询部门
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:45:35
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public Apartment selectApartmentById(String apartment_id);

	/**
	 * 描述：按部门编号查询
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午12:30:31
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public List<Apartment> selectApartmentByApNo(String apartment_no);

	/**
	 * 描述：修改部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:06:52
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public int updateApartmentInfoByApId(Apartment apartment);

	/**
	 * 描述：按ID修改部门状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:45:47
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public int updateApartmentStatusTo1ById(String apartment_id);

	/**
	 * 描述：按ID修改部门状态为0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:52:50
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public int updateApartmentStatusTo0ById(String apartment_id);

	/**
	 * 描述：按apartment_id 删除部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:02:07
	 * @since
	 * @param aparment_id
	 * @return
	 */
	public int deleteApartmentInfoByApId(String apartment_id);
}
