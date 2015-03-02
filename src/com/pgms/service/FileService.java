package com.pgms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgms.bean.Student;
import com.pgms.dao.StudentDao;

public class FileService {
	public Map importFromList(List l){
		Map status = new HashMap();
		StudentDao sd = new StudentDao();
		
		
		for(int i=0;i<l.size();i++){
			Student s_tmp = (Student) l.get(i);
			sd.add(s_tmp);
		}
		status.put("status", 1);
		System.out.println("IMPORT FINISHED");
		return status;
	}
	
	public Map exportXls(){
		return null;
	}
}
