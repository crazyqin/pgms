package com.pgms.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.pgms.bean.Admin;
import com.pgms.service.AdminService;

public class AdminAction {
	private Admin admin;
	//用于修改
	private String id;
	private String oldpassword;
	private String password;
	private Map changeStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(Map changeStatus) {
		this.changeStatus = changeStatus;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String login(){
		Map<String, String> session=(Map)ActionContext.getContext().get(ActionContext.SESSION);  
		AdminService as = new AdminService();
		Admin a = as.checkLogin(this.getAdmin().getUsername(), this.getAdmin().getPassword());
		if (a!=null){
			session.put("username", a.getUsername());
			session.put("userid",a.getId().toString());
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
	
	public String changePwd(){
		AdminService as = new AdminService();
		Map m = as.changePwd(Long.parseLong(this.getId()),this.getOldpassword(), this.getPassword());
		this.setChangeStatus(m); 
		return "success";
	}
	

	
}
