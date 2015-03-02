package com.pgms.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgms.bean.College;
import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.bean.Student;
import com.pgms.dao.CollegeDao;
import com.pgms.dao.DegreeDao;
import com.pgms.dao.FieldDao;
import com.pgms.dao.StudentDao;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class XLSUtil {
	

	public Map getSheets(String filepath) throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(new File(filepath));
		Sheet[] sheets = workbook.getSheets();
		String str_sheets = "";
		for (int i = 0; i < sheets.length; i++) {
			str_sheets = str_sheets + sheets[i].getName() + ";";
		}
		str_sheets = str_sheets.substring(0, str_sheets.length() - 1);
		workbook.close();
		Map m = new HashMap();
		m.put("sheets", str_sheets);
		return m;
	}

	public List getDateFromSheets(String filepath, String sheets)
			throws BiffException, IOException {
		List data_l = new ArrayList();
		Workbook workbook = Workbook.getWorkbook(new File(filepath));
		String[] sheet = sheets.split(",");
		for (int i = 0; i < sheet.length; i++) {
			int stu_college_col = -1;
			int stu_no_col = -1;
			int stu_name_col = -1;
			int stu_sex_col = -1;
			int stu_xz_col = -1;
			int stu_pylb_col = -1;
			int stu_mz_col = -1;
			int stu_birth_col = -1;
			int stu_zjlx_col = -1;
			int stu_zjhm_col = -1;
			int stu_zzmm_col = -1;
			int stu_lydm_col = -1;
			int stu_lymc_col = -1;
			int stu_xwlbdm_col = -1;
			int stu_xwlbmc_col = -1;

			Sheet st = workbook.getSheet(sheet[i]);
			System.out.println(sheet[i]);
			int col_total = st.getColumns();
			for (int j = 0; j < col_total; j++) {
				if (st.getCell(j, 0).getContents().equals("yxsmc")
						|| st.getCell(j, 0).getContents().equals("ssyxmc")
						|| st.getCell(j, 0).getContents().equals("学院")
						|| st.getCell(j, 0).getContents().equals("学院名称")
						|| st.getCell(j, 0).getContents().equals("lqyxsmc")) {
					stu_college_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("xh")
						|| st.getCell(j, 0).getContents().equals("学号")) {
					stu_no_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("xm")
						|| st.getCell(j, 0).getContents().equals("姓名")) {
					stu_name_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("性别")
						|| st.getCell(j, 0).getContents().equals("sex")
						|| st.getCell(j, 0).getContents().equals("xb")
						|| st.getCell(j, 0).getContents().equals("xbmc")
						|| st.getCell(j, 0).getContents().equals("xbm")) {
					stu_sex_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("学制")
						|| st.getCell(j, 0).getContents().equals("录取学制")
						|| st.getCell(j, 0).getContents().equals("学制（年）")) {
					stu_xz_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("培养类别")
						|| st.getCell(j, 0).getContents().equals("lqlbm")) {
					stu_pylb_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("民族")
						|| st.getCell(j, 0).getContents().equals("mzmc")) {
					stu_mz_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("出生日期")
						|| st.getCell(j, 0).getContents().equals("csrq")) {
					stu_birth_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("证件类型")
						|| st.getCell(j, 0).getContents().equals("zjlx")) {
					stu_zjlx_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("证件号码")
						|| st.getCell(j, 0).getContents().equals("zjhm")) {
					stu_zjhm_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("政治面貌")
						|| st.getCell(j, 0).getContents().equals("zzmm")
						|| st.getCell(j, 0).getContents().equals("zzmmmc")) {
					stu_zzmm_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("领域代码")
						|| st.getCell(j, 0).getContents().equals("lyzydm")
						|| st.getCell(j, 0).getContents().equals("lqlydm")
						|| st.getCell(j, 0).getContents().equals("录取专业代码")
						|| st.getCell(j, 0).getContents().equals("zylydm")
						|| st.getCell(j, 0).getContents().equals("lqzydm")) {
					stu_lydm_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("lqlymc")
						|| st.getCell(j, 0).getContents().equals("录取专业名称")
						|| st.getCell(j, 0).getContents().equals("zylymc")
						|| st.getCell(j, 0).getContents().equals("领域名称")
						|| st.getCell(j, 0).getContents().equals("lqzymc")) {
					stu_lymc_col = j;
				}

				if (st.getCell(j, 0).getContents().equals("lqlbdm")
						|| st.getCell(j, 0).getContents().equals("类别代码")
						|| st.getCell(j, 0).getContents().equals("ssxwlbdm")) {
					stu_xwlbdm_col = j;
				}
				if (st.getCell(j, 0).getContents().equals("类别名称")
						|| st.getCell(j, 0).getContents().equals("lqlbmc")
						|| st.getCell(j, 0).getContents().equals("ssxwlbmc")
						|| st.getCell(j, 0).getContents().equals("学位类别")
						|| st.getCell(j, 0).getContents().equals("学科名称")) {
					stu_xwlbmc_col = j;
				}

			}

			int row_total = st.getRows();
			for (int k = 1; k < row_total; k++) {
				try {
					Student s_tmp = new Student();
					if (st.getCell(stu_no_col, k).getContents().equals("")) {
						continue;
					}
					s_tmp.setStu_no(st.getCell(stu_no_col, k).getContents());
					//System.out.println(s_tmp.getStu_no());
					s_tmp.setStu_name(st.getCell(stu_name_col, k).getContents());

					if (stu_college_col != -1) {
						College c_tmp = new College();
						CollegeDao cd = new CollegeDao();
						c_tmp = cd.findByCollegeName(st.getCell(
								stu_college_col, k).getContents());
						if (c_tmp == null) {
							c_tmp = new College();
							c_tmp.setCollege_name(st
									.getCell(stu_college_col, k).getContents());
							cd.add(c_tmp);
						}
						s_tmp.setCollege(c_tmp);
					}

					if (stu_sex_col != -1)
						s_tmp.setSex(st.getCell(stu_sex_col, k).getContents());

					if (stu_xz_col != -1) {
						if (!st.getCell(stu_xz_col, k).getContents().equals("")) {
							s_tmp.setStu_xz(Float.parseFloat(st.getCell(
									stu_xz_col, k).getContents()));
						}
					}

					if (stu_pylb_col != -1){
						if (st.getCell(stu_pylb_col, k).getContents().equals("11")){
							s_tmp.setStu_pylb("非定向");
						}else if(st.getCell(stu_pylb_col, k).getContents().equals("12")){
							s_tmp.setStu_pylb("定向");
						}else if(st.getCell(stu_pylb_col, k).getContents().equals("24")){
							s_tmp.setStu_pylb("自筹");
						}else if (st.getCell(stu_pylb_col, k).getContents().equals("23")){
							s_tmp.setStu_pylb("委培");
						}else{
							s_tmp.setStu_pylb(st.getCell(stu_pylb_col, k).getContents());
						}
					}
				

					if (stu_mz_col != -1)
						s_tmp.setStu_mz(st.getCell(stu_mz_col, k).getContents());

					if (stu_birth_col != -1)
						s_tmp.setStu_birth(st.getCell(stu_birth_col, k)
								.getContents());

					if (stu_zjlx_col != -1)
						s_tmp.setStu_zjmc(st.getCell(stu_zjlx_col, k)
								.getContents());

					if (stu_zjhm_col != -1)
						s_tmp.setStu_zjhm(st.getCell(stu_zjhm_col, k)
								.getContents());

					if (stu_zzmm_col != -1)
						s_tmp.setStu_zzmm(st.getCell(stu_zzmm_col, k)
								.getContents());

					if (stu_lydm_col != -1 && stu_lymc_col != -1) {
						if (!st.getCell(stu_lydm_col, k).getContents()
								.equals("X")) {
							Field f_tmp = new Field();
							FieldDao fd = new FieldDao();
							f_tmp.setField_code(st.getCell(stu_lydm_col, k)
									.getContents());
							f_tmp.setField_name(st.getCell(stu_lymc_col, k)
									.getContents());
							fd.addorupdate(f_tmp);
							s_tmp.setField(f_tmp);
						}else{
							Field f_tmp = new Field();
							FieldDao fd = new FieldDao();
							f_tmp.setField_code("000000");
							f_tmp.setField_name("无专业领域");
							fd.addorupdate(f_tmp);
							s_tmp.setField(f_tmp);
						}
					}

					if (stu_xwlbdm_col != -1 && stu_xwlbmc_col != -1) {
						DegreeDao dd = new DegreeDao();
						Degree d_tmp = new Degree();
						d_tmp.setDegree_code(st.getCell(stu_xwlbdm_col, k)
								.getContents());
						d_tmp.setDegree_type(st.getCell(stu_xwlbmc_col, k)
								.getContents());
						dd.addorupdate(d_tmp);
						s_tmp.setDegree(d_tmp);
					}

					if (stu_xwlbdm_col == -1 && stu_xwlbmc_col != -1
							&& stu_lydm_col != -1) {
						DegreeDao dd = new DegreeDao();
						Degree d_tmp = new Degree();
						d_tmp.setDegree_code(st.getCell(stu_lydm_col, k)
								.getContents().substring(0, 4));
						d_tmp.setDegree_type(st.getCell(stu_xwlbmc_col, k)
								.getContents());
						dd.addorupdate(d_tmp);
						s_tmp.setDegree(d_tmp);

					}

					s_tmp.setStu_detail("妈蛋 太烦了 不干了。");

					// StudentDao sd = new StudentDao();
					// sd.add(s_tmp);
					data_l.add(s_tmp);
				} catch (RuntimeException re) {
					continue;
				}

			}

		}

		return data_l;
	}

	//years:11  12
	public Map genXlsFromDate(String src_dirpath,String dst_filepath,String[] sortby,String[] years) throws IOException, BiffException, WriteException{
		int year_num = years.length;
		String src_filepath = src_dirpath+"\\tjb_template"+year_num+"y.xls";
		Workbook wb = Workbook.getWorkbook(new File(src_filepath));
		WritableWorkbook wwb =  wb.createWorkbook(new File(dst_filepath), wb);
		if (year_num==1){
			List<Map> final_data = new ArrayList();
			for (int i = 0 ;i<sortby.length;i++){
				if (sortby[i].equals("按学院")){
					CollegeDao cd = new CollegeDao();
					List<College> college_list = cd.viewAllCollege();
					
					for (int j=0;j<college_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;
						Map m = new HashMap();
						m.put("collegeName", college_list.get(j).getCollege_name());
						
						StudentDao sd = new StudentDao();
						List<Student> stu_list = sd.findByCollegeName(college_list.get(j).getCollege_name());
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);
						
						final_data.add(m);
					}
					
					
					//写入sheet 按学院
	
			        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按学院");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(1,5,years[0]+"级"));
					ws.addCell(new Label(3,5,years[0]+"级"));
					ws.addCell(new Label(5,5,years[0]+"级"));
					ws.addCell(new Label(7,5,years[0]+"级"));
					ws.addCell(new Label(9,3,years[0]+"级"));
					ws.addCell(new Label(12,3,years[0]+"级"));
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("collegeName"),wcf));
						ws.addCell(new Number(1,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(3,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(5,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(9,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_m"))
								+(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_m"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_m"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_m"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
									
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));	
						ws.addCell(new Number(12,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));
						ws.addCell(new Number(14,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f"),wcf));
						
			
					}
					for (int k=0;k<46-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
				final_data.clear();
				if (sortby[i].equals("按学位类别")){
					
					DegreeDao dd = new DegreeDao();
					List<String> degree_list = dd.viewAllUniqueDegree();
					for(int j=0;j<degree_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;
						Map m = new HashMap();
						m.put("degreeType", degree_list.get(j));
						
						StudentDao sd = new StudentDao();
						List<Student> stu_list = sd.findByDegreeType(degree_list.get(j));
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);
						
						final_data.add(m);
					}
					//写入sheet 按学位类别
					DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按学位类别");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(1,5,years[0]+"级"));
					ws.addCell(new Label(3,5,years[0]+"级"));
					ws.addCell(new Label(5,5,years[0]+"级"));
					ws.addCell(new Label(7,5,years[0]+"级"));
					ws.addCell(new Label(9,3,years[0]+"级"));
					ws.addCell(new Label(12,3,years[0]+"级"));
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("degreeType"),wcf));
						ws.addCell(new Number(1,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(3,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(5,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(9,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_m"))
								+(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_m"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_m"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_m"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
									
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));	
						ws.addCell(new Number(12,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));
						ws.addCell(new Number(14,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f"),wcf));
						
			
					}
					for (int k=0;k<24-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
				final_data.clear();
				if (sortby[i].equals("按领域")){
					
					FieldDao fd = new FieldDao();
					List<Field> field_list = fd.viewAllField();
					for(int j=0;j<field_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;
						Map m = new HashMap();
						m.put("fieldCode", field_list.get(j).getField_code());
						m.put("fieldName",field_list.get(j).getField_name());
						
						StudentDao sd = new StudentDao();
						
						m.put("collegeName",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getCollege().getCollege_name());
						m.put("degreeType",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getDegree().getDegree_type());
						m.put("stu_xz",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getStu_xz());
						

						
						
						List<Student> stu_list = sd.findByFieldCode(field_list.get(j).getField_code());
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);
						
						final_data.add(m);
					}
					//写入sheet 按领域
					DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按领域");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(5,5,years[0]+"级"));
					ws.addCell(new Label(7,5,years[0]+"级"));
					ws.addCell(new Label(9,5,years[0]+"级"));
					ws.addCell(new Label(11,5,years[0]+"级"));
					ws.addCell(new Label(13,3,years[0]+"级"));
					ws.addCell(new Label(16,3,years[0]+"级"));
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("collegeName"),wcf));
						ws.addCell(new Label(1,6+j, (String) data_m.get("degreeType"),wcf));
						ws.addCell(new Label(2,6+j, (String) data_m.get("fieldCode"),wcf));
						ws.addCell(new Label(3,6+j, (String) data_m.get("fieldName"),wcf));	
						ws.addCell(new Number(4,6+j,  Double.parseDouble( data_m.get("stu_xz").toString()),wcf));	
						ws.addCell(new Number(5,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(9,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(13,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_m"))
								+(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_m"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_m"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_m"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
									
						ws.addCell(new Number(15,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));	
						ws.addCell(new Number(16,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));
						ws.addCell(new Number(18,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f"),wcf));
						
			
					}
					for (int k=0;k<184-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
			}
			
		}
		
		if (year_num==2){
			List<Map> final_data = new ArrayList();
			for (int i = 0 ;i<sortby.length;i++){
				if (sortby[i].equals("按学院")){
					CollegeDao cd = new CollegeDao();
					List<College> college_list = cd.viewAllCollege();
					
					for (int j=0;j<college_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;

						int sum_fdx_year1_m=0;
						int sum_fdx_year1_f=0;
						int sum_dx_year1_m=0;
						int sum_dx_year1_f=0;
						int sum_wp_year1_m=0;
						int sum_wp_year1_f=0;
						int sum_zc_year1_m=0;
						int sum_zc_year1_f=0;
						int sum_Z_year1_m=0;
						int sum_Z_year1_f=0;

						Map m = new HashMap();
						m.put("collegeName", college_list.get(j).getCollege_name());
						
						StudentDao sd = new StudentDao();
						List<Student> stu_list = sd.findByCollegeName(college_list.get(j).getCollege_name());
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);



						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[1])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year1_m++;
									}else{
										sum_fdx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year1_m++;
									}else{
										sum_dx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year1_m++;
									}else{
										sum_wp_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year1_m++;
									}else{
										sum_zc_year1_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[1])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year1_m++;
								}else{
									sum_Z_year1_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year1_m", sum_fdx_year1_m);
						m.put("sum_fdx_year1_f", sum_fdx_year1_f);
						m.put("sum_dx_year1_m", sum_dx_year1_m);
						m.put("sum_dx_year1_f", sum_dx_year1_f);
						m.put("sum_wp_year1_m", sum_wp_year1_m);
						m.put("sum_wp_year1_f", sum_wp_year1_f);
						m.put("sum_zc_year1_m", sum_zc_year1_m);
						m.put("sum_zc_year1_f", sum_zc_year1_f);
						m.put("sum_Z_year1_m", sum_Z_year1_m);
						m.put("sum_Z_year1_f", sum_Z_year1_f);
						
						



						final_data.add(m);
					}
					
					
					//写入sheet 按学院
	
			        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按学院");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(1,5,years[0]+"级"));
					ws.addCell(new Label(2,5,years[1]+"级"));
					ws.addCell(new Label(4,5,years[0]+"级"));
					ws.addCell(new Label(5,5,years[1]+"级"));
					ws.addCell(new Label(7,5,years[0]+"级"));
					ws.addCell(new Label(8,5,years[1]+"级"));
					ws.addCell(new Label(10,5,years[0]+"级"));
					ws.addCell(new Label(11,5,years[1]+"级"));
					
					ws.addCell(new Label(13,3,years[0]+"级"));
					ws.addCell(new Label(14,3,years[1]+"级"));
					ws.addCell(new Label(17,3,years[0]+"级"));
					ws.addCell(new Label(18,3,years[1]+"级"));
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("collegeName"),wcf));
						ws.addCell(new Number(1,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(2,6+j,(Integer)(final_data.get(j).get("sum_fdx_year1_m"))+(Integer)( final_data.get(j).get("sum_fdx_year1_f")),wcf));
						ws.addCell(new Number(4,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(5,6+j,(Integer)( final_data.get(j).get("sum_dx_year1_m"))+(Integer)( final_data.get(j).get("sum_dx_year1_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(8,6+j,(Integer)( final_data.get(j).get("sum_wp_year1_m"))+(Integer)( final_data.get(j).get("sum_wp_year1_f")),wcf));
						ws.addCell(new Number(10,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_zc_year1_m"))+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));
						ws.addCell(new Number(16,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year1_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));	
						ws.addCell(new Number(17,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));
						ws.addCell(new Number(18,6+j,(Integer)( final_data.get(j).get("sum_Z_year1_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));

						ws.addCell(new Number(20,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f")
							+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));	
						
			
					}
					for (int k=0;k<46-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
				final_data.clear();
				if (sortby[i].equals("按学位类别")){
					
					DegreeDao dd = new DegreeDao();
					List<String> degree_list = dd.viewAllUniqueDegree();
					for(int j=0;j<degree_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;

						int sum_fdx_year1_m=0;
						int sum_fdx_year1_f=0;
						int sum_dx_year1_m=0;
						int sum_dx_year1_f=0;
						int sum_wp_year1_m=0;
						int sum_wp_year1_f=0;
						int sum_zc_year1_m=0;
						int sum_zc_year1_f=0;
						int sum_Z_year1_m=0;
						int sum_Z_year1_f=0;

						Map m = new HashMap();
						m.put("degreeType", degree_list.get(j));
						
						StudentDao sd = new StudentDao();
						List<Student> stu_list = sd.findByDegreeType(degree_list.get(j));
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[1])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year1_m++;
									}else{
										sum_fdx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year1_m++;
									}else{
										sum_dx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year1_m++;
									}else{
										sum_wp_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year1_m++;
									}else{
										sum_zc_year1_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[1])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year1_m++;
								}else{
									sum_Z_year1_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year1_m", sum_fdx_year1_m);
						m.put("sum_fdx_year1_f", sum_fdx_year1_f);
						m.put("sum_dx_year1_m", sum_dx_year1_m);
						m.put("sum_dx_year1_f", sum_dx_year1_f);
						m.put("sum_wp_year1_m", sum_wp_year1_m);
						m.put("sum_wp_year1_f", sum_wp_year1_f);
						m.put("sum_zc_year1_m", sum_zc_year1_m);
						m.put("sum_zc_year1_f", sum_zc_year1_f);
						m.put("sum_Z_year1_m", sum_Z_year1_m);
						m.put("sum_Z_year1_f", sum_Z_year1_f);
						
						final_data.add(m);
					}
					//写入sheet 按学位类别
					DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按学位类别");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(1,5,years[0]+"级"));
					ws.addCell(new Label(2,5,years[1]+"级"));
					ws.addCell(new Label(4,5,years[0]+"级"));
					ws.addCell(new Label(5,5,years[1]+"级"));
					ws.addCell(new Label(7,5,years[0]+"级"));
					ws.addCell(new Label(8,5,years[1]+"级"));
					ws.addCell(new Label(10,5,years[0]+"级"));
					ws.addCell(new Label(11,5,years[1]+"级"));
					
					ws.addCell(new Label(13,3,years[0]+"级"));
					ws.addCell(new Label(14,3,years[1]+"级"));
					ws.addCell(new Label(17,3,years[0]+"级"));
					ws.addCell(new Label(18,3,years[1]+"级"));
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("degreeType"),wcf));
						ws.addCell(new Number(1,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(2,6+j,(Integer)(final_data.get(j).get("sum_fdx_year1_m"))+(Integer)( final_data.get(j).get("sum_fdx_year1_f")),wcf));
						ws.addCell(new Number(4,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(5,6+j,(Integer)( final_data.get(j).get("sum_dx_year1_m"))+(Integer)( final_data.get(j).get("sum_dx_year1_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(8,6+j,(Integer)( final_data.get(j).get("sum_wp_year1_m"))+(Integer)( final_data.get(j).get("sum_wp_year1_f")),wcf));
						ws.addCell(new Number(10,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_zc_year1_m"))+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));
						ws.addCell(new Number(16,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year1_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));	
						ws.addCell(new Number(17,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));
						ws.addCell(new Number(18,6+j,(Integer)( final_data.get(j).get("sum_Z_year1_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));

						ws.addCell(new Number(20,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f")
							+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));	
						
			
					}
					for (int k=0;k<24-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
				final_data.clear();
				if (sortby[i].equals("按领域")){
					
					FieldDao fd = new FieldDao();
					List<Field> field_list = fd.viewAllField();
					for(int j=0;j<field_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;

						int sum_fdx_year1_m=0;
						int sum_fdx_year1_f=0;
						int sum_dx_year1_m=0;
						int sum_dx_year1_f=0;
						int sum_wp_year1_m=0;
						int sum_wp_year1_f=0;
						int sum_zc_year1_m=0;
						int sum_zc_year1_f=0;
						int sum_Z_year1_m=0;
						int sum_Z_year1_f=0;

						Map m = new HashMap();
						m.put("fieldCode", field_list.get(j).getField_code());
						m.put("fieldName",field_list.get(j).getField_name());
						
						StudentDao sd = new StudentDao();
						
						m.put("collegeName",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getCollege().getCollege_name());
						m.put("degreeType",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getDegree().getDegree_type());
						m.put("stu_xz",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getStu_xz());
						

						
						
						List<Student> stu_list = sd.findByFieldCode(field_list.get(j).getField_code());
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);


						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[1])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year1_m++;
									}else{
										sum_fdx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year1_m++;
									}else{
										sum_dx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year1_m++;
									}else{
										sum_wp_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year1_m++;
									}else{
										sum_zc_year1_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[1])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year1_m++;
								}else{
									sum_Z_year1_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year1_m", sum_fdx_year1_m);
						m.put("sum_fdx_year1_f", sum_fdx_year1_f);
						m.put("sum_dx_year1_m", sum_dx_year1_m);
						m.put("sum_dx_year1_f", sum_dx_year1_f);
						m.put("sum_wp_year1_m", sum_wp_year1_m);
						m.put("sum_wp_year1_f", sum_wp_year1_f);
						m.put("sum_zc_year1_m", sum_zc_year1_m);
						m.put("sum_zc_year1_f", sum_zc_year1_f);
						m.put("sum_Z_year1_m", sum_Z_year1_m);
						m.put("sum_Z_year1_f", sum_Z_year1_f);
						
						


						final_data.add(m);
					}
					//写入sheet 按领域
					DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按领域");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(5,5,years[0]+"级"));
					ws.addCell(new Label(5,5,years[1]+"级"));
					ws.addCell(new Label(8,5,years[0]+"级"));
					ws.addCell(new Label(9,5,years[1]+"级"));
					ws.addCell(new Label(11,5,years[0]+"级"));
					ws.addCell(new Label(12,5,years[1]+"级"));
					ws.addCell(new Label(14,5,years[0]+"级"));
					ws.addCell(new Label(15,5,years[1]+"级"));
					
					ws.addCell(new Label(17,3,years[0]+"级"));
					ws.addCell(new Label(18,3,years[1]+"级"));
					ws.addCell(new Label(21,3,years[0]+"级"));
					ws.addCell(new Label(22,3,years[1]+"级"));
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("collegeName"),wcf));
						ws.addCell(new Label(1,6+j, (String) data_m.get("degreeType"),wcf));
						ws.addCell(new Label(2,6+j, (String) data_m.get("fieldCode"),wcf));
						ws.addCell(new Label(3,6+j, (String) data_m.get("fieldName"),wcf));	
						ws.addCell(new Number(4,6+j,  Double.parseDouble( data_m.get("stu_xz").toString()),wcf));	
						ws.addCell(new Number(5,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(6,6+j,(Integer)(final_data.get(j).get("sum_fdx_year1_m"))+(Integer)( final_data.get(j).get("sum_fdx_year1_f")),wcf));
						ws.addCell(new Number(8,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(9,6+j,(Integer)( final_data.get(j).get("sum_dx_year1_m"))+(Integer)( final_data.get(j).get("sum_dx_year1_f")),wcf));
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(12,6+j,(Integer)( final_data.get(j).get("sum_wp_year1_m"))+(Integer)( final_data.get(j).get("sum_wp_year1_f")),wcf));
						ws.addCell(new Number(14,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(15,6+j,(Integer)( final_data.get(j).get("sum_zc_year1_m"))+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));

									
						ws.addCell(new Number(20,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year1_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));	

						ws.addCell(new Number(21,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));
						ws.addCell(new Number(22,6+j,(Integer)( final_data.get(j).get("sum_Z_year1_m"))+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));
						
						ws.addCell(new Number(24,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f")
								+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));

						
			
					}
					for (int k=0;k<184-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
			}
		
		}
		
		if (year_num==3){
			List<Map> final_data = new ArrayList();
			for (int i = 0 ;i<sortby.length;i++){
				if (sortby[i].equals("按学院")){
					CollegeDao cd = new CollegeDao();
					List<College> college_list = cd.viewAllCollege();
					
					for (int j=0;j<college_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;

						int sum_fdx_year1_m=0;
						int sum_fdx_year1_f=0;
						int sum_dx_year1_m=0;
						int sum_dx_year1_f=0;
						int sum_wp_year1_m=0;
						int sum_wp_year1_f=0;
						int sum_zc_year1_m=0;
						int sum_zc_year1_f=0;
						int sum_Z_year1_m=0;
						int sum_Z_year1_f=0;

						int sum_fdx_year2_m=0;
						int sum_fdx_year2_f=0;
						int sum_dx_year2_m=0;
						int sum_dx_year2_f=0;
						int sum_wp_year2_m=0;
						int sum_wp_year2_f=0;
						int sum_zc_year2_m=0;
						int sum_zc_year2_f=0;
						int sum_Z_year2_m=0;
						int sum_Z_year2_f=0;

						Map m = new HashMap();
						m.put("collegeName", college_list.get(j).getCollege_name());
						
						StudentDao sd = new StudentDao();
						List<Student> stu_list = sd.findByCollegeName(college_list.get(j).getCollege_name());
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);



						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[1])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year1_m++;
									}else{
										sum_fdx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year1_m++;
									}else{
										sum_dx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year1_m++;
									}else{
										sum_wp_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year1_m++;
									}else{
										sum_zc_year1_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[1])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year1_m++;
								}else{
									sum_Z_year1_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year1_m", sum_fdx_year1_m);
						m.put("sum_fdx_year1_f", sum_fdx_year1_f);
						m.put("sum_dx_year1_m", sum_dx_year1_m);
						m.put("sum_dx_year1_f", sum_dx_year1_f);
						m.put("sum_wp_year1_m", sum_wp_year1_m);
						m.put("sum_wp_year1_f", sum_wp_year1_f);
						m.put("sum_zc_year1_m", sum_zc_year1_m);
						m.put("sum_zc_year1_f", sum_zc_year1_f);
						m.put("sum_Z_year1_m", sum_Z_year1_m);
						m.put("sum_Z_year1_f", sum_Z_year1_f);
						


						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[2])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year2_m++;
									}else{
										sum_fdx_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year2_m++;
									}else{
										sum_dx_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year2_m++;
									}else{
										sum_wp_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year2_m++;
									}else{
										sum_zc_year2_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[2])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year2_m++;
								}else{
									sum_Z_year2_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year2_m", sum_fdx_year2_m);
						m.put("sum_fdx_year2_f", sum_fdx_year2_f);
						m.put("sum_dx_year2_m", sum_dx_year2_m);
						m.put("sum_dx_year2_f", sum_dx_year2_f);
						m.put("sum_wp_year2_m", sum_wp_year2_m);
						m.put("sum_wp_year2_f", sum_wp_year2_f);
						m.put("sum_zc_year2_m", sum_zc_year2_m);
						m.put("sum_zc_year2_f", sum_zc_year2_f);
						m.put("sum_Z_year2_m", sum_Z_year2_m);
						m.put("sum_Z_year2_f", sum_Z_year2_f);
						



						final_data.add(m);
					}
					
					
					//写入sheet 按学院
	
			        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按学院");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(1,5,years[0]+"级"));
					ws.addCell(new Label(2,5,years[1]+"级"));
					ws.addCell(new Label(3,5,years[2]+"级"));
					ws.addCell(new Label(5,5,years[0]+"级"));
					ws.addCell(new Label(6,5,years[1]+"级"));
					ws.addCell(new Label(7,5,years[2]+"级"));
					ws.addCell(new Label(9,5,years[0]+"级"));
					ws.addCell(new Label(10,5,years[1]+"级"));
					ws.addCell(new Label(11,5,years[2]+"级"));
					ws.addCell(new Label(13,5,years[0]+"级"));
					ws.addCell(new Label(14,5,years[1]+"级"));
					ws.addCell(new Label(15,5,years[2]+"级"));

					ws.addCell(new Label(17,3,years[0]+"级"));
					ws.addCell(new Label(18,3,years[1]+"级"));
					ws.addCell(new Label(19,3,years[2]+"级"));
					ws.addCell(new Label(22,3,years[0]+"级"));
					ws.addCell(new Label(23,3,years[1]+"级"));
					ws.addCell(new Label(24,3,years[2]+"级"));
					
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("collegeName"),wcf));
						ws.addCell(new Number(1,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(2,6+j,(Integer)(final_data.get(j).get("sum_fdx_year1_m"))+(Integer)( final_data.get(j).get("sum_fdx_year1_f")),wcf));
						ws.addCell(new Number(3,6+j,(Integer)(final_data.get(j).get("sum_fdx_year2_m"))+(Integer)( final_data.get(j).get("sum_fdx_year2_f")),wcf));
						


						ws.addCell(new Number(5,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(6,6+j,(Integer)( final_data.get(j).get("sum_dx_year1_m"))+(Integer)( final_data.get(j).get("sum_dx_year1_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)( final_data.get(j).get("sum_dx_year2_m"))+(Integer)( final_data.get(j).get("sum_dx_year2_f")),wcf));
						

						ws.addCell(new Number(9,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(10,6+j,(Integer)( final_data.get(j).get("sum_wp_year1_m"))+(Integer)( final_data.get(j).get("sum_wp_year1_f")),wcf));
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_wp_year2_m"))+(Integer)( final_data.get(j).get("sum_wp_year2_f")),wcf));

						ws.addCell(new Number(13,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(14,6+j,(Integer)( final_data.get(j).get("sum_zc_year1_m"))+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));
						ws.addCell(new Number(15,6+j,(Integer)( final_data.get(j).get("sum_zc_year2_m"))+(Integer)( final_data.get(j).get("sum_zc_year2_f")),wcf));
						

						ws.addCell(new Number(21,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year1_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year1_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year2_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year2_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year2_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year2_f")),wcf));	

						ws.addCell(new Number(22,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));


						ws.addCell(new Number(23,6+j,(Integer)( final_data.get(j).get("sum_Z_year1_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));

						ws.addCell(new Number(24,6+j,(Integer)( final_data.get(j).get("sum_Z_year2_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year2_f")),wcf));


						ws.addCell(new Number(26,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f")
							+(Integer)( final_data.get(j).get("sum_Z_year1_f"))
							+(Integer)( final_data.get(j).get("sum_Z_year2_f")),wcf));	
						
			
					}
					for (int k=0;k<46-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
				final_data.clear();
				if (sortby[i].equals("按学位类别")){
					
					DegreeDao dd = new DegreeDao();
					List<String> degree_list = dd.viewAllUniqueDegree();
					for(int j=0;j<degree_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;

						int sum_fdx_year1_m=0;
						int sum_fdx_year1_f=0;
						int sum_dx_year1_m=0;
						int sum_dx_year1_f=0;
						int sum_wp_year1_m=0;
						int sum_wp_year1_f=0;
						int sum_zc_year1_m=0;
						int sum_zc_year1_f=0;
						int sum_Z_year1_m=0;
						int sum_Z_year1_f=0;

						int sum_fdx_year2_m=0;
						int sum_fdx_year2_f=0;
						int sum_dx_year2_m=0;
						int sum_dx_year2_f=0;
						int sum_wp_year2_m=0;
						int sum_wp_year2_f=0;
						int sum_zc_year2_m=0;
						int sum_zc_year2_f=0;
						int sum_Z_year2_m=0;
						int sum_Z_year2_f=0;

						Map m = new HashMap();
						m.put("degreeType", degree_list.get(j));
						
						StudentDao sd = new StudentDao();
						List<Student> stu_list = sd.findByDegreeType(degree_list.get(j));
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[1])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year1_m++;
									}else{
										sum_fdx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year1_m++;
									}else{
										sum_dx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year1_m++;
									}else{
										sum_wp_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year1_m++;
									}else{
										sum_zc_year1_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[1])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year1_m++;
								}else{
									sum_Z_year1_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year1_m", sum_fdx_year1_m);
						m.put("sum_fdx_year1_f", sum_fdx_year1_f);
						m.put("sum_dx_year1_m", sum_dx_year1_m);
						m.put("sum_dx_year1_f", sum_dx_year1_f);
						m.put("sum_wp_year1_m", sum_wp_year1_m);
						m.put("sum_wp_year1_f", sum_wp_year1_f);
						m.put("sum_zc_year1_m", sum_zc_year1_m);
						m.put("sum_zc_year1_f", sum_zc_year1_f);
						m.put("sum_Z_year1_m", sum_Z_year1_m);
						m.put("sum_Z_year1_f", sum_Z_year1_f);
						

						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[2])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year2_m++;
									}else{
										sum_fdx_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year2_m++;
									}else{
										sum_dx_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year2_m++;
									}else{
										sum_wp_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year2_m++;
									}else{
										sum_zc_year2_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[2])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year2_m++;
								}else{
									sum_Z_year2_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year2_m", sum_fdx_year2_m);
						m.put("sum_fdx_year2_f", sum_fdx_year2_f);
						m.put("sum_dx_year2_m", sum_dx_year2_m);
						m.put("sum_dx_year2_f", sum_dx_year2_f);
						m.put("sum_wp_year2_m", sum_wp_year2_m);
						m.put("sum_wp_year2_f", sum_wp_year2_f);
						m.put("sum_zc_year2_m", sum_zc_year2_m);
						m.put("sum_zc_year2_f", sum_zc_year2_f);
						m.put("sum_Z_year2_m", sum_Z_year2_m);
						m.put("sum_Z_year2_f", sum_Z_year2_f);
						

						final_data.add(m);
					}
					//写入sheet 按学位类别
					DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按学位类别");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(1,5,years[0]+"级"));
					ws.addCell(new Label(2,5,years[1]+"级"));
					ws.addCell(new Label(3,5,years[2]+"级"));
					ws.addCell(new Label(5,5,years[0]+"级"));
					ws.addCell(new Label(6,5,years[1]+"级"));
					ws.addCell(new Label(7,5,years[2]+"级"));
					ws.addCell(new Label(9,5,years[0]+"级"));
					ws.addCell(new Label(10,5,years[1]+"级"));
					ws.addCell(new Label(11,5,years[2]+"级"));
					ws.addCell(new Label(13,5,years[0]+"级"));
					ws.addCell(new Label(14,5,years[1]+"级"));
					ws.addCell(new Label(15,5,years[2]+"级"));

					ws.addCell(new Label(17,3,years[0]+"级"));
					ws.addCell(new Label(18,3,years[1]+"级"));
					ws.addCell(new Label(19,3,years[2]+"级"));
					ws.addCell(new Label(22,3,years[0]+"级"));
					ws.addCell(new Label(23,3,years[1]+"级"));
					ws.addCell(new Label(24,3,years[2]+"级"));
					
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("degreeType"),wcf));
						ws.addCell(new Number(1,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(2,6+j,(Integer)(final_data.get(j).get("sum_fdx_year1_m"))+(Integer)( final_data.get(j).get("sum_fdx_year1_f")),wcf));
						ws.addCell(new Number(3,6+j,(Integer)(final_data.get(j).get("sum_fdx_year2_m"))+(Integer)( final_data.get(j).get("sum_fdx_year2_f")),wcf));
						
						ws.addCell(new Number(5,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(6,6+j,(Integer)( final_data.get(j).get("sum_dx_year1_m"))+(Integer)( final_data.get(j).get("sum_dx_year1_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)( final_data.get(j).get("sum_dx_year2_m"))+(Integer)( final_data.get(j).get("sum_dx_year2_f")),wcf));

						ws.addCell(new Number(9,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(10,6+j,(Integer)( final_data.get(j).get("sum_wp_year1_m"))+(Integer)( final_data.get(j).get("sum_wp_year1_f")),wcf));
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_wp_year2_m"))+(Integer)( final_data.get(j).get("sum_wp_year2_f")),wcf));

						ws.addCell(new Number(13,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(14,6+j,(Integer)( final_data.get(j).get("sum_zc_year1_m"))+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));
						ws.addCell(new Number(15,6+j,(Integer)( final_data.get(j).get("sum_zc_year2_m"))+(Integer)( final_data.get(j).get("sum_zc_year2_f")),wcf));

						ws.addCell(new Number(21,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year1_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year1_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year2_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year2_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year2_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year2_f")),wcf));

						ws.addCell(new Number(22,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));

						ws.addCell(new Number(23,6+j,(Integer)( final_data.get(j).get("sum_Z_year1_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));

						ws.addCell(new Number(24,6+j,(Integer)( final_data.get(j).get("sum_Z_year2_m"))
									+(Integer)( final_data.get(j).get("sum_Z_year2_f")),wcf));

						ws.addCell(new Number(26,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f")
							+(Integer)( final_data.get(j).get("sum_Z_year1_f"))
							+(Integer)( final_data.get(j).get("sum_Z_year2_f")),wcf));	
						
			
					}
					for (int k=0;k<24-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
				final_data.clear();
				if (sortby[i].equals("按领域")){
					
					FieldDao fd = new FieldDao();
					List<Field> field_list = fd.viewAllField();
					for(int j=0;j<field_list.size();j++){
						int sum_fdx_year0_m=0;
						int sum_fdx_year0_f=0;
						int sum_dx_year0_m=0;
						int sum_dx_year0_f=0;
						int sum_wp_year0_m=0;
						int sum_wp_year0_f=0;
						int sum_zc_year0_m=0;
						int sum_zc_year0_f=0;
						int sum_Z_year0_m=0;
						int sum_Z_year0_f=0;

						int sum_fdx_year1_m=0;
						int sum_fdx_year1_f=0;
						int sum_dx_year1_m=0;
						int sum_dx_year1_f=0;
						int sum_wp_year1_m=0;
						int sum_wp_year1_f=0;
						int sum_zc_year1_m=0;
						int sum_zc_year1_f=0;
						int sum_Z_year1_m=0;
						int sum_Z_year1_f=0;

						int sum_fdx_year2_m=0;
						int sum_fdx_year2_f=0;
						int sum_dx_year2_m=0;
						int sum_dx_year2_f=0;
						int sum_wp_year2_m=0;
						int sum_wp_year2_f=0;
						int sum_zc_year2_m=0;
						int sum_zc_year2_f=0;
						int sum_Z_year2_m=0;
						int sum_Z_year2_f=0;

						Map m = new HashMap();
						m.put("fieldCode", field_list.get(j).getField_code());
						m.put("fieldName",field_list.get(j).getField_name());
						
						StudentDao sd = new StudentDao();
						
						m.put("collegeName",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getCollege().getCollege_name());
						m.put("degreeType",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getDegree().getDegree_type());
						m.put("stu_xz",sd.findByFieldCode(field_list.get(j).getField_code()).get(0).getStu_xz());
						

						
						
						List<Student> stu_list = sd.findByFieldCode(field_list.get(j).getField_code());
						
						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[0])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year0_m++;
									}else{
										sum_fdx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year0_m++;
									}else{
										sum_dx_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year0_m++;
									}else{
										sum_wp_year0_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year0_m++;
									}else{
										sum_zc_year0_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[0])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year0_m++;
								}else{
									sum_Z_year0_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year0_m", sum_fdx_year0_m);
						m.put("sum_fdx_year0_f", sum_fdx_year0_f);
						m.put("sum_dx_year0_m", sum_dx_year0_m);
						m.put("sum_dx_year0_f", sum_dx_year0_f);
						m.put("sum_wp_year0_m", sum_wp_year0_m);
						m.put("sum_wp_year0_f", sum_wp_year0_f);
						m.put("sum_zc_year0_m", sum_zc_year0_m);
						m.put("sum_zc_year0_f", sum_zc_year0_f);
						m.put("sum_Z_year0_m", sum_Z_year0_m);
						m.put("sum_Z_year0_f", sum_Z_year0_f);


						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[1])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year1_m++;
									}else{
										sum_fdx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year1_m++;
									}else{
										sum_dx_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year1_m++;
									}else{
										sum_wp_year1_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year1_m++;
									}else{
										sum_zc_year1_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[1])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year1_m++;
								}else{
									sum_Z_year1_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year1_m", sum_fdx_year1_m);
						m.put("sum_fdx_year1_f", sum_fdx_year1_f);
						m.put("sum_dx_year1_m", sum_dx_year1_m);
						m.put("sum_dx_year1_f", sum_dx_year1_f);
						m.put("sum_wp_year1_m", sum_wp_year1_m);
						m.put("sum_wp_year1_f", sum_wp_year1_f);
						m.put("sum_zc_year1_m", sum_zc_year1_m);
						m.put("sum_zc_year1_f", sum_zc_year1_f);
						m.put("sum_Z_year1_m", sum_Z_year1_m);
						m.put("sum_Z_year1_f", sum_Z_year1_f);
						
						

						for (int k=0;k<stu_list.size();k++){
							if (stu_list.get(k).getStu_no().substring(0,4).equals("MZ"+years[2])){
								if (stu_list.get(k).getStu_pylb().equals("非定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_fdx_year2_m++;
									}else{
										sum_fdx_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("定向")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_dx_year2_m++;
									}else{
										sum_dx_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("委培")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_wp_year2_m++;
									}else{
										sum_wp_year2_f++;
									}
								}
								if (stu_list.get(k).getStu_pylb().equals("自筹")){
									if (stu_list.get(k).getSex().equals("男")){
										sum_zc_year2_m++;
									}else{
										sum_zc_year2_f++;
									}
								}
								
							}else if(stu_list.get(k).getStu_no().substring(0,3).equals("Z"+years[2])){
								if (stu_list.get(k).getSex().equals("男")){
									sum_Z_year2_m++;
								}else{
									sum_Z_year2_f++;
								}
								
							}
							
							
						}
						
						m.put("sum_fdx_year2_m", sum_fdx_year2_m);
						m.put("sum_fdx_year2_f", sum_fdx_year2_f);
						m.put("sum_dx_year2_m", sum_dx_year2_m);
						m.put("sum_dx_year2_f", sum_dx_year2_f);
						m.put("sum_wp_year2_m", sum_wp_year2_m);
						m.put("sum_wp_year2_f", sum_wp_year2_f);
						m.put("sum_zc_year2_m", sum_zc_year2_m);
						m.put("sum_zc_year2_f", sum_zc_year2_f);
						m.put("sum_Z_year2_m", sum_Z_year2_m);
						m.put("sum_Z_year2_f", sum_Z_year2_f);


						final_data.add(m);
					}
					//写入sheet 按领域
					DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd"); 
			        Date date = new Date();

					WritableSheet ws = wwb.getSheet("按领域");
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0,1,"统计时间："+df6.format(date)));
					ws.addCell(new Label(5,5,years[0]+"级"));
					ws.addCell(new Label(6,5,years[1]+"级"));
					ws.addCell(new Label(7,5,years[2]+"级"));
					ws.addCell(new Label(9,5,years[0]+"级"));
					ws.addCell(new Label(10,5,years[1]+"级"));
					ws.addCell(new Label(11,5,years[2]+"级"));
					ws.addCell(new Label(13,5,years[0]+"级"));
					ws.addCell(new Label(14,5,years[1]+"级"));
					ws.addCell(new Label(15,5,years[2]+"级"));
					ws.addCell(new Label(17,5,years[0]+"级"));
					ws.addCell(new Label(18,5,years[1]+"级"));
					ws.addCell(new Label(19,5,years[2]+"级"));

					ws.addCell(new Label(21,3,years[0]+"级"));
					ws.addCell(new Label(22,3,years[1]+"级"));
					ws.addCell(new Label(23,3,years[2]+"级"));
					ws.addCell(new Label(26,3,years[0]+"级"));
					ws.addCell(new Label(27,3,years[1]+"级"));
					ws.addCell(new Label(28,3,years[2]+"级"));
					
					for (int j=0;j<final_data.size();j++){
						Map data_m = final_data.get(j);
						ws.addCell(new Label(0,6+j, (String) data_m.get("collegeName"),wcf));
						ws.addCell(new Label(1,6+j, (String) data_m.get("degreeType"),wcf));
						ws.addCell(new Label(2,6+j, (String) data_m.get("fieldCode"),wcf));
						ws.addCell(new Label(3,6+j, (String) data_m.get("fieldName"),wcf));	
						ws.addCell(new Number(4,6+j,  Double.parseDouble( data_m.get("stu_xz").toString()),wcf));	

						ws.addCell(new Number(5,6+j,(Integer)(final_data.get(j).get("sum_fdx_year0_m"))+(Integer)( final_data.get(j).get("sum_fdx_year0_f")),wcf));
						ws.addCell(new Number(6,6+j,(Integer)(final_data.get(j).get("sum_fdx_year1_m"))+(Integer)( final_data.get(j).get("sum_fdx_year1_f")),wcf));
						ws.addCell(new Number(7,6+j,(Integer)(final_data.get(j).get("sum_fdx_year2_m"))+(Integer)( final_data.get(j).get("sum_fdx_year2_f")),wcf));

						ws.addCell(new Number(9,6+j,(Integer)( final_data.get(j).get("sum_dx_year0_m"))+(Integer)( final_data.get(j).get("sum_dx_year0_f")),wcf));
						ws.addCell(new Number(10,6+j,(Integer)( final_data.get(j).get("sum_dx_year1_m"))+(Integer)( final_data.get(j).get("sum_dx_year1_f")),wcf));
						ws.addCell(new Number(11,6+j,(Integer)( final_data.get(j).get("sum_dx_year2_m"))+(Integer)( final_data.get(j).get("sum_dx_year2_f")),wcf));

						ws.addCell(new Number(13,6+j,(Integer)( final_data.get(j).get("sum_wp_year0_m"))+(Integer)( final_data.get(j).get("sum_wp_year0_f")),wcf));
						ws.addCell(new Number(14,6+j,(Integer)( final_data.get(j).get("sum_wp_year1_m"))+(Integer)( final_data.get(j).get("sum_wp_year1_f")),wcf));
						ws.addCell(new Number(15,6+j,(Integer)( final_data.get(j).get("sum_wp_year2_m"))+(Integer)( final_data.get(j).get("sum_wp_year2_f")),wcf));

						ws.addCell(new Number(17,6+j,(Integer)( final_data.get(j).get("sum_zc_year0_m"))+(Integer)( final_data.get(j).get("sum_zc_year0_f")),wcf));
						ws.addCell(new Number(18,6+j,(Integer)( final_data.get(j).get("sum_zc_year1_m"))+(Integer)( final_data.get(j).get("sum_zc_year1_f")),wcf));
						ws.addCell(new Number(19,6+j,(Integer)( final_data.get(j).get("sum_zc_year2_m"))+(Integer)( final_data.get(j).get("sum_zc_year2_f")),wcf));

									
						ws.addCell(new Number(25,6+j,(Integer)( final_data.get(j).get("sum_fdx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year0_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year0_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year0_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year1_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year1_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year1_f"))
								+(Integer)( final_data.get(j).get("sum_fdx_year2_f"))
								+(Integer)( final_data.get(j).get("sum_dx_year2_f"))
								+(Integer)( final_data.get(j).get("sum_wp_year2_f"))
								+(Integer)( final_data.get(j).get("sum_zc_year2_f")),wcf));	

						ws.addCell(new Number(26,6+j,(Integer)( final_data.get(j).get("sum_Z_year0_m"))+(Integer)( final_data.get(j).get("sum_Z_year0_f")),wcf));
						ws.addCell(new Number(27,6+j,(Integer)( final_data.get(j).get("sum_Z_year1_m"))+(Integer)( final_data.get(j).get("sum_Z_year1_f")),wcf));
						ws.addCell(new Number(28,6+j,(Integer)( final_data.get(j).get("sum_Z_year2_m"))+(Integer)( final_data.get(j).get("sum_Z_year2_f")),wcf));
						
						ws.addCell(new Number(30,6+j, (Integer)final_data.get(j).get("sum_Z_year0_f")
								+(Integer)( final_data.get(j).get("sum_Z_year1_f"))
								+(Integer)( final_data.get(j).get("sum_Z_year2_f")),wcf));

						
			
					}
					for (int k=0;k<184-final_data.size();k++)
					{
						ws.removeRow(6+final_data.size());
					}
				}
			}
		
		}
		wwb.write();
		wwb.close();
		Map m = new HashMap();
		m.put("exportStatus", 1);
		return m;
	}
}
