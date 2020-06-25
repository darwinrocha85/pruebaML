/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.rochadm.ejb.util;

import org.json.JSONObject;

/**
 *
 * @author martinezjed
 */
public class UtilFacturacion {

    public static final String RESULT_ERROR = "ERROR";



    public static Boolean isEmpty(String text) {
        return (text == null || text.isEmpty() || text.replaceAll("\\s", "").isEmpty());
    }

    public static Boolean isEmpty(String[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isValidNumber(String s) {
        boolean isValid = true;

        try {
            float nr = Float.parseFloat(s);

            if (nr <= 0) {
                isValid = false;
            } else {
                s = s.replace(',', '.');
                if (!s.contains(".")) {
                    s = s.concat(".00");
                }
                String nro[] = s.replace('.', '-').split("-");
                if (nro[1].length() > 2) {
                    isValid = false;
                }
            }

        } catch (Exception nfe) {
            isValid = false;
        }

        return isValid;
    }
    
    public static float getPrice(String MLA)
    {
        float price=0;
        JSONObject jsonMLA;
        
        jsonMLA = new JSONObject(MLA);
        
        price = jsonMLA.getFloat("price");
        
        return price;
    }

}
