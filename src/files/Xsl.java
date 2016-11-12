package files;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class Xsl {
	String filename;
	XSSFWorkbook workbook=null;
	
	public Xsl(String filename) {
		this.filename = filename;
	}
	private XSSFWorkbook getWorkbook() throws IOException {
		if (workbook == null) {
			try {
				FileInputStream fis = new FileInputStream (filename);
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				return wb;
			} finally {
				//do nothing yet
			}
		}
		return workbook;
	}
	
	public boolean VerifyTableHeader (int headerRow, ArrayList<String> columnNames) throws IOException {
		workbook = getWorkbook();
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(headerRow);
		for (int i=0; i<columnNames.size();i++) {
			XSSFCell cell = row.getCell(i);
			String value = cell.getStringCellValue();
			if (!columnNames.get(i).equals(value)) {
				return false;
			}
		}
		return true;
	}
	
	public <T> ArrayList<String> ReadFileToTable (ObservableList<TableColumn<T,?>> tableColumns) throws IOException {
		workbook = getWorkbook();		
        ArrayList<String> res = new ArrayList<String>();
        
		int rowCount=2;
		DataFormatter df = new DataFormatter();		
		
		//Get the first row of the first sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum() - 1;
        while(rowCount <= lastRow) {
        	String tString = ""; 
			XSSFRow row = sheet.getRow(rowCount++);
			for (int cellCount=0; cellCount<tableColumns.size(); cellCount++) {
				XSSFCell cell = row.getCell(cellCount);
				String value = df.formatCellValue(cell);
				if (value == null) {
					System.out.println("Yo!");
				}
				tString += tableColumns.get(cellCount).getText() + ":" + value + ";";
			}
			System.out.println(tString);
		    res.add(tString);   
		}
		return res;
	}
}
