package co.com.ml.rochadm.ws.rest;


import co.com.ml.rochadm.ejb.dto.ListarFacturacionResponse;
import co.com.ml.rochadm.ejb.service.impl.ManagerServices;
import co.com.ml.rochadm.ejb.constants.Constants;
import co.com.ml.rochadm.ejb.request.FacturacionRequest;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path(Constants.SEPARADOR_BARRA)
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + Constants.REST_CODIFICACION)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicesRest {

    @EJB
    private ManagerServices managerServices;

    @Path(Constants.URL_COUPON)
    @POST
    public ListarFacturacionResponse getFacturacionGeneral(FacturacionRequest request) {
        ListarFacturacionResponse response = managerServices.getFacturacionGeneral(request);
        return response;
    }

}
