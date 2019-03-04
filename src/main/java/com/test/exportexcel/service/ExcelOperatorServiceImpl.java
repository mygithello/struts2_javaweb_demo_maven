package com.test.exportexcel.service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
public class ExcelOperatorServiceImpl {
    /**
     * 导出：合并单元格
     */
    public InputStream export() throws Exception {
        //定义输出流
        ByteArrayOutputStream out;
        out = new ByteArrayOutputStream();

        //生成的excel文件将输出到输出流中
        WritableWorkbook book = Workbook.createWorkbook(out);
        WritableSheet sheet = book.createSheet("日程表", 0);//工作簿1
        //控制工作簿列宽
        sheet.setColumnView(0, 30);//第1列，列宽30
        sheet.setColumnView(1, 15);//第2列，列宽15
        sheet.setColumnView(2, 15);
        sheet.setColumnView(3, 15);
        sheet.setColumnView(4, 15);
        sheet.setColumnView(5, 15);

        //给工作簿添加内容

        //第1列、第1行的单元格内容为“日程表”,使用getTitleCellFormat()方法的样式
        sheet.addCell(new Label(0,0,"日程表",getTitleCellFormat()));

        sheet.addCell(new Label(0,1," ",getTitleCellFormat()));
        sheet.addCell(new Label(1,1,"周一",getTitleCellFormat()));

        //第3列、第2行的单元格内容为“周二”
        sheet.addCell(new Label(2,1,"周二",getTitleCellFormat()));

        sheet.addCell(new Label(3,1,"周三",getTitleCellFormat()));
        sheet.addCell(new Label(4,1,"周四",getTitleCellFormat()));
        sheet.addCell(new Label(5,1,"周五",getTitleCellFormat()));
        sheet.addCell(new Label(0,2,"8:00~10:00",getTitleCellFormat()));
        sheet.addCell(new Label(0,3,"10:00~11:00",getTitleCellFormat()));
        sheet.addCell(new Label(0,4,"11:00~12:00",getTitleCellFormat()));
        sheet.addCell(new Label(1,2,"没课",getBaseCellFormat()));
        sheet.addCell(new Label(2,2,"休息",getBaseCellFormat()));
        sheet.addCell(new Label(3,2,"看电影",getBaseCellFormat()));
        sheet.addCell(new Label(4,2,"没活动",getBaseCellFormat()));
        sheet.addCell(new Label(5,2,"睡觉",getBaseCellFormat()));
        sheet.addCell(new Label(2,3,"游戏",getBaseCellFormat()));
        sheet.addCell(new Label(4,3,"上课",getBaseCellFormat()));
        sheet.addCell(new Label(1,4,"吃饭",getBaseCellFormat()));

        sheet.mergeCells(0, 0, 5, 0);//第1列第一个单元格至第5列第1个单元格合并
        sheet.mergeCells(1, 2, 1, 3);//第2列第3个单元格至第2列第4个单元格合并
        sheet.mergeCells(3, 2, 3, 3);//第2列第3个单元格至第2列第4个单元格合并
        sheet.mergeCells(4, 3, 5, 3);//第2列第3个单元格至第2列第4个单元格合并
        sheet.mergeCells(1, 4, 5, 4);//第2列第3个单元格至第2列第4个单元格合并
        book.write();book.close();book = null;

        //将输出流转换成二进制数组，借这个数组将Excel写入输入流中（写入内存）
        return new ByteArrayInputStream(out.toByteArray());
    }

    /**
     * 设置基本单元格样式
     */
    private WritableCellFormat getBaseCellFormat() throws WriteException {
        WritableCellFormat format = new WritableCellFormat();
        format.setBorder(Border.ALL, BorderLineStyle.THIN);//单元边框           format.setAlignment(Alignment.CENTRE);//单元内容水平对齐           format.setVerticalAlignment(VerticalAlignment.CENTRE);//单元内容垂直对齐           format.setBackground(Colour.ICE_BLUE);
        WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 12);//字体为宋体，大小是12磅(小四)           format.setFont(titleFont);
        return format;
    }

    /**
     * 设置标题单元格样式
     */
    private WritableCellFormat getTitleCellFormat() throws WriteException{
        WritableCellFormat format = new WritableCellFormat();
        format.setBorder(Border.ALL, BorderLineStyle.THIN);//单元边框           format.setAlignment(Alignment.CENTRE);//单元内容水平对齐           format.setVerticalAlignment(VerticalAlignment.CENTRE);//单元内容垂直对齐           format.setBackground(Colour.GRAY_25);
        WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//字体为宋体，大小是四号,加粗           format.setFont(titleFont);
        return format;
    }
}