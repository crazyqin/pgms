package com.pgms.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pgms.bean.Admin;
import com.pgms.bean.College;
import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.bean.Student;
import com.pgms.dao.AdminDao;
import com.pgms.dao.CollegeDao;
import com.pgms.dao.DegreeDao;
import com.pgms.dao.FieldDao;
import com.pgms.dao.StudentDao;
import com.pgms.util.HibernateSessionFactory;

public class DBTest {

	
	public void DBconnect(){
		HibernateSessionFactory.getSession();
	}
	

	@Test
	public void DBTest_Admin(){
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("admin");
		AdminDao ad = new AdminDao();
		ad.add(admin);
		System.out.println("Admin added finished");
		
		List<Admin> l = ad.findByUsername_Password("admin", "adm1in");
		if (l.isEmpty()){
			System.out.println("Wrong username or password");
		}else{
			System.out.println("Admin Passed");
		}
		System.out.println("Admin checked ok");
	}
	
}



