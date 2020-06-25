/*
 * Sistema de EAP Facturación Portal Ventas
 */
package co.com.ml.rochadm.ejb.service.impl;

import co.com.ml.rochadm.ejb.dto.FacturacionDto;
import co.com.ml.rochadm.ejb.service.ClientWSSBLocal;
import co.com.ml.rochadm.ejb.service.FacturacionSBLocal;
import co.com.ml.rochadm.ejb.util.UtilFacturacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Servicio Facturacion EAP
 *
 * @author <a href="mailto:veracm@globalhitss.com">Carlos Vera</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
public class FacturacionSB implements FacturacionSBLocal {

    @EJB
    private ClientWSSBLocal clienteWS;

    @Override
    public void validateRequest(FacturacionDto dto) throws Exception {
        try {
            if (dto.getRequest() == null) {
                dto.getResponse().setError("el Request enviado no puede ser nulo");
            } else if (UtilFacturacion.isEmpty(dto.getRequest().getAmount())) {
                dto.getResponse().setError("Campo amount: debe existir y no puede ser vacio");
            } else if (!UtilFacturacion.isValidNumber(dto.getRequest().getAmount())) {
                dto.getResponse().setError("Campo amount: debe ser un numero natural (mayor a cero) con hasta dos decimales");
            } else if (UtilFacturacion.isEmpty(dto.getRequest().getItemIds())) {
                dto.getResponse().setError("Campo item_ids: debe existir y no puede ser vacio");
            }

        } catch (Exception ex) {
            dto.getResponse().setError(ex.toString());
        }
    }

    @Override
    public void prepareStatement(FacturacionDto dto) throws Exception {
        try {
            if (dto.getResponse().getError() != null) {
                return;
            }
            float price;
            float amount = Float.parseFloat(dto.getRequest().getAmount());
            Map<String, Float> items = new HashMap<String, Float>();

            for (String item : dto.getRequest().getItemIds()) {
                price = this.getPrice(item);
                if (price <= amount) {
                    items.put(item, price);
                }
            }
            dto.setItemsML(items);
        } catch (Exception ex) {
            dto.getResponse().setError(ex.toString());
        }
    }

    @Override
    public void run(FacturacionDto dto) throws Exception {
        try {
            if (dto.getResponse().getError() != null) {
                return;
            }
            List<String> shopping;

            shopping = calculate(dto.getItemsML(), Float.valueOf(dto.getRequest().getAmount()));

            if (shopping == null) {
                //caso 1: no hay articulos
                dto.getResponse().setTotal("0");
                dto.getResponse().setItemIds(new ArrayList<>());
                dto.getResponse().setError("404-NOT_FOUND");
            } else if (shopping.size() == 2) {
                //caso 2: solo hay un articulo
                dto.getResponse().setTotal(shopping.get(0));
                shopping.remove(0);
                dto.getResponse().setItemIds(shopping);
            } else {
                dto.getResponse().setTotal(shopping.get(0));
                shopping.remove(0);
                dto.getResponse().setItemIds(shopping);
            }

        } catch (Exception ex) {
            dto.getResponse().setError(ex.toString());
        }
    }

    public List<String> calculate(Map<String, Float> items, Float amount) {
        List<String> responseItem = new ArrayList<>();
        Iterator iteratorItems;
        float price;
        boolean[] rest;

        if (items.isEmpty()) {
            //caso 1: no hay articulos
            return null;
        } else if (items.size() == 1) {
            //caso 2: solo hay un articulo

            iteratorItems = items.entrySet().iterator();
            Map.Entry mapElement = (Map.Entry) iteratorItems.next();
            price = ((float) mapElement.getValue());
            responseItem.add(String.valueOf(price));
            responseItem.add((String) mapElement.getKey());
        } else {
            int[] costs = new int[items.size()];
            iteratorItems = items.entrySet().iterator();
            int ii = 0;
            while (iteratorItems.hasNext()) {
                Map.Entry mapElement = (Map.Entry) iteratorItems.next();
                price = ((float) mapElement.getValue());
                costs[ii] = (int) price * 100;
                ii++;
            }

            rest = solve(costs, (int) amount.floatValue() * 100);

            iteratorItems = items.entrySet().iterator();
            ii = 0;
            price = 0;
            while (iteratorItems.hasNext()) {
                Map.Entry mapElement = (Map.Entry) iteratorItems.next();
                if (rest[ii]) {
                    price = ((float) mapElement.getValue()) + price;
                    responseItem.add((String) mapElement.getKey());
                }
                ii++;
            }
            responseItem.add(0, String.valueOf(price));

        }
        return responseItem;
    }

    public boolean[] solve(int[] costs, int capacity) {
        boolean take[] = new boolean[costs.length];
        int min_cost = Integer.MAX_VALUE;
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] < min_cost) {
                min_cost = costs[i];
            }
        }
        int table[][] = new int[costs.length][capacity + 1 - min_cost];
        for (int i = 0; i < costs.length; i++) {
            int v = costs[i];
            int w = costs[i];
            for (int j = 0; j < capacity - min_cost + 1; j++) {
                int prev_value = 0;
                int new_value = 0;
                if (i > 0) {
                    prev_value = table[i - 1][j];
                    if (w <= j + min_cost) {
                        if (w <= j) {
                            new_value = table[i - 1][j - w] + v;
                        } else {
                            new_value = v;
                        }
                    }
                } else if (w <= j + min_cost) {
                    new_value = v;
                }
                table[i][j] = Math.max(prev_value, new_value);
            }
        }
        int index = capacity - min_cost;
        for (int i = costs.length - 1; i > 0 && index >= 0; i--) {
            if (table[i][index] != table[i - 1][index]) {
                take[i] = true;
                index -= costs[i];
                if (index < 0) {
                    System.err.println("index = " + index);
                }
            } else {
                take[i] = false;
            }
        }
        take[0] = index >= 0 && table[0][index] != 0;
        return take;
    }

    private float getPrice(String item) {
        try {
            float price;
            String responseMLA = clienteWS.getPrice(item);

            price = UtilFacturacion.getPrice(responseMLA);

            return price;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

}
