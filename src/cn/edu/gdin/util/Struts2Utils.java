package cn.edu.gdin.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class Struts2Utils
{
	//��ȡ����response�����
	public static HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}
	//��ȡ����request�����
	public static HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}
	//��ȡsession�����
	public static Map<String, Object> getSession(){
		/*����sessionʱ�䣨��λ��s��*/
		//ServletActionContext.getRequest().getSession().setMaxInactiveInterval(8*60*60); 
		return ActionContext.getContext().getSession();
	}
	//����̨���ݷ���request����,����ǰ̨����
	public static void set2Request(String s,Object o){
		ServletActionContext.getRequest().setAttribute(s, o);
	}
}
