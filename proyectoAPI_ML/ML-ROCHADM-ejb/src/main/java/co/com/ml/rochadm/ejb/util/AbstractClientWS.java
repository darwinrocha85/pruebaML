/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.rochadm.ejb.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author rochadm
 */
public class AbstractClientWS {
  private static Client client = ClientBuilder.newClient();
    private static final Logger LOG = Logger.getLogger(AbstractClientWS.class.getName());

    public static final String AUTHORIZATION = "Authorization";

    static {
        try {

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }

    public AbstractClientWS() {
        super();
    }

    public Response get(String url, String token) {
        WebTarget target = client.target(url);

        if (token == null || token.isEmpty()) {
            return target.request(MediaType.APPLICATION_JSON).get();
        } else {
            return target.request(MediaType.APPLICATION_JSON)
                    .header(AUTHORIZATION, token).get();
        }

    }
}
