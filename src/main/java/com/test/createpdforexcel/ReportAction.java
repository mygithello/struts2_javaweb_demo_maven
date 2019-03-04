package com.test.createpdforexcel;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.test.createpdforexcel.BaseAction;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;


public class ReportAction extends BaseAction {

	private Logger logger = Logger.getLogger(ReportAction.class);

	public String reportPdf() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//String sql = "select empno,ename from emp";
		String sql = "select COMMISSION_PCT as EMPNO,last_name as ENAME from employees emp  where first_name ='Ellen'";
		try {
			//1.注册驱动
			Class.forName("oracle.jdbc.OracleDriver");
			//2.获得数据库的连接
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			//3.获得Statement对象
			stmt = con.prepareStatement(sql);
			//4.执行SQL语句
			//rs = stmt.executeQuery(sql);
			/*//5.处理执行结果
			System.out.println("雇员的姓名\t性别\t出生日期");
			while(rs.next()) {
				String name = rs.getString("EMPNO");
				String gender = rs.getString("ENAME");
				//String borndate = rs.getString("borndate");
				System.out.println(name + "\t" + gender + "\t" );
				System.out.println("name=====: "+name+"   gender =====: "+gender);
			}*/

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//reportPdfSQL("report.jasper",sql,"emp.pdf",con,stmt,null);
		reportExcelSQL("report.jasper",sql,"emp.xls",con,stmt,null);

		return SUCCESS;
	}


	private void reportPdfSQL(String jasperPath,String sql,String fileName,Connection conn,PreparedStatement ps,Map parameters){
		ResultSet rs = null;
		File reportFile = new File(this.getRequest().getRealPath("/reports/"+jasperPath));
		if(reportFile != null && reportFile.exists()){
			try {
				rs = ps.executeQuery();
				logger.error("+++++++++++++"+conn);
				byte[] bytes = null;
				bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),parameters,new JRResultSetDataSource(rs));

				if (bytes != null && bytes.length > 0)
				{
					this.getResponse().setContentType("textml;charset=UTF-8");
					this.getResponse().setContentType("application/pdf");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename="+fileName);
					this.getResponse().setContentLength(bytes.length);
					ServletOutputStream out = this.getServletOutputStream();
					out.write(bytes,0,bytes.length);
					out.flush();
					this.closeServletOutputStream(out);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(conn != null){
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					conn = null;
					rs = null;
					ps = null;
				}
				if(reportFile != null){
					reportFile = null;
				}
			}
		}else{
			logger.error("++++++++++++++++go else");
		}
		this.getOut().write("对不起，系统原因或者查询错误导致报表没有生成，请重试。");
		this.closeOut(this.getOut());

	}

	private void reportExcelSQL(String jasperPath,String sql,String fileName,Connection conn,PreparedStatement ps,Map parameters){
		ResultSet rs = null;
		File reportFile = new File(this.getRequest().getRealPath("/reports/"+jasperPath));
		if(reportFile != null && reportFile.exists()){
			try {
				rs = ps.executeQuery();
				byte[] bytes = null;
				JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),parameters,new JRResultSetDataSource(rs));
				JRXlsExporter exporter = new JRXlsExporter();
				ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
				exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, xlsReport);
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE); // 删除记录最下面的空行
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);// 删除多余的ColumnHeader
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);// 显示边框
				exporter.exportReport();
				bytes = xlsReport.toByteArray();

				//System.out.println(bytes.length);
				if (bytes != null && bytes.length > 0)
				{
					this.getResponse().setContentType("textml;charset=UTF-8");
					this.getResponse().setContentType("application/vnd.ms-excel");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename="+fileName);
					this.getResponse().setContentLength(bytes.length);
					ServletOutputStream out = this.getServletOutputStream();
					out.write(bytes,0,bytes.length);
					out.flush();
					this.closeServletOutputStream(out);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(conn != null){
					try {
						rs.close();
						ps.close();
						conn.close();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					conn = null;
					rs = null;
					ps = null;
				}
				if(reportFile != null){
					reportFile = null;
				}
			}
		}else{
			logger.error("++++++++++++++++go else");
		}
		this.getOut().write("对不起，系统原因或者查询错误导致报表没有生成，请重试。");
		this.closeOut(this.getOut());
	}

}
