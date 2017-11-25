/**
 * 首先编写核心代码，Javascript打开上传页面，并且
 * 从上传页获取返回参数，最后数据返回给回调函数callback
 */

/**
 * 跳转到上传
 * functionId:功能ID 
 * fileType:文件类型 
 * maxSize:文件容量上限 
 * callback:回调函数，返回三个参数：文件真名、文件存放名和文件大小 
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