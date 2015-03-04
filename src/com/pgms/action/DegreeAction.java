package com.pgms.action;

import java.util.Map;

import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.service.DegreeService;
import com.pgms.service.FieldService;
import com.pgms.service.StudentService;

public class DegreeAction {
	private boolean _search;
	private int rows;
	private int page;
	private String sidx;
	private String sord;

	private String filters;

	private String oper;
	private String id;

	private Map allDegree;
	private Map actionStatus;

	//add&edit
	private String degree_code;
	private String degree_type;
	
	

	
	public Map getAllDegree() {
		return allDegree;
	}

	public void setAllDegree(Map allDegree) {
		this.allDegree = allDegree;
	}

	public String getDegree_code() {
		return degree_code;
	}

	public void setDegree_code(String degree_code) {
		this.degree_code = degree_code;
	}

	public String getDegree_type() {
		return degree_type;
	}

	public void setDegree_type(String degree_type) {
		this.degree_type = degree_type;
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

	public String degree_search() {
		DegreeService ds = new DegreeService();

		this.setAllDegree(ds.degree_search(rows, page, sord, _search, filters));

		return "success";
	}

	public String doAction() {
		DegreeService ds = new DegreeService();
		if (oper.equals("del")) {
			String[] s_ids = id.split(",");
			for (int i = 0; i < s_ids.length; i++) {
				ds.delDegree(s_ids[i]);
			}
		} else if (oper.equals("edit")) {
			Degree degree = new Degree();
			degree.setDegree_code(this.getDegree_code());
			degree.setDegree_type(this.getDegree_type());
			ds.updateDegree(id, degree);
		} else if (oper.equals("add")) {
			Degree degree = new Degree();
			degree.setDegree_code(this.getDegree_code());
			degree.setDegree_type(this.getDegree_type());
			ds.addDegree(degree);
		}

		return "success";
	}

}
