/**
 * 
 */
package com.iesports.util;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * ���������ݷ�װ�����ദ���ҳʱʹ��
 * 
 * @author xiongdun
 * @created 2016��9��1�� ����10:09:19
 * @since
 */
public class DataPage{
	private static Logger logger = Logger.getLogger(DataPage.class);
	
	/**
	 * Ĭ��ÿҳ��ʾ�ļ�¼��
	 */
	public static final int NUMBERS_PER_PAGE = 10;
	// ÿһҳ����ʾ�ļ�¼��
	private int numPerPage;
	// ��¼������
	private int totalRows;
	// ��¼��ҳ��
	private int totalPages;
	// ��ǰҳ��
	private int currentPage;
	// ��ʼ����
	private int startIndex;
	// ��һ�ν���������
	private int lastIndex;
	// �����������List����
	private List dataList;
	
	private boolean firstFlag = true;
    private boolean prevFlag = true;
    private boolean nextFlag = true;
    private boolean lastFlag = true;
    private String buttonType = "";
	
    /**
     * ��ҳ���޲ι�����
     */
    public DataPage() {
	}
    
    /**
     * ��ҳ������
     * @param currentPage ��ǰҳ��
     * @param numPerPage ÿҳ��ʾ�ļ�¼��
     */
    public DataPage(int currentPage, int numPerPage) {
    	this.currentPage = currentPage;
    	this.numPerPage = numPerPage;
    }
    
    /**
     * �����������ܼ�¼������ͨ����������������ҳֵ
     * @author xiongdun
     * @created 2016��9��8�� ����11:36:50
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
     * ���������÷�ҳ��ť��Ϣ
     * @author xiongdun
     * @created 2016��9��8�� ����11:38:28
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
     * ������������ҳ��
     * @author xiongdun
     * @created 2016��9��8�� ����7:33:41
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
     * ������������ʼ����
     * @author xiongdun
     * @created 2016��9��8�� ����7:34:13
     * @since
     */
    private void calcStartIndex() {
    	this.startIndex = (currentPage - 1) * numPerPage;
    }
    
    /**
     * �����������������
     * @author xiongdun
     * @created 2016��9��8�� ����7:34:34
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
     * ��������Ⱦbutton��ť
     * @author xiongdun
     * @created 2016��9��8�� ����7:37:21
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
     * ��������Ⱦtext ��ǩ
     * @author xiongdun
     * @created 2016��9��8�� ����7:40:01
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
     * ���������ð�ť����
     * @author xiongdun
     * @created 2016��9��8�� ����7:43:26
     * @since 
     * @param buttonType
     */
    public void setButtonType(String buttonType) { 
    	this.buttonType = buttonType;
    }
    
    /**
     * ��������ȡ��ǰҳ��
     * @author xiongdun
     * @created 2016��9��8�� ����7:44:46
     * @since 
     * @return
     */
    public int getCurrentPage() {
    	return this.currentPage;
    }
    
    /**
     * ��������ȡÿҳ��ʾ�ļ�¼��
     * @author xiongdun
     * @created 2016��9��8�� ����7:45:30
     * @since 
     * @return
     */
    public int getNumPerPage() {
    	return this.numPerPage;
    }
    
    /**
     * ��������ȡ��ҳ����
     * @author xiongdun
     * @created 2016��9��8�� ����7:48:47
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
     * ��������ȡ��ҳ��
     * @author xiongdun
     * @created 2016��9��8�� ����7:49:35
     * @since 
     * @return
     */
    public int getTotalPages() {
    	return this.totalPages;
    }
    
    /**
     * ��������ȡ�ܼ�¼����
     * @author xiongdun
     * @created 2016��9��8�� ����7:50:23
     * @since 
     * @return
     */
    public int getTotalRows() {
    	return this.totalRows;
    }
    
    /**
     * ��������õ�ǰ��ҳ��¼��ʼλ��
     * @author xiongdun
     * @created 2016��9��8�� ����7:52:02
     * @since 
     * @return
     */
    public int getStartIndex() {
    	return this.startIndex;
    }
    
