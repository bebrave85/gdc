package com.gdc.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.messaging.SubscribableChannel;

public class Test {
	
	private String x;
	private String y;
	private String z;
	private String v;
	
	
	public String getX() {
		return x;
	}


	public void setX(String x) {
		this.x = x;
	}


	public String getY() {
		return y;
	}


	public void setY(String y) {
		this.y = y;
	}


	public String getZ() {
		return z;
	}


	public void setZ(String z) {
		this.z = z;
	}


	public String getV() {
		return v;
	}


	public void setV(String v) {
		this.v = v;
	}
	
	


	public Test(String x, String y, String z, String v) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.v = v;
	}
	
	public Test() {

	}
	
	public Test subs(String x){
		Test t=new Test();
		try{
			Integer i1=x.indexOf("|");
			int integerx=Integer.parseInt(x.substring(0,i1));
			t.x=integerx+"";
			System.out.println(integerx);
			//////////////////////////////
			x=x.substring(i1+1, x.length());
			Integer i2=x.indexOf("|");
			String stringx2=x.substring(0,i2);
			t.y=stringx2;
			System.out.println(stringx2);
			//////////////////////////////
			x=x.substring(i2+1, x.length());
			Integer i3=x.indexOf("|");
			String stringx3=x.substring(0,i3);
			t.z=stringx3;
			System.out.println(stringx3);
			///////////////////////////////////
			x=x.substring(i3+1, x.length());
			if(x.length()>0){
				Integer i4=x.indexOf("|");
				String stringx4=x.substring(0,i4);
				t.v=stringx4;
				System.out.println(stringx4);
			}else{
				System.out.println("hhhhhhhhhhhh");
				t.v="";
			}
			
			
		}catch(Exception e){			
		}
		return t;
		
	}


	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		Test test=new Test();
		List<Test> ListObject=new ArrayList<Test>();
		
		 // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\Be BrAvE\\Desktop\\plan-comptable-marocain-excel.xls"));

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getRowNum()!=0){
            	String X="";
            	Iterator<Cell> cellIterator = row.cellIterator();
               while (cellIterator.hasNext()) {
                	Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                	X+=cellValue+"|";
                    //System.out.print(cellValue + "\t");                    
                }
               Test tt=test.subs(X);
               if(tt!=null && tt.getX()!=null && tt.getX()!=""){
            	   ListObject.add(tt);
               }               
                //System.out.println();
            }           
        }
        workbook.close();
        
        for(Test ttt : ListObject){
        	System.out.println(ttt.getX()+"\t"+ttt.getY()+"\t"+ttt.getZ()+"\t"+ttt.getV());
        }				
    }

	

}
