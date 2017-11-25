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
 * 描述：单个文件上传适合使用的servlet类方法
 * @author xiongdun
 * @created 2016年9月19日 下午5:35:33
 * @since 
 */
public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 日志打印配置
	private static Logger logger = Logger.getLogger(FileUploadServlet.class);
	// 上传文件的持久目录名
	private static final String uploadFolerName = Configuration.getString("fileUpload.uploadFolerName");
	// 上传文件的临时保存目录
	private static final String tempFolerName = Configuration.getString("fileUpload.tempFolerName");
	// 上传文件大小控制为30M
	private static final double fileMaxSize = Configuration.getDouble("fileUpload.fileMaxSize");
	// 允许上传的文件后缀名
	private static final String[] extensionPermit = StringUtil.split(Configuration.getString("fileUpload.extensionPermit"), "|");
	// 统一的编码格式
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
		logger.info("文件上传操作开始！");
		try {
			// 获取当前项目路径
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
		logger.info("文件上传操作结束！");
	}
	
	/**
	 * 描述：返回数据
	 * @author xiongdun
	 * @created 2016年11月28日 下午10:59:28
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
