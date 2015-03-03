package com.pgms.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

public class FileDownloadAction {
	
	private String fileName;
	private String fileDSTName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileDSTName() {
		return fileDSTName;
	}

	public void setFileDSTName(String fileDSTName) {
		this.fileDSTName = fileDSTName;
	}

	public InputStream getInputStream() throws UnsupportedEncodingException{
		System.out.println(fileName);
		fileDSTName=new Date().toString()+".xls";
		return ServletActionContext.getServletContext().getResourceAsStream("/xls_tj/"+fileName);
	}
	
	 public String execute(){
		 return "success";
	 }
	

}
