package com.pgms.action;

import java.util.Map;

import com.pgms.bean.College;
import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.service.CollegeService;
import com.pgms.service.DegreeService;
import com.pgms.service.FieldService;
import com.pgms.service.StudentService;

public class CollegeAction {
	private boolean _search;
	private int rows;
	private int page;
	private String sidx;
	private String sord;

	private String filters;

	private String oper;
	private String id;

	private Map allCollege;
	private Map actionStatus;

	//add&edit
	private String college_name;
	
	

	

	public Map getAllCollege() {
		return allCollege;
	}

	public void setAllCollege(Map allCollege) {
		this.allCollege = allCollege;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	public boolean is_search() {
		return _search;
	}

	public void set_search(boolean _search) {
		this._search = _search;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public Map getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(Map actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String college_search() {
		CollegeService cs = new CollegeService();

		this.setAllCollege(cs.college_search(rows, page, sord, _search, filters));

		return "success";
	}

	public String doAction() {
		CollegeService cs = new CollegeService();
		if (oper.equals("del")) {
			String[] s_ids = id.split(",");
			for (int i = 0; i < s_ids.length; i++) {
				cs.delCollege(s_ids[i]);
			}
		} else if (oper.equals("edit")) {
			College college = new College();
			cs.updateCollege(id, this.getCollege_name());
		} else if (oper.equals("add")) {
			College college = new College();
			college.setCollege_name(this.getCollege_name());
			cs.addCollege(college);
		}

		return "success";
	}

}
