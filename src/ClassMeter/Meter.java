package ClassMeter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NieveFernandez
 */

import java.util.HashMap;
import java.util.Map;


public class Meter {
    
    private String mac;
    private String firmware;
    private String productCode;
    private String meterTypeId;
    private String serialNumber;
    private String productModel;
    private int numberOfMeters;
    private final String[] meterModel = {"W3P","WB3","WBB","WBM","WBP","WBT","WTD","WX3","WX2","WWM"};
    private final String[] productTipeID_model = {"wibeee_3phase","mirubox_v2_tri","mirubox_v2","wibeee_1phase","wibeee_smartplug","wibeee_3phase","wibeee_3phase","wibeee_max","wibeee_max","wibeee_connect"};    


    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMeterTypeId() {
        return meterTypeId;
    }

    public void setMeterTypeId(String meterTypeId) {
        this.meterTypeId = meterTypeId;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public String getProductModel() {
        return productModel;
    }
    
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }
    
    public int getNumberOfMeters(){
        return numberOfMeters;
    }
    
    public void setNumberOfMeters(int numberOfMeters){
        this.numberOfMeters = numberOfMeters;
    }
    
 
    //Añadir el meter_type_id de BBDD segun el description que recibimos
    public void setAutoMeterTypeIdByProductModel(String productModel) {
        Map<String, String> productMap = getModelAndTypeId();  
        this.meterTypeId = "";
        
        for (Map.Entry<String, String> entry : productMap.entrySet() ) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(key.equalsIgnoreCase(productModel)) {
                this.meterTypeId = value;
                break;
            }
        }
    }
    
    public Map<String, String> getModelAndTypeId(){
        Map<String, String> result = new HashMap<>();
                    
        for (int i = 0; i < meterModel.length; i++) {
            result.put(meterModel[i], productTipeID_model[i]);
        }
      
        return result;
    }
    
    //Añadir el meter_type_id de BBDD segun el codigo del producto
    public void setAutoMeterTypeIdByProductCode(String productCode, Map<String, String> productMap) {
        this.meterTypeId = "";
        for (Map.Entry<String, String> entry : productMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(key.equalsIgnoreCase(productCode)) {
                this.meterTypeId = value;
                break;
            }
        }
    }

    
}