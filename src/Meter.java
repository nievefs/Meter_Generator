/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NieveFernandez
 */
import java.util.Map;


public class Meter {
    
    private String mac;
    private String firmware;
    private String productCode;
    private String meterTypeId;
    private String serialNumber;
    private String productModel;
    private int numberOfMeters;

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
    
    public void setAutoMeterTypeIdByProductModel(String productModel, Map<String, String> productMap) {
        this.meterTypeId = "";
        for (Map.Entry<String, String> entry : productMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(key.equalsIgnoreCase(productModel)) {
                this.meterTypeId = value;
                break;
            }
        }
    }
    
}