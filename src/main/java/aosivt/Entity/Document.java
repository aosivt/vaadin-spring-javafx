package aosivt.Entity;

import javax.persistence.*;

/**
 * Created by oshchepkovayu on 18.10.16.
 */
@Entity
@Table(name = "documents")

public class Document {

    @Id

    private Long protocol_id;

    private String name_document;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "documents")
    private PivotTableProtocol pivotTableProtocol;

    public Long getProtocol_id() {
        return protocol_id;
    }

    public void setProtocol_id(Long protocol_id) {
        this.protocol_id = protocol_id;
    }

    public String getName_document() {
        return name_document;
    }

    public void setName_document(String name_document) {
        this.name_document = name_document;
    }

    public PivotTableProtocol getPivotTableProtocol() {
        return pivotTableProtocol;
    }

    public void setPivotTableProtocol(PivotTableProtocol pivotTableProtocol) {
        this.pivotTableProtocol = pivotTableProtocol;
    }
}
