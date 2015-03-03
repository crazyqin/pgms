package com.pgms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgms.bean.Admin;
import com.pgms.dao.AdminDao;

public class AdminService {
	public Admin checkLogin(String username,String password){
		AdminDao ad = new AdminDao();
		List<Admin> l = ad.findByUsername_Password(username, password);
		if (l.isEmpty()){
			return null;
		}else{
			return l.get(0);
		}
	}
	
	public Map changePwd(Long id,String oldpassword,String password){
		AdminDao ad = new AdminDao();
		Map m = new HashMap();
		try{
			Admin admin = ad.findById(id);
			if (admin.getPassword().equals(oldpassword)){
				admin.setPassword(password);
				ad.update(admin);
				m.put("status", 1);
				m.put("msg", "修改成功");
			}else{
				m.put("status", 0);
				m.put("msg", "修改失败：原密码错误");
			}
		}catch(RuntimeException re){
			m.put("status", 0);
			m.put("msg", "修改失败");
		}
		return m;
	}

}
