/**
 * 
 */
package com.carport.service;

import java.util.List;
import java.util.Map;

import com.carport.bean.Apartment;
import com.github.pagehelper.PageInfo;

/**
 * 描述：部门管理
 * 
 * @author zhangyijie
 * @created 2016年12月2日 上午8:58:35
 * @since
 */
public interface ApartmentService {

	/**
	 * 描述：查询所有部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午11:12:40
	 * @since
	 * @return
	 */
	public List<Apartment> queryAllApartment();

	/**
	 * 描述：查询状态为1的部门
	 * @author xiongdun
	 * @created 2016年12月15日 下午5:00:38
	 * @since 
	 * @return
	 */
	public List<Apartment> queryApartmentStatus1();
	/**
	 * 描述：获取各个部门的车位号
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午11:19:27
	 * @since
	 * @return
	 */
	public Map<String, Integer[]> getAllApartmentCarport();

	/**
	 * 描述：查询指定部门所有的车位号
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午12:27:39
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public String[] getApartmentCarportByApNo(String apartment_no);

	/**
	 * 描述：部门编号查询部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午1:31:14
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public Apartment getApartmentInfoByApNo(String apartment_no);

	/**
	 * 描述：按Id查询部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午1:34:45
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public Apartment getApartmentInfoById(String apartment_id);

	/**
	 * 描述：id查询车位编号
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午12:58:30
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public String[] getApartmentCarportByApId(String apartment_id);

	/**
	 * 描述：添加部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午3:56:29
	 * @since
	 * @param apartment
	 * @return
	 */
	public boolean addApartmentInfo(Apartment apartment);

	/**
	 * 描述：修改部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午3:57:26
	 * @since
	 * @param apartment
	 * @return
	 */
	public boolean modifyApartmentInfoByApId(Apartment apartment);

	/**
	 * 描述：按apartment_id修改部门状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:14:31
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public boolean modfiyApartmentStatusTo1ByApId(String apartment_id);

	/**
	 * 描述：按apartment_id修改部门状态为0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:14:53
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public boolean modfiyApartmentStatusTo0ByApId(String apartment_id);

	/**
	 * 描述：按apartment_id 删除部门信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午4:00:14
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public boolean deleteApartmentInfoByApId(String apartment_id);
	
	/**
	 * 描述：分页查询部门信息
	 * @author zhangyijia
	 * @created 2016年12月19日 下午4:00:57
	 * @since 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<Apartment> queryApartmentByPage(Integer pageNo, Integer pageSize);

}
