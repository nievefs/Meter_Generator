package ReadExcel;

import ClassMeter.Meter;
import ReadExcel.GenerateFrame;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
//Para trabajar con ficheros antiguos excel xls
import org.apache.poi.hssf.usermodel.*; 

/**
 *
 * @author NieveFernandez
 */

public class ReadExcel {
    
    public static ArrayList ReadExcel(File fileXlsx){
        
        ArrayList cellData = new ArrayList();
                         
        try {
            //Un FileInputStream obtiene bytes de entrada desde un archivo
            //FileInputStream file = new FileInputStream(fileUrl);
            //XSSWorkbook permite la lectura de los ficheros xlsx
            
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(fileXlsx));
            //Accedo a la primera hoja del fichero xls
            XSSFSheet sheet = book.getSheetAt(0);            
            
            //Almacenamos los datos en el iterador y recorremos cada fila
            Iterator<Row> rowIterator = sheet.rowIterator(); 
            while(rowIterator.hasNext()){
                //Obtengo los datos de cada fila
                XSSFRow row = (XSSFRow) rowIterator.next();
                
                List cellTemp = new ArrayList();
                //Recorro cada celda o columnas de cada fila
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    XSSFCell cell = (XSSFCell) cellIterator.next();
                    //Guardo los datos de cada fila
                    cellTemp.add(cell);
                }
                //Guardo los datos de todas las filas
                cellData.add(cellTemp);
            }
                 
            book.close();
                        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return cellData;
    }
    
    public Meter createMeter(List rowMeter){
                 
        Meter meter = new Meter();
        for (int c = 0; c < rowMeter.size(); c++) {
            Cell columnData = (Cell) rowMeter.get(c);

            if (c == 0) {
                int productCode = (int) columnData.getNumericCellValue();
                meter.setProductCode(String.valueOf(productCode));
                //System.out.print(meter.getProductCode() + " ");
            }
            if (c == 1) {
                String productModel = columnData.toString();
                meter.setProductModel(productModel);
                meter.setAutoMeterTypeIdByProductModel(productModel);
                               
                //System.out.print(meter.getProductModel() + " ");
                //System.out.print(meter.getMeterTypeId() + " ");
            }
            if (c == 2) {
                String firmware = columnData.toString();
                meter.setFirmware(firmware);
                
                //System.out.print(meter.getFirmware() + " ");
            }
            if (c == 5) {
                DataFormatter dataFormatter = new DataFormatter();
                String serialNumber = dataFormatter.formatCellValue(columnData);
                meter.setSerialNumber(serialNumber);
                
                //System.out.print(meter.getSerialNumber() + " ");
            }
            if (c == 9) {
                String mac = columnData.toString();
                mac = mac.replace(":", "");
                mac = mac.toUpperCase();
                meter.setMac(mac);
                
                //System.out.print(meter.getMac() + " ");
            }
        }
        return meter;

    }
    
    public String createQuerys(File file, String lot){
    
        String query = ""; 
        
        String newline = "\n";
        if (file.exists()) {

            //System.out.println(file.getAbsolutePath().contains(".xlsx"));

            ArrayList metersList = new ArrayList();
            metersList = ReadExcel(file);

            if (!metersList.isEmpty()) {

                //Recorremos la colección de row para guardar los datos de product, mac, fw, SN y description
                for (int r = 1; r < metersList.size(); r++) {
                    List rowData = (List) metersList.get(r);
                    //System.out.println(cellData.size());
                    
                    Meter meter = createMeter(rowData);
                    
                     query += "INSERT INTO meter (sid,meter_type_id,lot_id,firmware,product_code, serial_number, meter_type_code_id) "
                            + "values('@sid','@meter_type_id','@lot_id','@firmware','@product_code', '@serial_number', '@product_model');" + newline;
                      
                        query = query.replace("@sid", meter.getMac());
                        query = query.replace("@meter_type_id", meter.getMeterTypeId());
                        query = query.replace("@lot_id", lot);
                        query = query.replace("@firmware", meter.getFirmware());
                        query = query.replace("@product_code", meter.getProductCode());
                        query = query.replace("@serial_number", meter.getSerialNumber());
                        query = query.replace("@product_model", meter.getProductModel());  
                                           
                } 
            }
        }
        
        //System.out.print(query);   
        return query;
    }

    public static void main(String[] args) {
        
        //Creo la interfaz gráfica
        GenerateFrame window = new GenerateFrame();
        window.setVisible(true);
     
    }
 
}








    