import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream fis = new FileInputStream("C://Users//v-nidhsingh//eclipse-workspace//ExcelDriven//DataRestApi.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis); 
		
		int sheet = workbook.getNumberOfSheets();  
		
		for(int i=0;i<sheet;i++) { 
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
			XSSFSheet sheet1 = workbook.getSheetAt(0); 
			
			Iterator<Row> row = sheet1.iterator(); 
			Row firstraw = row.next(); 
			
			Iterator<Cell> ce = firstraw.iterator();  
			
			int k =0; 
			int column =0; 
			while(ce.hasNext()) {
				Cell value = ce.next(); 
				if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
					column = k;
				} 
				k++; 
			} 
			System.out.println(column);
		 
			//start of while
			while(row.hasNext()) {
				Row r = row.next(); 
		       
				if(r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase")) {
					Iterator<Cell> cv = r.cellIterator();
					while(cv.hasNext()) {
						System.out.println(cv.next().getStringCellValue());
					}
				}
			}
			
			///end of while 
		

			
		} 
		}
		
	}

}
