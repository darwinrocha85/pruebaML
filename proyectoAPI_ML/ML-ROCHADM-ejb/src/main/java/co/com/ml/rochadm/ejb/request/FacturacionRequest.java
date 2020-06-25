/*
 * PVE
 */
package co.com.ml.rochadm.ejb.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gutierrezdf
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facturacionRequest")
public class FacturacionRequest {

    @XmlElement(name = "item_ids")
    private String[] itemIds;

    @XmlElement(name = "amount")
    private String amount;

    public FacturacionRequest() {
    }

    public FacturacionRequest(String[] itemIds) {
        this.itemIds = itemIds;
    }

    public FacturacionRequest(String[] itemIds, String amount) {
        this.itemIds = itemIds;
        this.amount = amount;
    }



}
