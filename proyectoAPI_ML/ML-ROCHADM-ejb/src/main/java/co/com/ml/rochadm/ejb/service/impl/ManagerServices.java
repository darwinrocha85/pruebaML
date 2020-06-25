package co.com.ml.rochadm.ejb.service.impl;

import co.com.ml.rochadm.ejb.constants.Constants;
import co.com.ml.rochadm.ejb.dto.FacturacionDto;
import co.com.ml.rochadm.ejb.dto.ListarFacturacionResponse;
import co.com.ml.rochadm.ejb.request.FacturacionRequest;
import co.com.ml.rochadm.ejb.service.FacturacionSBLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerServices {
    
    private static final String VERSION = Constants.getCurrentVersion();
    
    @EJB
    private FacturacionSBLocal facturacionService;
    
    public ListarFacturacionResponse getFacturacionGeneral(FacturacionRequest request) {
        ListarFacturacionResponse response = new ListarFacturacionResponse();
        FacturacionDto dto = new FacturacionDto(request, response);
        try {
            
            facturacionService.validateRequest(dto);
            facturacionService.prepareStatement(dto);
            facturacionService.run(dto);
            
        } catch (Exception ex) {
            dto.getResponse().setError(ex.toString());
        }
        
        return dto.getResponse();
    }
    
}
