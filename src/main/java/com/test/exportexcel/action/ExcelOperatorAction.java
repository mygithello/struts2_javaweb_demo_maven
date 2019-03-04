package com.test.exportexcel.action;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;
import com.test.exportexcel.service.ExcelOperatorServiceImpl;

/**
 *
 */
public class ExcelOperatorAction extends ActionSupport {
    private InputStream excelFile; //将要导出的Excel将写到这个InputStream中
    private String filename; //导出的Excel文件名
    private ExcelOperatorServiceImpl excelOperatorservice = new ExcelOperatorServiceImpl();

    // ===================================属性get/set==========================================
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public void setExcelFile(InputStream excelFile) {
        this.excelFile = excelFile;
    }
    public ExcelOperatorServiceImpl getExcelOperatorservice() {
        return excelOperatorservice;
    }
    /*public void setExcelOperatorservice(ExcelOperatorServiceImpl excelOperatorservice) {
         this.excelOperatorservice = excelOperatorservice;
    } */
    public InputStream getExcelFile() {
        return excelFile;
    }

    // ================================方法===========================
    public String export() throws Exception {
        excelFile = this.excelOperatorservice.export();//获取excel数据,并转换成输入流
        filename = "test.xls";// 获取导出的excel的名字
        return SUCCESS;
    }
}