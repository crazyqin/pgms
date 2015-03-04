package com.pgms.action;

import java.util.Map;

import com.pgms.bean.Field;
import com.pgms.service.FieldService;
import com.pgms.service.StudentService;

public class FieldAction {
	private boolean _search;
	private int rows;
	private int page;
	private String sidx;
	private String sord;

	private String filters;

	private String oper;
	private String id;

	private Map allField;
	private Map actionStatus;

	//add&edit
	private String field_code;
	private String field_name;
	
	
	
	public String getField_code() {
		return field_code;
	}

	public void setField_code(String field_code) {
		this.field_code = field_code;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
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

	public Map getAllField() {
		return allField;
	}

	public void setAllField(Map allField) {
		this.allField = allField;
	}

	public Map getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(Map actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String field_search() {
		FieldService fs = new FieldService();

		this.setAllField(fs.field_search(rows, page, sord, _search, filters));

		return "success";
	}

	public String doAction() {
		FieldService fs = new FieldService();
		if (oper.equals("del")) {
			String[] s_ids = id.split(",");
			for (int i = 0; i < s_ids.length; i++) {
				fs.delField(s_ids[i]);
			}
		} else if (oper.equals("edit")) {
			Field field = new Field();
			field.setField_code(this.getField_code());
			field.setField_name(this.getField_name());
			fs.updateField(id, field);
		} else if (oper.equals("add")) {
			Field field = new Field();
			field.setField_code(this.getField_code());
			field.setField_name(this.getField_name());
			fs.addField(field);
		}

		return "success";
	}

}
