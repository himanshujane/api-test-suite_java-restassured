package genericLib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRW {

	public FileInputStream fis;
	public XSSFWorkbook wb;

	//Initializing WorkBook
	public ExcelRW(String path) throws Exception {

	//File input stream
	fis = new FileInputStream(path);

	//Excel workbook
	wb = new XSSFWorkbook(fis);
	}
	
	//Returns Sheet
	public XSSFSheet getSheet(String sheetName) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		return sheet;
	}

	//Returns Row Count
	public int getRowCount(String sheetName) {
		int rowcount = getSheet(sheetName).getLastRowNum();
		return rowcount;
	}

	//Returns Column Count
	public int getColCount(String sheetName) {
		int colcount = getSheet(sheetName).getRow(0).getLastCellNum();
		return colcount;
	}

    //Read Functionality
	public String readCellValue(String sheetName, int row, int col) throws IOException {
		
		String Celltext = "";

		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFCell cellval = sheet.getRow(row).getCell(col);

		if (cellval.getCellType() == Cell.CELL_TYPE_BLANK) {
			Celltext = "";
		} else if (cellval.getCellType() == Cell.CELL_TYPE_STRING) {
			Celltext = cellval.getStringCellValue();
		} else if (cellval.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Celltext = String.valueOf(cellval.getNumericCellValue());
		}
		fis.close();
		return Celltext;
	}

	//Write to Excel Sheet
	public void writeCellValue(String sheetName, int row, int col, String val) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		sheet.getRow(row).getCell(col).setCellValue(val);
	}

	//Save and Close
	void SaveandClose(String path) throws Exception {
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();
		fis.close();
	}
}
