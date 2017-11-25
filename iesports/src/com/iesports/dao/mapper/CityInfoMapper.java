/**
 * 
 */
package com.iesports.dao.mapper;

import java.util.List;

import com.iesports.dao.bean.CityInfo;


/**
 * 描述：user表映射文件
 * @author xiongdun
 * @created 2016年10月11日 上午8:45:58
 * @since 
 */
public interface CityInfoMapper {
	
	public List<CityInfo> getCity();
	
	public CityInfo selectCityId(String id);
	
	public int insertCityInfo(CityInfo city);
	
	public int updateCityInfo(CityInfo city);

	public int deleteCityInfoById(String id);
}
