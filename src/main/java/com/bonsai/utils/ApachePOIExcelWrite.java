package com.bonsai.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIExcelWrite {
	
	private static final String FILE_NAME = "C:/Users/ADMIN/Desktop/Test.xlsx";
	
	public void createFile() {
		
		XSSFWorkbook  workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Bonsai Data");
		Object[][] dataTypes = {
				{
					"Number", "URL"
				},
				{
					"1", "URL1"
				},
				{
					"2", "URL2"
				},
				{
					"3", "URL3"
				},
		};
		
		int rowNum = 0;
		System.out.println("Creating excel");
		
		for(Object[] dataType : dataTypes) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for(Object field : dataType) {
				Cell cell = row.createCell(colNum++);
				if(field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}
		
		try {
			FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}
}
