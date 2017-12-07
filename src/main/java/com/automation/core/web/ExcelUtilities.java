package com.automation.core.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author neerajkumar.b.lad
 * About the File: ExcelUtilities file the all resuable methods to deal with excel file
 * Date of Creation: 28th November 2017
 */
public class ExcelUtilities {

	

	/**
	 * @param ExcelFilePath 
	 * @return
	 * @author neerajkumar.b.lad
	 * Date of Creation: 28th November 2017
	 * Purpose: This method help to get excel object by passing excel file path
	 * Contributors Name : neerajkumar.b.lad
	 * Example : ExcelFilePath = ".//Automation/TestData/Login.xls"
	 */
	public Workbook getExcelSheet(String ExcelFilePath)  {
		Workbook Workbook = null;
		try {
			File file = new File(ExcelFilePath);
			FileInputStream inputStream = new FileInputStream(file);
			String fileExtensionName = ExcelFilePath.substring(ExcelFilePath.indexOf("."));

			if (fileExtensionName.equals(".xlsx")) {
				Workbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				Workbook = new HSSFWorkbook(inputStream);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found " +  ExcelFilePath);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File does not exists " +  ExcelFilePath);
			e.printStackTrace();
		}
		return Workbook;

	}

	/**
	 * @param ExcelFilePath
	 * @param SheetName
	 * @return
	 * @author neerajkumar.b.lad
	 * Date of Creation: 28th November 2017
	 * Purpose: This method help to get excel sheet name by passing excel file path, and sheet name
	 * Contributors Name : neerajkumar.b.lad
	 * Example : ExcelFilePath = ".//Automation/TestData/Login.xls" 
	 * SheetName = "Sheet1"
	 */
	public Sheet getsheetName(String ExcelFilePath, String SheetName) {
		Workbook workbook = getExcelSheet(ExcelFilePath);
		Sheet sheet = null;
		try {
			sheet = workbook.getSheet(SheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Sheet " + SheetName + " Not found");
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * @param SheetName
	 * @return
	 * Date of Creation: 28th November 2017
	 * Purpose: This method help to get total row count of excel sheet name by passing sheet name
	 * Contributors Name : neerajkumar.b.lad
	 * Example :  SheetName = "Sheet1"
	 */
	public int getRowCount(Sheet SheetName) {
		return SheetName.getLastRowNum();
	}

	/**
	 * @param SheetName
	 * @return
	 * Date of Creation: 28th November 2017
	 * Purpose: This method help to get total column count of excel sheet name by passing sheet name
	 * Contributors Name : neerajkumar.b.lad
	 * Example :  SheetName = "Sheet1"
	 */
	public int getColumnCount(Sheet SheetName) {
		return SheetName.getRow(0).getLastCellNum();
	}

	/**
	 * @param SheetName
	 * @param Row
	 * @param Col
	 * @return
	 * Date of Creation: 28th November 2017
	 * Purpose: This method help to get cell data from excel sheet by passing sheet name, row and col index
	 * Contributors Name : neerajkumar.b.lad
	 * Example :  SheetName = "Sheet1"
	 * Row = 0
	 * Col = 1
	 */
	@SuppressWarnings("static-access")
	public String getCellData(Sheet SheetName, int Row, int Col) {
		String cellValue = null;
		
			try {
				Cell cell = SheetName.getRow(Row).getCell(Col);
				
				int cellType = cell.getCellType();
				if (cellType == cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cellType == Cell.CELL_TYPE_FORMULA) {
					cellValue = cell.getCellFormula();
				} else if (cellType == Cell.CELL_TYPE_NUMERIC) {
					if (DateUtil.isCellDateFormatted(cell)) {
				        cellValue = cell.getDateCellValue().toString();
				    } else {
				        cellValue = Double.toString(cell.getNumericCellValue());
				    }
				} else if (cellType == Cell.CELL_TYPE_BLANK) {
					cellValue = "";
				} else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
					cellValue = Boolean.toString(cell.getBooleanCellValue());
				}
			} catch (Exception e) {
				System.out.println("Could not return TestData");
				e.printStackTrace();
			}
		
		return cellValue;
	}

	/**
	 * @param SheetName
	 * @return
	 * Date of Creation: 28th November 2017
	 * Purpose: This method help to read 1st row header name from excel sheet by passing sheet name
	 * Contributors Name : neerajkumar.b.lad
	 * Example :  SheetName = "Sheet1"
	 */
	public Map<String, Integer> readHeader(Sheet SheetName) {
		Map<String, Integer> RecordIndex = new HashMap<String, Integer>();
		Row Row = SheetName.getRow(0);

		for(int i=0;i<Row.getLastCellNum();i++)
		{
			RecordIndex.put(Row.getCell(i).getStringCellValue(), i);
		}
		return RecordIndex;
	}


	/**
	 * @param SheetName
	 * @param Record
	 * @param ColumnName
	 * @return
	 * Date of Creation: 28th November 2017
	 * Purpose: This method help to cell data from given sheet name, row index and header Name
	 * Contributors Name : neerajkumar.b.lad
	 * Example :  SheetName = "Sheet1"
	 * Record = SheetName.getRow(0);
	 * ColumnName = "Login"
	 */
	public String getColumnName(Sheet SheetName, Cell[] Record,
			String ColumnName) {
		try {
			Map<String, Integer> Header = readHeader(SheetName);
			Integer Index = Header.get(ColumnName);
			return Record[Index].getStringCellValue();
		} catch (Exception e) {
			System.out.println(ColumnName + " Column is not exists, or could be empty");
		}
		return null;
	}

}
