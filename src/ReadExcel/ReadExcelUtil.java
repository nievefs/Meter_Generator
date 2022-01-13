package ReadExcel;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.*; //Para trabajar con ficheros antiguos excel sls
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;

/**
 *
 * @author NieveFernandez
 */

public class ReadExcelUtil {
   
    
    public static ArrayList ReadExcelUtil(File fileXlsx){
                
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

    public static void main(String[] args) {
        
        //Creo la interfaz gráfica
        GeneratePanel panel = new GeneratePanel();
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);
    
        File fileXls = new File("C:/Users/NieveFernandez/Documents/MACS_WHome/Prueba.xlsx");
        if (fileXls.exists()){
            
            System.out.println(fileXls.getAbsolutePath().contains(".xlsx"));
           
            ArrayList metersList = new ArrayList();
            metersList = ReadExcelUtil(fileXls);
            
            if (!metersList.isEmpty()) {
                //Recorremos la colección para guardar los datos de product, mac, fw, SN y description
                for (int r = 1; r < metersList.size(); r++){
                    List rowData = (List)metersList.get(r);
                    //System.out.println(cellData.size());
                    for (int c = 0; c < rowData.size(); c++){
                        Cell columnData = (Cell) rowData.get(c);
                        
                        if(c==0){
                            int productCode = (int)columnData.getNumericCellValue();
                            System.out.print(productCode + " ");
                            System.out.print(String.valueOf(productCode) + " ");
                        }
                        if(c==1){ 
                            String productModel =  columnData.toString();
                            System.out.print(productModel + " ");
                        }
                        if(c==2){
                            String firmware =  columnData.toString();
                            System.out.print(firmware + " ");
                        }
                        if(c==5){
                         
                            DataFormatter dataFormatter = new DataFormatter();
                            String serialNumber = dataFormatter.formatCellValue(columnData);
                            System.out.print(serialNumber + " ");
                        }
                        if(c==9){
                            String mac =  columnData.toString();
                            mac = mac.replace(":","");
                            mac = mac.toUpperCase();
                            System.out.print(mac + " ");
                        }
                    }
                    System.out.println();    
                }
            }   
            
        }
       
    }
}








    