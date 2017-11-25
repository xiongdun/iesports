/**
 * 
 */
package com.iesports.util;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * 描述：数据封装工具类处理分页时使用
 * 
 * @author xiongdun
 * @created 2016年9月1日 上午10:09:19
 * @since
 */
public class DataPage{
	private static Logger logger = Logger.getLogger(DataPage.class);
	
	/**
	 * 默认每页显示的记录数
	 */
	public static final int NUMBERS_PER_PAGE = 10;
	// 每一页中显示的记录数
	private int numPerPage;
	// 记录总行数
	private int totalRows;
	// 记录总页数
	private int totalPages;
	// 当前页码
	private int currentPage;
	// 起始行数
	private int startIndex;
	// 上一次结束的行数
	private int lastIndex;
	// 将结果集存入List集合
	private List dataList;
	
	private boolean firstFlag = true;
    private boolean prevFlag = true;
    private boolean nextFlag = true;
    private boolean lastFlag = true;
    private String buttonType = "";
	
    /**
     * 分页类无参构造器
     */
    public DataPage() {
	}
    
    /**
     * 分页构造器
     * @param currentPage 当前页码
     * @param numPerPage 每页显示的记录数
     */
    public DataPage(int currentPage, int numPerPage) {
    	this.currentPage = currentPage;
    	this.numPerPage = numPerPage;
    }
    
    /**
     * 描述：设置总记录数，并通过方法计算其他分页值
     * @author xiongdun
     * @created 2016年9月8日 上午11:36:50
     * @since 
     * @param totalRows
     */
    public void setTotalRows(int totalRows) {
    	this.totalRows = totalRows;
    	calcTotalPages();
    	calcLastIndex();
    	calcStartIndex();
    	setPageButton();
    }
    
    /**
     * 描述：设置分页按钮信息
     * @author xiongdun
     * @created 2016年9月8日 上午11:38:28
     * @since
     */
    public void setPageButton() {
    	if (totalPages == 0 || totalPages == 1) {
            firstFlag = false;
            prevFlag = false;
            nextFlag = false;
            lastFlag = false;
            return;
        }

        if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        if (currentPage <= 1 || currentPage == 0) {
            firstFlag = false;
            prevFlag = false;
        }
        if (currentPage >= totalPages || totalPages == 0) {
            lastFlag = false;
            nextFlag = false;
        }
    }
    
    /**
     * 描述：计算总页数
     * @author xiongdun
     * @created 2016年9月8日 下午7:33:41
     * @since
     */
    private void calcTotalPages() {
    	if (totalRows % numPerPage == 0) {
            this.totalPages = totalRows / numPerPage;
        }
        else {
            this.totalPages = (totalRows / numPerPage) + 1;
        }
    }
    
    /**
     * 描述：计算起始行数
     * @author xiongdun
     * @created 2016年9月8日 下午7:34:13
     * @since
     */
    private void calcStartIndex() {
    	this.startIndex = (currentPage - 1) * numPerPage;
    }
    
    /**
     * 描述：计算结束行数
     * @author xiongdun
     * @created 2016年9月8日 下午7:34:34
     * @since
     */
    private void calcLastIndex() {
    	if (totalRows < numPerPage) {
            this.lastIndex = totalRows;
        } else if ((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)) {
            this.lastIndex = currentPage * numPerPage;
        } else if (totalRows % numPerPage != 0 && currentPage == totalPages) {
            this.lastIndex = totalRows;
        }
    }
    
    /**
     * 描述：渲染button按钮
     * @author xiongdun
     * @created 2016年9月8日 下午7:37:21
     * @since 
     * @param show
     * @param disabled
     * @param page
     * @return
     */
    private String renderButton(String show, boolean disabled, int page) {
    	if (buttonType.equalsIgnoreCase("text")) {
    		return renderText(show, disabled, page);
    	}

        String temp = "";
        if (disabled == true) {
        	temp = "disabled";
        }
        return "<input type=\"submit\" class=\"pageButton\" value=\"" + show + "\" onclick=\"goToPage(" + page + ",this.form)\" " + temp + ">\n";
    }
    
    /**
     * 描述：渲染text 标签
     * @author xiongdun
     * @created 2016年9月8日 下午7:40:01
     * @since 
     * @param show
     * @param disabled
     * @param page
     * @return
     */
    private String renderText(String show, boolean disabled, int page) {
    	if (disabled == true) {
    		return show;
    	}
    	return "<a class=\"pageLink\" href=\"javascript:toPage(" + page + ")\">" + show + "</a>";
    }
    
    public String first(String show) {
    	if (firstFlag == false) {
    		return renderButton(show, true, 0);
    	}
    	return renderButton(show, false, 1);
    }
    
    public String preview(String show) {
    	if (prevFlag == false) {
    		return renderButton(show, true, 0);
    	}
    	return renderButton(show, false, (currentPage - 1));
    }
    
    public String next(String show) {
    	if (nextFlag == false) {
    		return renderButton(show, true, 0);
    	}
    	return renderButton(show, false, (currentPage + 1));
    }
    
    public String last(String show) {
    	if (lastFlag == false) {
    		return renderButton(show, true, 0);
    	}
    	return renderButton(show, false, totalPages);
    }
    
    /**
     * 描述：设置按钮类型
     * @author xiongdun
     * @created 2016年9月8日 下午7:43:26
     * @since 
     * @param buttonType
     */
    public void setButtonType(String buttonType) { 
    	this.buttonType = buttonType;
    }
    
