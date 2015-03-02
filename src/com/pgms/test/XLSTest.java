package com.pgms.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.junit.Test;

public class XLSTest {

	public void xlstest() throws BiffException, IOException{
		String filepath="C:\\Users\\Administrator\\Desktop\\专业学位统计数据\\非全日制\\2011级.xls";
		System.out.println(filepath);
		Workbook workbook = Workbook.getWorkbook(new File(filepath));
		Sheet[] sheets = workbook.getSheets();
		System.out.println(sheets.length);
		String str_sheets="";
		for (int i =0;i<sheets.length;i++){
			System.out.println(sheets[i].getName());
			str_sheets=str_sheets+sheets[i].getName()+";";
		}
		Map m = new HashMap();
		m.put("sheets", str_sheets);
		workbook.close();
	}
	
	public void test(){
		for (int i=0;i<100;i++){
			if (i>5){
				continue;
			}
			System.out.println(i);
		}
	}


	public void writeXls() throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File("E:\\MyEclipse Professional 2014\\.metadata\\.me_tcat\\webapps\\pgms\\xls_template\\tjb_template1y.xls"));
		WritableWorkbook wwb =  wb.createWorkbook(new File("E:\\MyEclipse Professional 2014\\.metadata\\.me_tcat\\webapps\\pgms\\xls_tj\\tjb2.xls"), wb);
		WritableSheet ws = wwb.getSheet("按学院");
		CellFormat cf = ws.getCell(1, 5).getCellFormat();
		Label t=null;
		t=new Label(9,3,"15级");
		t.setCellFormat(cf);

		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setFont(arg0);

		ws.addCell(new Label(9,3,"15级"));
		ws.addCell(new Label(0,1,"统计时间：鬼知道"));
		wwb.write();
		wwb.close();
	}
	@Test
	public void whiletest(){
		
		Map m = new HashMap();
		m.put("a1", 1);
		m.put("a2", 2);
		
		int b = (Integer)m.get("a1")+(Integer)m.get("a2");
System.out.println(b);
	}
	
	
	
}
