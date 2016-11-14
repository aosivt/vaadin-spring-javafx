package aosivt.Entity;

import javax.persistence.*;

/**
 * Created by oshchepkovayu on 18.10.16.
 */

@Entity
@Table(name = "reviews")

public class Review {
    @Id
    private Long protocol_id;

    private String text_review;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reviews")
    private PivotTableProtocol pivotTableProtocol;

    public Long getProtocol_id() {
        return protocol_id;
    }

    public void setProtocol_id(Long protocol_id) {
        this.protocol_id = protocol_id;
    }

    public String getText_review() {
        return text_review;
    }

    public void setText_review(String text_review) {
        this.text_review = text_review;
    }

    public PivotTableProtocol getPivotTableProtocol() {
        return pivotTableProtocol;
    }

    public void setPivotTableProtocol(PivotTableProtocol pivotTableProtocol) {
        this.pivotTableProtocol = pivotTableProtocol;
    }
}
