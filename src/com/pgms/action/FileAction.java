package com.pgms.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.pgms.service.FileService;
import com.pgms.service.StudentService;
import com.pgms.util.XLSUtil;

public class FileAction {
	private File xlsfile;
	private String xlsfileFileName;
	private String processXlsFileName;
	private String processSheets;
	
	private String export_sortby;
	private String export_years;
	
	private Map uploadStatus;
	private Map sheets;
	private Map importStatus;
	private Map exportStatus;

	
	public File getXlsfile() {
		return xlsfile;
	}


	public void setXlsfile(File xlsfile) {
		this.xlsfile = xlsfile;
	}


	public String getXlsfileFileName() {
		return xlsfileFileName;
	}


	public void setXlsfileFileName(String xlsfileFileName) {
		this.xlsfileFileName = xlsfileFileName;
	}


	public Map getUploadStatus() {
		return uploadStatus;
	}


	public void setUploadStatus(Map uploadStatus) {
		this.uploadStatus = uploadStatus;
	}


	public String getProcessXlsFileName() {
		return processXlsFileName;
	}


	public void setProcessXlsFileName(String processXlsFileName) {
		this.processXlsFileName = processXlsFileName;
	}


	public Map getSheets() {
		return sheets;
	}


	public void setSheets(Map sheets) {
		this.sheets = sheets;
	}


	public Map getImportStatus() {
		return importStatus;
	}


	public void setImportStatus(Map importStatus) {
		this.importStatus = importStatus;
	}


	public String getProcessSheets() {
		return processSheets;
	}


	public void setProcessSheets(String processSheets) {
		this.processSheets = processSheets;
	}


	public Map getExportStatus() {
		return exportStatus;
	}


	public void setExportStatus(Map exportStatus) {
		this.exportStatus = exportStatus;
	}


	public String getExport_sortby() {
		return export_sortby;
	}


	public void setExport_sortby(String export_sortby) {
		this.export_sortby = export_sortby;
	}


	public String getExport_years() {
		return export_years;
	}


	public void setExport_years(String export_years) {
		this.export_years = export_years;
	}


	public String uploadExcel() throws IOException{
		String realpath = ServletActionContext.getServletContext().getRealPath("/xls");
		Map m = new HashMap();
		m.put("status", "0");
		if (xlsfile!=null){
			File savefile = new File(new File(realpath),xlsfileFileName);
			if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
			FileUtils.copyFile(xlsfile, savefile);
			ActionContext.getContext().put("message", "上传成功");
			m.put("status", "1");
			m.put("filename", xlsfileFileName);
		}
		this.setUploadStatus(m);
		return "success";
	}
	
	public String sheetsFromExcel() throws BiffException, IOException{
		String realpath = ServletActionContext.getServletContext().getRealPath("/xls");
		String filepath=realpath+"\\"+this.getProcessXlsFileName();
		XLSUtil xlsutil = new XLSUtil();
		Map m = xlsutil.getSheets(filepath);
		this.setSheets(m);
		return "success";
	}
	
	public String xlsImport() throws BiffException, IOException{
		System.out.println(this.getProcessXlsFileName());
		String realpath = ServletActionContext.getServletContext().getRealPath("/xls");
		String filepath=realpath+"\\"+this.getProcessXlsFileName();
		XLSUtil xlsutil = new XLSUtil();
		System.out.println(filepath);
		List data_l = xlsutil.getDateFromSheets(filepath, this.getProcessSheets());
		
		
		FileService fs = new FileService();
		Map m = fs.importFromList(data_l);
		//Map m = new HashMap();
		//m.put("status", 1);
		this.setImportStatus(m);
		return "success";
	}
	
	public String xlsExport() throws BiffException, WriteException, IOException{
		String tmp_realpath = ServletActionContext.getServletContext().getRealPath("/xls_template");
		String dst_realpath = ServletActionContext.getServletContext().getRealPath("/xls_tj");
		String p =this.getExport_years().replaceAll(",", "_")+this.getExport_sortby().replaceAll(",", "_")+".xls" ;
		String dst_filepath=dst_realpath+"\\"+p;
		XLSUtil xlsutil = new XLSUtil();
		String[] stb = this.getExport_sortby().split(",");
		String[] ys = this.getExport_years().split(",");
		Map m = xlsutil.genXlsFromDate(tmp_realpath, dst_filepath, stb, ys);
		this.setExportStatus(m);
		return "success";
	}

}
