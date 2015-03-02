package com.pgms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.pgms.bean.Student;
import com.pgms.dao.StudentDao;

public class StudentService {
	
	public Map stu_search(int pageSize,int page,String sord,boolean _search,String filters){
		StudentDao sd = new StudentDao();
		
		String hql="From Student ";
		
		
		if (_search==true){
			hql=hql+"where ";
			JSONObject jo = JSONObject.fromObject(filters);
			String groupOp=jo.getString("groupOp");
			JSONArray ja = jo.getJSONArray("rules");
			
			for (Object obj:ja){
				JSONObject rule = (JSONObject) obj;
				String field = rule.getString("field");
				String op = rule.getString("op");
				String data = rule.getString("data");
				
				if (op.equals("eq")){
					hql=hql+field+"='"+data+"' ";
				}else if(op.equals("cn")){
					hql=hql+field+" like '%"+data+"%' ";
				}
				hql=hql+" "+groupOp+" ";
			}
			hql=hql.substring(0, hql.length()-5);
			System.out.println(hql);
			
			
			
		}

		
		
		if (sord.equals("asc")){
			hql=hql+" order by id "+sord;
		}else{
			hql=hql+" order by id desc";
		}
		
		System.out.println(hql);
		

		List l = sd.searchStu(pageSize*(page-1), pageSize, hql);
		int count=sd.countSearchStu(hql);
		
		
		Map m = new HashMap();
		m.put("rows", l);
		m.put("page", page);
		m.put("records", count);
		m.put("total",  count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
		return m;
	}

}
