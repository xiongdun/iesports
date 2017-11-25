/**
 * 
 */
package com.iesports.util.face;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



/**
 * 描述：
 * @author xiongdun
 * @created 2016年9月23日 下午1:54:35
 * @since 
 */
public class UseFaceFunc {
	public static void main(String[] args) {
		try {
			help();
			Scanner sc = new Scanner(System.in);
			String arg = "";
			Map result = new HashMap();
			while (!arg.equals("no")) {
				System.out.println("请输入有效的图片URL地址...");
				//http://10.4.16.124:7001/SunECMDM/servlet/getFile?ZmlsZVNpemU9MTY2MjQwOCZncm91cE5hbWU9c3VuZWNtZGVsJnZvbHVtbklEPTEmU0FWRV9OQU1FPTgwRkU5QzAzLUE4QTAtNzhCOS01NUZCLUM5MEE2N0UxRkQ3MSZmaWxlSUQ9ODBGRTlDMDMtQThBMC03OEI5LTU1RkItQzkwQTY3RTFGRDcxJmZpbGVUeXBlPWpwZyZlZmZUaW1lPTIwMTYwOTIzMTYxMSZmaWxlUGF0aD0yMDE2MDkvMjkvJnNlcnZlcklEPTE=
				System.out.println("第一张图片地址：");
				String imgFace = sc.next();
				System.out.println("第二张图片地址：");
				String imgNet = sc.next();
				FaceFunc faceFunc = new FaceFunc();
				result = faceFunc.getResult(imgFace, imgNet);
				String is_success = (String) result.get("is_success");
				if (is_success == "1" || "1".equals(is_success)) {
					String face_result = (String) (result.get("face_result") + "");
					String create_time = (String) result.get("create_time");
					int checkResultStatus = Integer.valueOf((String) result.get("checkResultStatus"));
					String cast_time = (String) (result.get("cast_time") + "");
					System.out.println("★★★★★★识别结果★★★★★★");
					System.out.println("★★相似度★★" + face_result + "%");
					System.out.println("★★是否成功★★" + (checkResultStatus == 0 ? "失败" : "成功" ));
					System.out.println("★★识别耗时★★" + cast_time + "毫秒");
					System.out.println("★★识别使用时间★★" + create_time);
				} else {
					System.out.println("★★★★★★识别错误★★★★★★");
					System.out.println("★★可能情况：1、图片不可用2、服务识别超时★★");
				}
				
				System.out.println("★★是否继续使用？★★任意字符★★-->★★继续★★ / ★★no★★-->★★结束★★");
				arg = sc.next();
			}
			System.out.println("★★★★★★★★★★★★★测试结束！欢迎下次使用！★★★★★★★★★★★★★");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void help() {
		String str1 = "★★★★★★★★★★★★★人脸识别测试工具 v1.1★★★★★★★★★★★★★";
	    String str2 = "★★★★★★★★★★★★★作者：xiongdun★★★★★★★★★★★★★";
	    String str5 = "★★★★★★★★★★★★★Email:xiongdun@thinkive.com★★★★★★★★★★★★★";
	    String str3 = "★★★★★★★★★★★★★此工具只用作开发测试之用，切勿用作非法用途★★★★★★★★★★★★★";
	    String str4 = "★★★★★★★★★★★★★退出程序指令：no★★★★★★★★★★★★★";
	    System.out.println(str1);
	    System.out.println(str2);
	    System.out.println(str5);
	    System.out.println(str3);
	    System.out.println(str4);
	}
}
