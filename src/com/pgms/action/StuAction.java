package com.pgms.action;

import java.util.Map;

import com.pgms.service.StudentService;

public class StuAction {
	private boolean _search;
	private int rows;
	private int page;
	private String sidx;
	private String sord;

	
	private String filters;


	private String oper;
	private String id;
	
	private Map allStu;
	private Map actionStatus;
	



	public Map getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(Map actionStatus) {
		this.actionStatus = actionStatus;
	}

	public Map getAllStu() {
		return allStu;
	}

	public void setAllStu(Map allStu) {
		this.allStu = allStu;
	}

	public boolean is_search() {
		return _search;
	}

	public void set_search(boolean _search) {
		this._search = _search;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
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

	public String stu_search() {
		StudentService ss = new StudentService();

		this.setAllStu(ss.stu_search(rows, page, sord,_search,filters));

		return "success";
	}
	
	public String doAction(){
		StudentService ss = new StudentService();
		if (oper.equals("del")){
			String[] s_ids=id.split(",");
			for (int i=0;i<s_ids.length;i++){
				ss.delStu(Long.parseLong(s_ids[i]));
			}
		}else if(oper.equals("edit")){
			
		}else if(oper.equals("add")){
			
		}
		
		

		
		return "success";
	}

}
