package co.com.ml.rochadm.ejb.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarFacturacionResponse {

    @XmlElement(name = "item_ids")
    private List<String> itemIds;

    @XmlElement(name = "total")
    private String total;
    
    @XmlElement(name = "ERROR")
    private String error;
    

    public ListarFacturacionResponse() {

    }

}
