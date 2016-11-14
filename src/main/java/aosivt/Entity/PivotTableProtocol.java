package aosivt.Entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by oshchepkovayu on 18.10.16.
 */
@Entity
@Table(name = "pivot_table_protocol")

public class PivotTableProtocol {

    @Id

    private Long protocol_id;

    private Long organization_id;

    private Long view_protocol_id;

    @Column(name = "sum_protocol")
    private Double sum;

    private Date date_in;

    private Date date_out;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private ViewProtocol viewProtocol;

    @OneToOne
    @JoinColumn(name = "protocol_id", referencedColumnName = "protocol_id")
    private Review reviews;

    @OneToOne
    @JoinColumn(name = "protocol_id", referencedColumnName = "protocol_id")
    private Reason reasons;

    @OneToOne
    @JoinColumn(name = "protocol_id", referencedColumnName = "protocol_id")
    private Document documents;

    public Long getProtocol_id() {
        return protocol_id;
    }

    public void setProtocol_id(Long protocol_id) {
        this.protocol_id = protocol_id;
    }

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }

    public Long getView_protocol_id() {
        return view_protocol_id;
    }

    public void setView_protocol_id(Long view_protocol_id) {
        this.view_protocol_id = view_protocol_id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getDate_in() {
        return date_in;
    }

    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    public Date getDate_out() {
        return date_out;
    }

    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public ViewProtocol getViewProtocol() {
        return viewProtocol;
    }

    public void setViewProtocol(ViewProtocol viewProtocol) {
        this.viewProtocol = viewProtocol;
    }

    public Review getReview() {
        return reviews;
    }

    public void setReview(Review review) {
        this.reviews = review;
    }

    public Reason getReason() {
        return reasons;
    }

    public void setReason(Reason reason) {
        this.reasons = reason;
    }

    public Document getDocument() {
        return documents;
    }

    public void setDocument(Document document) {
        this.documents = document;
    }
}