    /**
     * ��������õ�ǰ��ҳ��¼����¼λ��
     * @author xiongdun
     * @created 2016��9��8�� ����7:52:42
     * @since 
     * @return
     */
    public int getLastIndex() {
    	return this.lastIndex;
    }
    
    /**
     * ���������÷�ҳ���������
     * @author xiongdun
     * @created 2016��9��8�� ����7:53:47
     * @since 
     * @param dataList
     */
    public void setDataList(List dataList) {
    	this.dataList = dataList;
    }
    
    /**
     * ����������url�����ҳ�ַ���
     * @author xiongdun
     * @created 2016��9��8�� ����7:56:24
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

        linkStr += "�� <b>" + total + "</b> ��&nbsp;��ǰ <b>" + pageNumber +
            "</b>/<b>" + pages + "</b> ҳ&nbsp;&nbsp;";
        if (url.indexOf("?") > 0) { //�Ѿ�����һ������
            url = url + "&";
        } else {
            url = url + "?";
        }
        if ( (pageNumber == 1) && (pageNumber < pages)) {
            linkStr += "��ҳ&nbsp;��һҳ&nbsp;<a href='" + url + "&pageNumber=" +
                (pageNumber + 1) +
                "'>��һҳ</a>&nbsp;<a href='" + url +
                "&pageNumber=" +
                pages + "'>βҳ</a>&nbsp;����<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='"+pageNumber+"'>ҳ&nbsp;<img border=\"0\" src=\""+path+"/images/button020.gif\" width=\"20\" height=\"18\"  style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
        } else if ( (pageNumber > 1) && (pageNumber < pages)) {
        	logger.info("��ҳ���ã�");
            linkStr += "<a href='" + url +
                "&pageNumber=1'>��ҳ</a>&nbsp;<a href='" +
                url + "&pageNumber=" + (pageNumber - 1) +
                "'>��һҳ</a>&nbsp;<a href='" + url +
                "&pageNumber=" +
                (pageNumber + 1) + "'>��һҳ&nbsp;<a href='" +
                url +
                "&pageNumber=" +
                pages + "'>βҳ</a>&nbsp;����<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='"+pageNumber+"'>ҳ&nbsp;<img border=\"0\" src=\""+path+"/images/button020.gif\" width=\"20\" height=\"18\"  style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
        } else if ( (pageNumber == pages) && (pages > 1)) {
            linkStr += "<a href='" + url +
                "&pageNumber=1'>��ҳ</a>&nbsp;<a href='" +
                url + "&pageNumber=" + (pageNumber - 1) +
                "'>��һҳ</a>&nbsp;��һҳ&nbsp;βҳ&nbsp;����<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='"+pageNumber+"'>ҳ&nbsp;<img border=\"0\" src=\""+path+"/images/button020.gif\" width=\"20\" height=\"18\" style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
        }
        scriptTmp += "<SCRIPT LANGUAGE=\"JavaScript\">\n<!--\nfunction checkPage()\n {\n if(document.getElementById(\"jump\").value > " +
            pages + ") {\n alert('�������ҳ�볬����Χ�����������룡');\n document.getElementById('jump').focus();\n return false;\n} else if(document.getElementById('jump').value < 1) {\n alert('�������ҳ�볬����Χ�����������룡');\n document.getElementById('jump').focus();\n return false;\n} else {\n jumpTo('" +
            url + "');\n}\n}\n//-->\n</SCRIPT>";
        scriptTmp += "<SCRIPT LANGUAGE=\"JavaScript\">\n<!--\nfunction jumpTo(url)\n {\n self.location.href=\"" +
            url + "&pageNumber=\"+" +
            "document.getElementById(\"jump\").value;\n}\n//-->\n</SCRIPT>";
        linkStr += scriptTmp;
        return linkStr;
    }
    
}
