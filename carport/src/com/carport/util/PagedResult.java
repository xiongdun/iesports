/**
 * 
 */
package com.carport.util;

import java.util.List;

/**
 * ������PagedResult��װ��һ����ҳ�����
 * @author xiongdun
 * @created 2016��12��15�� ����5:26:16
 * @since 
 */
public class PagedResult<T> extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<T> dataList;//����  
    
    private long pageNo;//��ǰҳ  
      
    private long pageSize;//����  
      
    private long total;//������  
      
    private long pages;//��ҳ����Ŀ  
  
    public List<T> getDataList() {  
        return dataList;  
    }  
  
    public void setDataList(List<T> dataList) {  
        this.dataList = dataList;  
    }  
  
    public long getPageNo() {  
        return pageNo;  
    }  
  
    public void setPageNo(long pageNo) {  
        this.pageNo = pageNo;  
    }  
  
    public long getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(long pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public long getTotal() {  
        return total;  
    }  
  
    public void setTotal(long total) {  
        this.total = total;  
    }  
  
    public long getPages() {  
        return pages;  
    }  
  
    public void setPages(long pages) {  
        this.pages = pages;  
    }

}
