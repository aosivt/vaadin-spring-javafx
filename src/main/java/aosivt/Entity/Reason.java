package aosivt.Entity;

import javax.persistence.*;

/**
 * Created by oshchepkovayu on 18.10.16.
 */

@Entity
@Table(name = "reasons")

public class Reason {
    @Id
    private Long protocol_id;

    private String text_reason;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reviews")
    private PivotTableProtocol pivotTableProtocol;

    public Long getProtocol_id() {
        return protocol_id;
    }

    public void setProtocol_id(Long protocol_id) {
        this.protocol_id = protocol_id;
    }

    public String getText_reason() {
        return text_reason;
    }

    public void setText_reason(String text_reason) {
        this.text_reason = text_reason;
    }

    public PivotTableProtocol getPivotTableProtocol() {
        return pivotTableProtocol;
    }

    public void setPivotTableProtocol(PivotTableProtocol pivotTableProtocol) {
        this.pivotTableProtocol = pivotTableProtocol;
    }
}