    /**
     * 描述：获取当前页码
     * @author xiongdun
     * @created 2016年9月8日 下午7:44:46
     * @since 
     * @return
     */
    public int getCurrentPage() {
    	return this.currentPage;
    }
    
    /**
     * 描述：获取每页显示的记录数
     * @author xiongdun
     * @created 2016年9月8日 下午7:45:30
     * @since 
     * @return
     */
    public int getNumPerPage() {
    	return this.numPerPage;
    }
    
    /**
     * 描述：获取分页数据
     * @author xiongdun
     * @created 2016年9月8日 下午7:48:47
     * @since 
     * @return
     */
    public List getDataList() {
    	
    	if (dataList != null && dataList.size() > getNumPerPage()) {
    		return dataList.subList(getStartIndex(), getLastIndex());
    	}
    	return dataList;
    }
    
    /**
     * 描述：获取总页数
     * @author xiongdun
     * @created 2016年9月8日 下午7:49:35
     * @since 
     * @return
     */
    public int getTotalPages() {
    	return this.totalPages;
    }
    
    /**
     * 描述：获取总记录行数
     * @author xiongdun
     * @created 2016年9月8日 下午7:50:23
     * @since 
     * @return
     */
    public int getTotalRows() {
    	return this.totalRows;
    }
    
    /**
     * 描述：获得当前分页记录开始位置
     * @author xiongdun
     * @created 2016年9月8日 下午7:52:02
     * @since 
     * @return
     */
    public int getStartIndex() {
    	return this.startIndex;
    }
    
    /**
     * 描述：获得当前分页记录最后记录位置
     * @author xiongdun
     * @created 2016年9月8日 下午7:52:42
     * @since 
     * @return
     */
    public int getLastIndex() {
    	return this.lastIndex;
    }
    
    /**
     * 描述：设置分页对象的数据
     * @author xiongdun
     * @created 2016年9月8日 下午7:53:47
     * @since 
     * @param dataList
     */
    public void setDataList(List dataList) {
    	this.dataList = dataList;
    }
    
    /**
     * 描述：根据url构造分页字符串
     * @author xiongdun
     * @created 2016年9月8日 下午7:56:24
     * @since 
     * @param url
     * @param path
     * @return
     */
    public String getLinkStr(String url, String path) {
    	String linkStr = "";
        String scriptTmp = "";

        int pageNumber = this.currentPage;
        int pages = this.totalPages;
        int total = this.totalRows;

        linkStr += "共 <b>" + total + "</b> 条&nbsp;当前 <b>" + pageNumber +
            "</b>/<b>" + pages + "</b> 页&nbsp;&nbsp;";
        if (url.indexOf("?") > 0) { //已经带有一个参数
            url = url + "&";
        } else {
            url = url + "?";
        }
        if ( (pageNumber == 1) && (pageNumber < pages)) {
            linkStr += "首页&nbsp;上一页&nbsp;<a href='" + url + "&pageNumber=" +
                (pageNumber + 1) +
                "'>下一页</a>&nbsp;<a href='" + url +
                "&pageNumber=" +
                pages + "'>尾页</a>&nbsp;跳到<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='"+pageNumber+"'>页&nbsp;<img border=\"0\" src=\""+path+"/images/button020.gif\" width=\"20\" height=\"18\"  style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
        } else if ( (pageNumber > 1) && (pageNumber < pages)) {
        	logger.info("分页设置！");
            linkStr += "<a href='" + url +
                "&pageNumber=1'>首页</a>&nbsp;<a href='" +
                url + "&pageNumber=" + (pageNumber - 1) +
                "'>上一页</a>&nbsp;<a href='" + url +
                "&pageNumber=" +
                (pageNumber + 1) + "'>下一页&nbsp;<a href='" +
                url +
                "&pageNumber=" +
                pages + "'>尾页</a>&nbsp;跳到<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='"+pageNumber+"'>页&nbsp;<img border=\"0\" src=\""+path+"/images/button020.gif\" width=\"20\" height=\"18\"  style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
        } else if ( (pageNumber == pages) && (pages > 1)) {
            linkStr += "<a href='" + url +
                "&pageNumber=1'>首页</a>&nbsp;<a href='" +
                url + "&pageNumber=" + (pageNumber - 1) +
                "'>上一页</a>&nbsp;下一页&nbsp;尾页&nbsp;跳到<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='"+pageNumber+"'>页&nbsp;<img border=\"0\" src=\""+path+"/images/button020.gif\" width=\"20\" height=\"18\" style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
        }
        scriptTmp += "<SCRIPT LANGUAGE=\"JavaScript\">\n<!--\nfunction checkPage()\n {\n if(document.getElementById(\"jump\").value > " +
            pages + ") {\n alert('您输入的页码超出范围，请重新输入！');\n document.getElementById('jump').focus();\n return false;\n} else if(document.getElementById('jump').value < 1) {\n alert('您输入的页码超出范围，请重新输入！');\n document.getElementById('jump').focus();\n return false;\n} else {\n jumpTo('" +
            url + "');\n}\n}\n//-->\n</SCRIPT>";
        scriptTmp += "<SCRIPT LANGUAGE=\"JavaScript\">\n<!--\nfunction jumpTo(url)\n {\n self.location.href=\"" +
            url + "&pageNumber=\"+" +
            "document.getElementById(\"jump\").value;\n}\n//-->\n</SCRIPT>";
        linkStr += scriptTmp;
        return linkStr;
    }
    
}
