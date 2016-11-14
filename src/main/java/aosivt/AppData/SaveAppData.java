package aosivt.AppData;

import aosivt.Entity.*;

import java.util.Date;

/**
 * Created by oshchepkovayu on 20.10.16.
 */
public final class SaveAppData {

    private static Organization organization;

    private static ViewProtocol viewProtocol;

    private static Document document;

    private static Reason reason;

    private static Review review;

    private static double sum;

    private static Date date_open;

    private static Date date_close;


    public SaveAppData()
    {
    }

    public static Organization getOrganization() {
        return organization;
    }

    public static void setOrganization(Organization organization) {
        SaveAppData.organization = organization;
    }

    public static Document getDocument() {
        return document;
    }

    public static void setDocument(Document document) {
        SaveAppData.document = document;
    }

    public static Reason getReason() {
        return reason;
    }

    public static void setReason(Reason reason) {
        SaveAppData.reason = reason;
    }

    public static Review getReview() {
        return review;
    }

    public static void setReview(Review review) {
        SaveAppData.review = review;
    }

    public static ViewProtocol getViewProtocol() {
        return viewProtocol;
    }

    public static void setViewProtocol(ViewProtocol viewProtocol) {
        SaveAppData.viewProtocol = viewProtocol;
    }

    public static Date getDate_open() {
        return date_open;
    }

    public static void setDate_open(Date date_open) {
        SaveAppData.date_open = date_open;
    }

    public static Date getDate_close() {
        return date_close;
    }

    public static void setDate_close(Date date_close) {
        SaveAppData.date_close = date_close;
    }

    public static double getSum() {
        return sum;
    }

    public static void setSum(double sum) {
        SaveAppData.sum = sum;
    }
}
