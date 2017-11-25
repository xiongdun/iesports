/**
 * 
 */
package com.iesports.util.enums;

/**
 * 描述：文件上传状态
 * @author xiongdun
 * @created 2016年11月28日 下午10:46:38
 * @since 
 */
public enum FileUploadState {
	OK(200, "上传成功"),
	ERROR(500, "上传失败"),
	OVER_FILE_LIMIT(501, "超过文件上传大小限制"),
	NO_SUPPORT_EXTENSION(502, "不支持的扩展名");
	
	private int code;
	private String message;
	private FileUploadState(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 描述：获取状态码
	 * @author xiongdun
	 * @created 2016年11月28日 下午10:49:50
	 * @since 
	 * @return
	 */
	public int getCode() {
		return code;
	}
	/**
	 * 描述：获取状态名
	 * @author xiongdun
	 * @created 2016年11月28日 下午10:50:08
	 * @since 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
}
