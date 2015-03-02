package com.pgms.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.pgms.bean.Admin;
import com.pgms.service.AdminService;

public class AdminAction {
	private Admin admin;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String login(){
		Map<String, String> session=(Map)ActionContext.getContext().get(ActionContext.SESSION);  
		AdminService as = new AdminService();
		Boolean loginStatus = as.checkLogin(this.getAdmin().getUsername(), this.getAdmin().getPassword());
		if (loginStatus){
			session.put("username", this.getAdmin().getUsername());
			
			return "success";
		}
		Map request=(Map)ActionContext.getContext().get("request");  
		request.put("loginError", 1);
		return "input";
	}
	
	public String logout(){
		Map<String, String> session=(Map)ActionContext.getContext().get(ActionContext.SESSION);  
		session.remove("username");
		return "success";
	}
	

	
}
