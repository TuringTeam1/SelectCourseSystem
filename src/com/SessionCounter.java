package com;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ͳ����������
 * @author ׿С��
 * @version 1.1
 */
public class SessionCounter implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		ServletContext ctx = se.getSession().getServletContext();
		Integer numSessions = (Integer)ctx.getAttribute("numSessions");
		if(numSessions==null){
			numSessions = new Integer(1);
		}else{
			int count = numSessions.intValue();
			numSessions = new Integer(count+1);
		}
		ctx.setAttribute("numSessions", numSessions);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		ServletContext ctx = se.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
	    if(numSessions==null){
	    	numSessions = new Integer(0);
	    }else{
	    	int count = numSessions.intValue();
	    	numSessions = new Integer(count-1);
	    }
	
	    ctx.setAttribute("numSessions", numSessions);
	}

}
