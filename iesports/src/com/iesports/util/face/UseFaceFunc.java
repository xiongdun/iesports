/**
 * 
 */
package com.iesports.util.face;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



/**
 * ������
 * @author xiongdun
 * @created 2016��9��23�� ����1:54:35
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
				System.out.println("��������Ч��ͼƬURL��ַ...");
				//http://10.4.16.124:7001/SunECMDM/servlet/getFile?ZmlsZVNpemU9MTY2MjQwOCZncm91cE5hbWU9c3VuZWNtZGVsJnZvbHVtbklEPTEmU0FWRV9OQU1FPTgwRkU5QzAzLUE4QTAtNzhCOS01NUZCLUM5MEE2N0UxRkQ3MSZmaWxlSUQ9ODBGRTlDMDMtQThBMC03OEI5LTU1RkItQzkwQTY3RTFGRDcxJmZpbGVUeXBlPWpwZyZlZmZUaW1lPTIwMTYwOTIzMTYxMSZmaWxlUGF0aD0yMDE2MDkvMjkvJnNlcnZlcklEPTE=
				System.out.println("��һ��ͼƬ��ַ��");
				String imgFace = sc.next();
				System.out.println("�ڶ���ͼƬ��ַ��");
				String imgNet = sc.next();
				FaceFunc faceFunc = new FaceFunc();
				result = faceFunc.getResult(imgFace, imgNet);
				String is_success = (String) result.get("is_success");
				if (is_success == "1" || "1".equals(is_success)) {
					String face_result = (String) (result.get("face_result") + "");
					String create_time = (String) result.get("create_time");
					int checkResultStatus = Integer.valueOf((String) result.get("checkResultStatus"));
					String cast_time = (String) (result.get("cast_time") + "");
					System.out.println("�������ʶ�����������");
					System.out.println("������ƶȡ��" + face_result + "%");
					System.out.println("����Ƿ�ɹ����" + (checkResultStatus == 0 ? "ʧ��" : "�ɹ�" ));
					System.out.println("���ʶ���ʱ���" + cast_time + "����");
					System.out.println("���ʶ��ʹ��ʱ����" + create_time);
				} else {
					System.out.println("�������ʶ�����������");
					System.out.println("�����������1��ͼƬ������2������ʶ��ʱ���");
				}
				
				System.out.println("����Ƿ����ʹ�ã���������ַ����-->��������� / ���no���-->���������");
				arg = sc.next();
			}
			System.out.println("����������������Խ�������ӭ�´�ʹ�ã���������������");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void help() {
		String str1 = "������������������ʶ����Թ��� v1.1��������������";
	    String str2 = "�����������������ߣ�xiongdun��������������";
	    String str5 = "��������������Email:xiongdun@thinkive.com��������������";
	    String str3 = "��������������˹���ֻ������������֮�ã����������Ƿ���;��������������";
	    String str4 = "���������������˳�����ָ�no��������������";
	    System.out.println(str1);
	    System.out.println(str2);
	    System.out.println(str5);
	    System.out.println(str3);
	    System.out.println(str4);
	}
}
