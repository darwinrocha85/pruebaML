
package co.com.ml.rochadm.ejb.service;

import co.com.ml.rochadm.ejb.dto.FacturacionDto;
import javax.ejb.Local;

@Local
public interface FacturacionSBLocal {

    /**
     * Validar Request
     *
     * @param dto
     * @throws Exception
     */
    public void validateRequest(FacturacionDto dto) throws Exception;

    /**
     * Preparar solicitud de declaración
     *
     * @param dto
     * @throws Exception
     */
    public void prepareStatement(FacturacionDto dto) throws Exception;
    
     /**
     * Preparar solicitud de declaración
     *
     * @param dto
     * @throws Exception
     */
    public void run(FacturacionDto dto) throws Exception;

    
}
