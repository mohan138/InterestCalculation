package com.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadingExel {
    private String fileLocation;
    private String sheetName;
    XSSFWorkbook workbook;

    XSSFSheet sheet;
    public ReadingExel(String fileLocation,String sheetName) throws IOException {
        this.fileLocation = fileLocation;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook(new FileInputStream(this.fileLocation));
        sheet = workbook.getSheet(sheetName);
    }

    public Object[][] getData() throws IOException {

        return getData(1);
    }

    public Object[][] getData(int startingRow) throws IOException {
        return getData(startingRow,1);
    }

    public Object[][] getData(int startingRow, int startingColumn) throws IOException {
        return getData(startingRow,startingColumn,sheet.getLastRowNum()+1,sheet.getRow(startingRow).getLastCellNum());
    }

    public Object[][] getData(int startingRow, int startingColumn, int endingRow, int endingColumn) throws IOException {
        startingRow--;
        Object[][] data = new Object[endingRow-startingRow][(endingColumn-startingColumn)+1];
        DataFormatter formatter = new DataFormatter();

        for(int row=startingRow; row< endingRow; row++){
            for (int column=startingColumn-1; column<endingColumn; column++){
                data[row-startingRow][column-startingColumn+1] = formatter.formatCellValue(sheet.getRow(row).getCell(column));
            }
        }
        return data;
    }
}
