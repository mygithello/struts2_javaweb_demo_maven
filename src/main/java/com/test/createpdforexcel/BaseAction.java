package com.test.createpdforexcel;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 */
public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = -403323345542984392L;


	public BaseAction(){
		//this.getResponse().setCharacterEncoding("utf-8");
	}

	/*static {
		new BaseAction().getResponse().setCharacterEncoding("utf-8");
	}*/

	private PrintWriter out;
	private ServletOutputStream servletOutputStream;



	public PrintWriter getOut() {
		try {
			if( null == out )
				return this.getResponse().getWriter();
			else
				return out;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public ServletOutputStream getServletOutputStream() {
		try {
			if( null == servletOutputStream )
				return this.getResponse().getOutputStream();
			else
				return servletOutputStream;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}


	public void closeOut(PrintWriter out){
		if(null != out){
			out.close();
			out = null;
		}
	}
	public void closeServletOutputStream(ServletOutputStream servletOutputStream){
		if(null != servletOutputStream){
			try {
				servletOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			servletOutputStream = null;
		}
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	ActionContext context = ActionContext.getContext();

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

}
