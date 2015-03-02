package com.pgms.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	private Long id;
	private String stu_no;
	private String stu_name;
	private String sex;
	private float stu_xz; //学制
	private String stu_pylb; //培养类别
	private String stu_mz;//民族
	private String stu_birth;
	private String stu_zjmc;//证件类型
	private String stu_zjhm;//证件号码
	private String stu_zzmm;//政治面貌
	private String stu_detail;
	
	private Field field;//领域
	private Degree degree;//学位类别
	private College college;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStu_no() {
		return stu_no;
	}
	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	public float getStu_xz() {
		return stu_xz;
	}
	public void setStu_xz(float stu_xz) {
		this.stu_xz = stu_xz;
	}
	public String getStu_pylb() {
		return stu_pylb;
	}
	public void setStu_pylb(String stu_pylb) {
		this.stu_pylb = stu_pylb;
	}
	public String getStu_mz() {
		return stu_mz;
	}
	public void setStu_mz(String stu_mz) {
		this.stu_mz = stu_mz;
	}
	public String getStu_birth() {
		return stu_birth;
	}
	public void setStu_birth(String stu_birth) {
		this.stu_birth = stu_birth;
	}
	public String getStu_zjmc() {
		return stu_zjmc;
	}
	public void setStu_zjmc(String stu_zjmc) {
		this.stu_zjmc = stu_zjmc;
	}
	public String getStu_zjhm() {
		return stu_zjhm;
	}
	public void setStu_zjhm(String stu_zjhm) {
		this.stu_zjhm = stu_zjhm;
	}
	public String getStu_zzmm() {
		return stu_zzmm;
	}
	public void setStu_zzmm(String stu_zzmm) {
		this.stu_zzmm = stu_zzmm;
	}
	@ManyToOne
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	@ManyToOne
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	
	@Lob
	public String getStu_detail() {
		return stu_detail;
	}
	public void setStu_detail(String stu_detail) {
		this.stu_detail = stu_detail;
	}
	
	@ManyToOne
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	
	
	
	

}
