package com.company.project.controller;


import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Test {

    public static void main(String[] args) throws IOException {
    	 //1 创建工作簿对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        //2 创建工作表对象
        XSSFSheet xssfSheet = workbook.createSheet("学生名单");
        xssfSheet.setColumnWidth(0,20*256);//设置列宽度
        xssfSheet.setColumnWidth(1,20*256);
        xssfSheet.setColumnWidth(2,20*256);
        xssfSheet.setColumnWidth(3,20*256);
        xssfSheet.setColumnWidth(4,20*256);
        //3 创建行
        XSSFRow row01 = xssfSheet.createRow(0);
        row01.setHeightInPoints(28);
        //合并列
        row01.createCell(0).setCellValue("地区：福建省   福州市   闽侯县   上街镇");
        CellRangeAddress region=new CellRangeAddress(0,0,0,4);
        xssfSheet.addMergedRegion(region);
        
        XSSFRow row02 = xssfSheet.createRow(1);
        row02.setHeightInPoints(26);
        row02.createCell(0).setCellValue("小区名称：永嘉天地幸福里");
        CellRangeAddress region1=new CellRangeAddress(1,1,0,1);
        xssfSheet.addMergedRegion(region1);
        
        row02.createCell(2).setCellValue("日期：2010-10-01");
        CellRangeAddress region2=new CellRangeAddress(1,1,2,3);
        xssfSheet.addMergedRegion(region2);
        
        row02.createCell(4).setCellValue("操作员：肖俊峰");
        XSSFRow row03 = xssfSheet.createRow(2);
        row03.createCell(0).setCellValue("人员姓名");
        row03.createCell(1).setCellValue("身份证号");
        row03.createCell(2).setCellValue("人员属性");
        row03.createCell(3).setCellValue("通行时间");
        row03.createCell(4).setCellValue("列别（进或出）");
        //5.通过输出流对象写到磁盘
        FileOutputStream os = new FileOutputStream("C:\\Users\\陈先生\\Desktop\\111\\xjf.xlsx");
        workbook.write(os);
        os.flush();
        os.close();
        workbook.close();
    	

    }

}
