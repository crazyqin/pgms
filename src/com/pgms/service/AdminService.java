package com.pgms.service;

import java.util.List;

import com.pgms.bean.Admin;
import com.pgms.dao.AdminDao;

public class AdminService {
	public Boolean checkLogin(String username,String password){
		AdminDao ad = new AdminDao();
		List<Admin> l = ad.findByUsername_Password(username, password);
		if (l.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
