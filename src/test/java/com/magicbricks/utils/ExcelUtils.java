package com.magicbricks.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.*;
 
public class ExcelUtils {
 
	public static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
 
		List<Map<String, String>> testData = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();
 
		try (FileInputStream fis = new FileInputStream(filePath); 
		Workbook workbook = new XSSFWorkbook(fis))
		{
 
			Sheet sheet = workbook.getSheet(sheetName);
			Row headerRow = sheet.getRow(0); // First row as header
 
			for (int i = 1; i <= sheet.getLastRowNum(); i++) 
			{
				Row currentRow = sheet.getRow(i);
 
				Map<String, String> rowData = new HashMap<>();
 
				for (int j = 0; j < currentRow.getLastCellNum(); j++) 
				{
					String key = headerRow.getCell(j).getStringCellValue();
					String value = formatter.formatCellValue(currentRow.getCell(j));
					rowData.put(key, value);
				}
				testData.add(rowData);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
 
		return testData;
 
	}
 
}
