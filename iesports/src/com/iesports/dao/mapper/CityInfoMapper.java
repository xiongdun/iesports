/**
 * 
 */
package com.iesports.dao.mapper;

import java.util.List;

import com.iesports.dao.bean.CityInfo;


/**
 * ������user��ӳ���ļ�
 * @author xiongdun
 * @created 2016��10��11�� ����8:45:58
 * @since 
 */
public interface CityInfoMapper {
	
	public List<CityInfo> getCity();
	
	public CityInfo selectCityId(String id);
	
	public int insertCityInfo(CityInfo city);
	
	public int updateCityInfo(CityInfo city);

	public int deleteCityInfoById(String id);
}
