package com.pgms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgms.bean.Field;
import com.pgms.bean.Student;
import com.pgms.dao.FieldDao;
import com.pgms.dao.StudentDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FieldService {

	public Map field_search(int pageSize, int page, String sord,
			boolean _search, String filters) {
		FieldDao fd = new FieldDao();

		String hql = "From Field ";

		if (_search == true) {
			hql = hql + "where ";
			JSONObject jo = JSONObject.fromObject(filters);
			String groupOp = jo.getString("groupOp");
			JSONArray ja = jo.getJSONArray("rules");

			for (Object obj : ja) {
				JSONObject rule = (JSONObject) obj;
				String field = rule.getString("field");
				String op = rule.getString("op");
				String data = rule.getString("data");

				if (op.equals("eq")) {
					hql = hql + field + "='" + data + "' ";
				} else if (op.equals("cn")) {
					hql = hql + field + " like '%" + data + "%' ";
				}
				hql = hql + " " + groupOp + " ";
			}
			hql = hql.substring(0, hql.length() - 5);
			System.out.println(hql);

		}

		if (sord.equals("asc")) {
			hql = hql + " order by field_code " + sord;
		} else {
			hql = hql + " order by field_code desc";
		}

		System.out.println(hql);

		List l = fd.searchField(pageSize * (page - 1), pageSize, hql);
		int count = fd.countSearchField(hql);

		Map m = new HashMap();
		m.put("rows", l);
		m.put("page", page);
		m.put("records", count);
		m.put("total", count % pageSize == 0 ? count / pageSize : count
				/ pageSize + 1);
		return m;
	}

	public Map delField(String field_code) {
		Map m = new HashMap();
		FieldDao fd = new FieldDao();
		try {
			fd.delete(fd.findByFieldCode(field_code));
			m.put("status", 1);
			m.put("msg", "删除成功");
		} catch (RuntimeException re) {
			m.put("status", 0);
			m.put("msg", "删除失败");
		}
		return m;

	}
	
	public Map addField(Field field){
		Map m = new HashMap();
		FieldDao fd = new FieldDao();
		try{
			fd.add(field);
			m.put("status", 1);
			m.put("msg", "添加成功");
		}catch (RuntimeException re) {
			m.put("status", 0);
			m.put("msg", "删除失败");
		}
		return m;
	}
	
	public Map updateField(String id,Field field){
		Map m = new HashMap();
		FieldDao fd = new FieldDao();
		//old field_code
		Field f = fd.findByFieldCode(id);
		
		//field_code not changed
		if(id.equals(field.getField_code())){
			f.setField_name(field.getField_name());
			fd.update(field);
			return m;
		}
		
		//field_code changed
		fd.add(field);
		StudentDao sd = new StudentDao();
		List<Student> stu_list = sd.findByFieldCode(id);
		for(Student obj:stu_list){
			obj.setField(field);
			sd.update(obj);
		}
		fd.delete(f);

		return m;
	}

}
