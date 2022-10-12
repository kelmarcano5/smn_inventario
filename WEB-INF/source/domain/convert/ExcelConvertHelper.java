package domain.convert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelConvertHelper { 
	
	public InputStream convertFromXLSXtoXLS(File XSLX) throws FileNotFoundException{ 
		InputStream inp = new FileInputStream(XSLX); 
		return logic(inp);
	}
	
	public InputStream convertFromXLSXtoXLS(InputStream XSLX) throws FileNotFoundException{ 
		return logic(XSLX);
	}
	
	public InputStream logic (InputStream inp) {
		try { 
			Workbook wb = WorkbookFactory.create(inp); 
			HSSFWorkbook newWb = new HSSFWorkbook(); 
			final CellStyle newCellStyle = newWb.createCellStyle();
			int ns = wb.getNumberOfSheets();
			for (int i = 0; i < ns; i++) {
				Sheet sheet = wb.getSheetAt(i);
				Sheet copia = newWb.createSheet(wb.getSheetName(i));
				Iterator<Row> rows = sheet.iterator(); 
				while(rows.hasNext()){ 
					Row row = rows.next(); 
					if(row.getRowNum()<=65535) {
						Row newRow = copia.createRow(row.getRowNum()); 
						Iterator<Cell> cells = row.cellIterator(); 
						while( cells.hasNext()){ 
							try {
								Cell cell = cells.next(); 
								if(cell.getColumnIndex()<=255) {
									Cell newCell = newRow.createCell(cell.getColumnIndex()); 
									int type = cell.getCellType();
									
									boolean f = false;
									
									try {
										f = HSSFDateUtil.isCellDateFormatted(cell);
									} catch (Throwable e) {}
									
									if (f) {
										newCell.setCellValue(cell.getDateCellValue());
										newCellStyle.setDataFormat(cell.getCellStyle().getDataFormat());
										newCell.setCellStyle(newCellStyle);
									} else {
										switch(type){ 
											case Cell.CELL_TYPE_BLANK: 
												break; 
											case Cell.CELL_TYPE_NUMERIC: 
												newCell.setCellValue(cell.getNumericCellValue()); 
												break; 
											case Cell.CELL_TYPE_STRING: 
												newCell.setCellValue(cell.getStringCellValue()); 
												break; 
											case Cell.CELL_TYPE_ERROR: 
												newCell.setCellErrorValue(cell.getErrorCellValue()); 
												break; 
											case Cell.CELL_TYPE_BOOLEAN: 
												newCell.setCellValue(cell.getBooleanCellValue()); 
												break; 
											case Cell.CELL_TYPE_FORMULA: 
												newCell.setCellFormula(cell.getCellFormula()); 
												break; 
										}
									}
									
									
								}
							} catch (Throwable e) {
								e.printStackTrace();
							} 
						}
					} 
				} 
			}
			
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			newWb.write(bo);
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			return bi; 
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (InvalidFormatException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		return null; 
	}
} 