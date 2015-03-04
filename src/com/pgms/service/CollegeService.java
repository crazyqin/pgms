package com.pgms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgms.bean.College;
import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.bean.Student;
import com.pgms.dao.CollegeDao;
import com.pgms.dao.DegreeDao;
import com.pgms.dao.FieldDao;
import com.pgms.dao.StudentDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CollegeService {

	public Map college_search(int pageSize, int page, String sord,
			boolean _search, String filters) {
		CollegeDao cd = new CollegeDao();

		String hql = "From College ";

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
			hql = hql + " order by id " + sord;
		} else {
			hql = hql + " order by id desc";
		}

		System.out.println(hql);

		List l = cd.searchCollege(pageSize * (page - 1), pageSize, hql);
		int count = cd.countSearchCollege(hql);

		Map m = new HashMap();
		m.put("rows", l);
		m.put("page", page);
		m.put("records", count);
		m.put("total", count % pageSize == 0 ? count / pageSize : count
				/ pageSize + 1);
		return m;
	}

	public Map delCollege(String id) {
		Map m = new HashMap();
		CollegeDao cd = new CollegeDao();
		try {
			cd.delete(cd.findById(Long.parseLong(id)));
			m.put("status", 1);
			m.put("msg", "删除成功");
		} catch (RuntimeException re) {
			m.put("status", 0);
			m.put("msg", "删除失败");
		}
		return m;

	}

	public Map addCollege(College college) {
		Map m = new HashMap();
		CollegeDao cd = new CollegeDao();
		try {
			cd.add(college);
			m.put("status", 1);
			m.put("msg", "添加成功");
		} catch (RuntimeException re) {
			m.put("status", 0);
			m.put("msg", "删除失败");
		}
		return m;
	}

	public Map updateCollege(String id,String college_name) {
		Map m = new HashMap();
		CollegeDao cd = new CollegeDao();

		College c = cd.findById(Long.parseLong(id));

		c.setCollege_name(college_name);
		cd.update(c);

		return m;
	}


}
