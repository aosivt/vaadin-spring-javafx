package aosivt.AppData;

import aosivt.Entity.*;

/**
 * Created by alex on 20.10.16.
 */
public class GetAppData {
    private Long id_protocol;

    private  String name_organization;

    private Organization organization;

    private Document document;

    private Reason reason;
    private Review review;

    private ViewProtocol viewProtocol;

    private  String date_in;
    private  String date_out;

    private  double sum;

    public GetAppData()
    {

    }

    public Long getId_protocol() {
        return id_protocol==null?Long.parseLong("0"):id_protocol;
    }


    public String getDate_in() {
        return date_in==null?"Не указана":date_in;
    }

    public String getDate_out() {
        return date_out==null?"Не указана":date_out;
    }

    public String getName_organization() {
        return name_organization==null?"Не указана":name_organization;
    }

    public double getSum() {
        return  sum;
    }

    public void setId_protocol(Long id_protocol) {
        this.id_protocol = id_protocol==null?Long.parseLong("0"):id_protocol;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public ViewProtocol getViewProtocol() {
        return viewProtocol;
    }

    public void setViewProtocol(ViewProtocol viewProtocol) {
        this.viewProtocol = viewProtocol;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in==null?"Не указана":date_in;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out==null?"Не указана":date_out;
    }

    public void setName_organization(String name_organization) {
        this.name_organization = name_organization==null?"Не указана":name_organization;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
