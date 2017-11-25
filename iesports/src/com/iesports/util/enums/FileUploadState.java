/**
 * 
 */
package com.iesports.util.enums;

/**
 * �������ļ��ϴ�״̬
 * @author xiongdun
 * @created 2016��11��28�� ����10:46:38
 * @since 
 */
public enum FileUploadState {
	OK(200, "�ϴ��ɹ�"),
	ERROR(500, "�ϴ�ʧ��"),
	OVER_FILE_LIMIT(501, "�����ļ��ϴ���С����"),
	NO_SUPPORT_EXTENSION(502, "��֧�ֵ���չ��");
	
	private int code;
	private String message;
	private FileUploadState(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * ��������ȡ״̬��
	 * @author xiongdun
	 * @created 2016��11��28�� ����10:49:50
	 * @since 
	 * @return
	 */
	public int getCode() {
		return code;
	}
	/**
	 * ��������ȡ״̬��
	 * @author xiongdun
	 * @created 2016��11��28�� ����10:50:08
	 * @since 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
}
