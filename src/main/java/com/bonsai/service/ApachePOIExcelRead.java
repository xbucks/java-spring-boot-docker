package com.bonsai.service;

import com.bonsai.model.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ApachePOIExcelRead {
	private FileInputStream fis;
	private Workbook workbook;

	public ApachePOIExcelRead(FileInputStream fis) throws IOException {
		if (fis.equals(null)) {
			fis = new FileInputStream(new File("C:/Users/ADMIN/Desktop/BonSai.xlsx"));
		}
		this.fis = fis;
		workbook = new XSSFWorkbook(fis);
	}

	public ApachePOIExcelRead() throws IOException {
		FileInputStream fis = new FileInputStream(new File("C:/Users/ADMIN/Desktop/BonSai.xlsx"));
		workbook = new XSSFWorkbook(fis);
	}

	public List<Image> getModels(int sheet) {
		List<Image> list = new ArrayList<>();
		Sheet datatypeSheet = workbook.getSheetAt(sheet);
		Iterator<Row> iterator = datatypeSheet.iterator();

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			Image excelModel = new Image();
			Object[] tmp = new Object[8];
			// load cell
			int i = 0;
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				tmp[i] = getCell(currentCell);
				i++;
			}
			try {
				excelModel.setId(tmp[0].toString());
				excelModel.setTitle(tmp[1].toString());
				excelModel.setSummary(tmp[2].toString());
				excelModel.setContent(tmp[3].toString());
				excelModel.setLink(tmp[4].toString());
				excelModel.setHighlights(Boolean.parseBoolean(tmp[5].toString()));
				excelModel.setId_tree_type(tmp[6].toString());
				excelModel.setViews(Double.parseDouble(tmp[7].toString()));
				list.add(excelModel);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}

		return list;

	}

	private Object getCell(Cell currentCell) {
		if (currentCell.getCellTypeEnum() == CellType.STRING) {
			return currentCell.getStringCellValue();

		} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
			return currentCell.getNumericCellValue();
		}
		return null;
	}

}
