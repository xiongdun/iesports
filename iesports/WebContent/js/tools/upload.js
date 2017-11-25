/**
 * ���ȱ�д���Ĵ��룬Javascript���ϴ�ҳ�棬����
 * ���ϴ�ҳ��ȡ���ز�����������ݷ��ظ��ص�����callback
 */

/**
 * ��ת���ϴ�
 * functionId:����ID 
 * fileType:�ļ����� 
 * maxSize:�ļ��������� 
 * callback:�ص����������������������ļ��������ļ���������ļ���С 
 */
function openUpload(functionId, fileType, maxSize, callback) {
	var url =  root + "/CommonController.jhtml?method=goFileUpload&";
	if (functionId != null) {
		url = url + "functionId" + functionId + "&";
	}
	if (fileType != null) {
		url = url + "fileType=" + fileType + "&";
	}
	if (maxSize != null) {
		url = url + "maxSize=" + maxSize;
	}
	
	var win = window.showModalDialog(url, "", "dialogWidth:300px;dialogHeight:150px;scroll:no;status:no");
	if (win != null) {
		var arrWin = win.split(",");
		callback(arrWin[0], arrWin[1], arrWin[2]);
	}
}