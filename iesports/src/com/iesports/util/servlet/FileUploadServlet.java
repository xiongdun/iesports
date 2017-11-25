/**
 * 
 */
package com.iesports.util.servlet;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.iesports.util.FileHelper;
import com.iesports.util.StringUtil;
import com.iesports.util.config.Configuration;
import com.iesports.util.enums.FileUploadState;

/**
 * �����������ļ��ϴ��ʺ�ʹ�õ�servlet�෽��
 * @author xiongdun
 * @created 2016��9��19�� ����5:35:33
 * @since 
 */
public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ��־��ӡ����
	private static Logger logger = Logger.getLogger(FileUploadServlet.class);
	// �ϴ��ļ��ĳ־�Ŀ¼��
	private static final String uploadFolerName = Configuration.getString("fileUpload.uploadFolerName");
	// �ϴ��ļ�����ʱ����Ŀ¼
	private static final String tempFolerName = Configuration.getString("fileUpload.tempFolerName");
	// �ϴ��ļ���С����Ϊ30M
	private static final double fileMaxSize = Configuration.getDouble("fileUpload.fileMaxSize");
	// �����ϴ����ļ���׺��
	private static final String[] extensionPermit = StringUtil.split(Configuration.getString("fileUpload.extensionPermit"), "|");
	// ͳһ�ı����ʽ
	private static final String encode = Configuration.getString("fileUpload.encode");
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info("�ļ��ϴ�������ʼ��");
		try {
			// ��ȡ��ǰ��Ŀ·��
			String currProjectPath = this.getServletContext().getRealPath("/");
			String saveDirectoryPath = currProjectPath + "/" + uploadFolerName;
			String tempDirectoryPath = currProjectPath + "/" + tempFolerName;
			File saveDirecotory = FileHelper.generateDirectory(saveDirectoryPath);
			File tempDirecotory = FileHelper.generateDirectory(tempDirectoryPath);
			
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
			
			List<FileItem> fileItems = upload.parseRequest(req);
			for (Iterator<FileItem> iterator = fileItems.iterator(); iterator.hasNext();) {
				//type type = (type) iterator.next();
				
			}
			
		} catch (Exception e) {
			
		}
		logger.info("�ļ��ϴ�����������");
	}
	
	/**
	 * ��������������
	 * @author xiongdun
	 * @created 2016��11��28�� ����10:59:28
	 * @since 
	 * @param resp
	 * @param state
	 */
	private void responeMessage(HttpServletResponse resp, FileUploadState state) {
		resp.setCharacterEncoding(encode);
		resp.setContentType("text/html;charset" + encode);
		Writer writer = null;
		try {
			writer = resp.getWriter();
			writer.write("<script>");
			writer.write("window.parent.fileUploadCallBack({\"code\":" +
			state.getCode() +",\"message\":\"" + state.getMessage()+ "\"});");
			writer.write("</script>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

}
