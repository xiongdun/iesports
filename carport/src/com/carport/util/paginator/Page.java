/**
 * 
 */
package com.carport.util.paginator;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月21日 上午10:34:32
 * @since 
 */
public class Page {
	
	private static Logger logger = Logger.getLogger(Page.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private static final String DEFAULT_SIZE = "10";
	private Integer pageNo;// 当前页码
	private Integer pageSize;// 每页行数
	private Integer totalRecord;// 总记录数
	private Integer totalPage;// 总页数
	private Map<String, String> params;// 查询条件
	private Map<String, List<String>> paramLists;// 数组查询条件
	private String searchUrl;// URL地址
	private String pageNoDisp;// 可显示的分页号
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public Map<String, List<String>> getParamLists() {
		return paramLists;
	}
	public void setParamLists(Map<String, List<String>> paramLists) {
		this.paramLists = paramLists;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	public String getPageNoDisp() {
		return pageNoDisp;
	}
	public void setPageNoDisp(String pageNoDisp) {
		this.pageNoDisp = pageNoDisp;
	}
	
	private Page() {
		pageNo = 1;
		pageSize = Integer.valueOf(DEFAULT_SIZE);
		totalPage = 0;
		totalRecord = 0;
		params = Maps.newHashMap();
		paramLists = Maps.newHashMap();
		searchUrl = "";
		pageNoDisp = "";
	}
	
	public static Page newBuilder(int pageNo, int pageSize, String url) {
		Page page = new Page();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setSearchUrl(url);
		return page;
	}
	
	/**
	 * 描述：条件查询装JSON
	 * @author xiongdun
	 * @created 2016年12月21日 下午1:40:41
	 * @since 
	 * @return
	 */
	public String getParaJson() {
		Map<String, Object> map = Maps.newHashMap();
		for (String key : params.keySet()) {
			if (params.get(key) != null) {
				map.put(key, params.get(key));
			}
		}
		String json = "";
		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("json转换异常，异常原因：", e);
		}
		return json;
	}
	
	/**
	 * 描述：数组查询条件转Json
	 * @author xiongdun
	 * @created 2016年12月21日 下午1:44:55
	 * @since 
	 * @return
	 */
	public String getParaListJson() {
		Map<String, Object> map = Maps.newHashMap();
		for (String key : paramLists.keySet()) {
			List<String> lists = paramLists.get(key);
			if (lists != null && lists.size() > 0) {
				map.put(key, lists);
			}
		}
		String json = "";
		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("json转换异常，异常原因：", e);
		}
		return json;
	}
	
	/**
	 * 描述：总件数变化时，更新总页数并计算显示样式
	 * @author xiongdun
	 * @created 2016年12月21日 下午1:48:32
	 * @since
	 */
	private void refreshPage() {
		// 总页数计算
		totalPage = totalRecord % pageSize == 0 ? 
				totalRecord / pageSize : (totalRecord / pageSize + 1);
		// 防止超过最末页，浏览途中数据被删除的情况
		if (pageNo > totalPage && totalPage != 0) {
			pageNo = totalPage;
		}
		pageNoDisp = computeDisplayStyleAndPage();
	}
	
	private String computeDisplayStyleAndPage() {
		List<Integer> pageDisplays = Lists.newArrayList();
		if ( totalPage <= 11 ) {  
			for (int i = 1; i <= totalPage; i++) {  
				pageDisplays.add(i);  
			}  
		} else if ( pageNo < 7 ) {  
			for (int i=1; i<=8; i++){  
				pageDisplays.add(i);  
			}  
			pageDisplays.add(0);// 0 表示 省略部分(下同)  
			pageDisplays.add(totalPage-1);         
			pageDisplays.add(totalPage);  
		} else if ( pageNo> totalPage-6 ) {  
			pageDisplays.add(1);  
			pageDisplays.add(2);  
			pageDisplays.add(0);  
			for (int i = totalPage-7; i <= totalPage; i++) {  
				pageDisplays.add(i);  
			}         
	    } else {  
	    	pageDisplays.add(1);  
	    	pageDisplays.add(2);  
	    	pageDisplays.add(0);  
	    	for (int i=pageNo-2; i<=pageNo+2; i++) {  
	    		pageDisplays.add(i);  
	    	}  
	    	pageDisplays.add(0);
	    	pageDisplays.add(totalPage-1);  
	    	pageDisplays.add(totalPage);  
	    }  
	    return Joiner.on("|").join(pageDisplays.toArray());
	}
}
