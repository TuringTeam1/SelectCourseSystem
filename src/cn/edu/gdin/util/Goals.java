package cn.edu.gdin.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Goals {
	 public static String Log_Type_LOGIN="��¼"; //��½
	 public static String Log_Type_EXIT="�˳�";  //�˳�
	 public static String Log_Type_INSERT="����"; //����
	 public static String Log_Type_DEL="ɾ��"; //ɾ��
	 public static String Log_Type_UPDATE="����"; //����
	 public static String Log_Type_UPLOAD="�ϴ�"; //�ϴ�
	 public static String Log_Type_IMPORT="����"; //����
	 public static String Log_Type_EXPORT="����"; //����
	 public static String Log_Type_OTHER="����"; //����
	 
	 public static String getAName(){
		 return (String) Struts2Utils.getHttpServletRequest().getSession().getAttribute("username");
	 }
	 
	 public static String getExceptionMessage(Exception ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			return sw.toString();
		}
}
