package com.pgms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.bean.Student;
import com.pgms.dao.DegreeDao;
import com.pgms.dao.FieldDao;
import com.pgms.dao.StudentDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DegreeService {

	public Map degree_search(int pageSize, int page, String sord,
			boolean _search, String filters) {
		DegreeDao  dd = new DegreeDao();

		String hql = "From Degree ";

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
			hql = hql + " order by degree_code " + sord;
		} else {
			hql = hql + " order by degree_code desc";
		}

		System.out.println(hql);

		List l = dd.searchDegree(pageSize * (page - 1), pageSize, hql);
		int count = dd.countSearchDegree(hql);

		Map m = new HashMap();
		m.put("rows", l);
		m.put("page", page);
		m.put("records", count);
		m.put("total", count % pageSize == 0 ? count / pageSize : count
				/ pageSize + 1);
		return m;
	}

	public Map delDegree(String degree_code) {
		Map m = new HashMap();
		DegreeDao  dd = new DegreeDao();
		try {
			dd.delete(dd.findByDegreeCode(degree_code));
			m.put("status", 1);
			m.put("msg", "删除成功");
		} catch (RuntimeException re) {
			m.put("status", 0);
			m.put("msg", "删除失败");
		}
		return m;

	}
	
	public Map addDegree(Degree degree){
		Map m = new HashMap();
		DegreeDao  dd = new DegreeDao();
		try{
			dd.add(degree);
			m.put("status", 1);
			m.put("msg", "添加成功");
		}catch (RuntimeException re) {
			m.put("status", 0);
			m.put("msg", "删除失败");
		}
		return m;
	}
	
	public Map updateDegree(String id,Degree degree){
		Map m = new HashMap();
		DegreeDao  dd = new DegreeDao();
		//old degree_code
		Degree d = dd.findByDegreeCode(id);
		
		//field_code not changed
		if(id.equals(degree.getDegree_code())){
			d.setDegree_type(degree.getDegree_type());
			dd.update(degree);
			return m;
		}
		
		//field_code changed
		dd.add(degree);
		StudentDao sd = new StudentDao();
		List<Student> stu_list = sd.findByDegreeType(id);
		for(Student obj:stu_list){
			obj.setDegree(degree);
			sd.update(obj);
		}
		dd.delete(d);

		return m;
	}

}
