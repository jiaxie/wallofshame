package com.wallofshame.service;

import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: twer
 * Date: 7/3/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("chartDataFetchService")
public class ChartDataFetchService {

    public ChartDataFetchService() {

    }
    public JSONObject fetchData() throws Exception {
        return fetchData(new Date());
    }

    private String convertToString(ArrayList<Date> dateEachOffice){
        String data = "";
        for(int i = 0; i< dateEachOffice.size(); i++){
            data = data + dateEachOffice.get(i).toString();
           if(i != dateEachOffice.size()-1){
               data += ",";
           }
        }
        return data;
    }

    public JSONObject fetchData(Date endingDate) throws Exception {
        JSONObject jsonData = new JSONObject();
        ArrayList<Date> dateTCXOffice = new ArrayList<Date>();
        ArrayList<Date> dateTCCOffice = new ArrayList<Date>();
        ArrayList<Date> dateTBSOffice = new ArrayList<Date>();

     try{
         HSSFSheet sheet = loadSheetOfExcelFile();
         Iterator<Row> rows = sheet.rowIterator();
         while(rows.hasNext()){
             fetchDataPerRow(dateTCXOffice, dateTCCOffice, dateTBSOffice, rows);

         }
     }catch(IOException ex){
         ex.printStackTrace();
     }
        jsonData.put("TCX", convertToString(dateTCXOffice));
        jsonData.put("TBS", convertToString(dateTBSOffice));
        jsonData.put("TCC", convertToString(dateTCCOffice));
        return jsonData;
    }

    private void fetchDataPerRow(ArrayList<Date> dateTCXOffice, ArrayList<Date> dateTCCOffice, ArrayList<Date> dateTBSOffice, Iterator<Row> rows) {
        HSSFRow row = (HSSFRow) rows.next();
        Iterator<Cell> cells = row.cellIterator();

        while(cells.hasNext()) {
            fetchDataPerCell(dateTCXOffice, dateTCCOffice, dateTBSOffice, row, cells);
        }
    }

    private void fetchDataPerCell(ArrayList<Date> dateTCXOffice, ArrayList<Date> dateTCCOffice, ArrayList<Date> dateTBSOffice, HSSFRow row, Iterator<Cell> cells) {
        HSSFCell cell = (HSSFCell) cells.next();
        if (isHeader(row)) return;
        if(cell.getCellNum() == 13){
           String office = cell.getRow().getCell(12).getStringCellValue();
            if(office.equals("TCX")){
               dateTCXOffice.add(cell.getRow().getCell(14).getDateCellValue());
            }else if(office.equals("TCC")){
               dateTCCOffice.add(cell.getRow().getCell(14).getDateCellValue());
            }else if(office.equals("TBS")){
               dateTBSOffice.add(cell.getRow().getCell(14).getDateCellValue());
            }else{
               System.out.print("This person is not in China");
            }
        }
    }

    private boolean isHeader(HSSFRow row) {
        return row.getRowNum() < 2;
    }

    private HSSFSheet loadSheetOfExcelFile() throws IOException {
        FileInputStream input = new FileInputStream("./server/source/submitted-timesheet.xls");
        POIFSFileSystem fs = new POIFSFileSystem(input);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        return wb.getSheetAt(0);
    }
}


















