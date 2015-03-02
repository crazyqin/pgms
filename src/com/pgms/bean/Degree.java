package com.pgms.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Degree {
	private String degree_code;
	private String degree_type;
	
	
	@Id
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
	

}
