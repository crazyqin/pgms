package com.pgms.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Field {
	private String field_code;
	private String field_name;
	
	
	@Id
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

}
