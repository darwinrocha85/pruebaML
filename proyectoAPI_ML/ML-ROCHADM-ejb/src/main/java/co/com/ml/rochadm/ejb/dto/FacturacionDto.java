/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.rochadm.ejb.dto;

import co.com.ml.rochadm.ejb.request.FacturacionRequest;
import java.util.Map;

/**
 *
 * @author rochadm
 */
public class FacturacionDto {
    
    private ListarFacturacionResponse response;
    
    private FacturacionRequest request;
    
    private Map<String, Float> itemsML;

    public FacturacionDto(FacturacionRequest request,ListarFacturacionResponse response) {
        this.response = response;
        this.request = request;
    }
    
    public ListarFacturacionResponse getResponse() {
        return response;
    }

    public void setResponse(ListarFacturacionResponse response) {
        this.response = response;
    }

    public FacturacionRequest getRequest() {
        return request;
    }

    public void setRequest(FacturacionRequest request) {
        this.request = request;
    }

    public Map<String, Float> getItemsML() {
        return itemsML;
    }

    public void setItemsML(Map<String, Float> itemsML) {
        this.itemsML = itemsML;
    }
    
    
    
    
}
