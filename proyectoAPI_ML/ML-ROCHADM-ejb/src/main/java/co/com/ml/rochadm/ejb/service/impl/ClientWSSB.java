/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.rochadm.ejb.service.impl;

import co.com.ml.rochadm.ejb.service.ClientWSSBLocal;
import co.com.ml.rochadm.ejb.util.AbstractClientWS;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import org.apache.commons.httpclient.HttpStatus;

/**
 *
 * @author rochadm
 */
@Stateless
public class ClientWSSB extends AbstractClientWS implements ClientWSSBLocal {

    public static String url = "https://api.mercadolibre.com/items/";

    @Override
    public String getPrice(String item) throws Exception {
        String result = "";
        try {

            String urlTemp = url.concat(item);
            
            Response response = super.get(urlTemp, null);

            if (response.getStatus() == HttpStatus.SC_OK
                    || response.getStatus() == HttpStatus.SC_CREATED) {
                result = response.readEntity(String.class);
            } else {

                throw new Exception("error consumiendo servicio");

            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
