package aosivt.UI;

import aosivt.AppData.SaveAppData;
import aosivt.Entity.*;
import aosivt.util.HibernateUtil;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by oshchepkovayu on 20.10.16.
 */
public class ButtonSaveData extends Button {
    public ButtonSaveData()
    {
        this.setCaption("Сохранить данные");
        this.setSizeFull();
        this.setId("save_button");
//        this.setDisableOnClick(true);

        this.addClickListener(e -> this.saveData());

    }

    public void saveData()
    {
        String id_protocol = MainLayout.id_protocol.getValue().toString().replace("/","");

        if (this.checkAllField()) {

            SaveAppData.setOrganization(((Organization) MainLayout.organization_name.getValue()));

            SaveAppData.setViewProtocol(((ViewProtocol) MainLayout.view_protocol.getValue()));

            SaveAppData.setDocument(this.createDocument(MainLayout.id_protocol_doc.getValue().toString(), id_protocol));

            SaveAppData.setReason(this.createReason(MainLayout.reason.getValue().toString(), id_protocol));

            SaveAppData.setReview(this.createReview(MainLayout.review.getValue().toString(), id_protocol));

            SaveAppData.setDate_open((MainLayout.date_open.getValue()));

            SaveAppData.setDate_close((MainLayout.date_close.getValue()));

            SaveAppData.setSum(Double.valueOf(MainLayout.sum.getValue().replace(",", ".")));

        saveDataToDB();
        }
    }
    private void saveDataToDB()
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Document document = SaveAppData.getDocument();
        session.save(document);

        Organization organization= SaveAppData.getOrganization();
//        session.save(organization);

        ViewProtocol  viewProtocol = SaveAppData.getViewProtocol();

        Reason reason = SaveAppData.getReason();
        session.save(reason);

        Review review = SaveAppData.getReview();
        session.save(review);

        PivotTableProtocol tableProtocol = new PivotTableProtocol();
        tableProtocol.setProtocol_id(SaveAppData.getDocument().getProtocol_id());

        tableProtocol.setDate_in(SaveAppData.getDate_open());
        tableProtocol.setDate_out(SaveAppData.getDate_close());
        tableProtocol.setSum(SaveAppData.getSum());
        tableProtocol.setOrganization_id(organization.getOrganization_id());
        tableProtocol.setView_protocol_id(SaveAppData.getViewProtocol().getView_protocol_id());
        tableProtocol.setOrganization(organization);
        tableProtocol.setViewProtocol(viewProtocol);
        tableProtocol.setReview(review);
        tableProtocol.setReason(reason);

        session.save(tableProtocol);
        transaction.commit();

        session.clear();
        session.close();

        Notification.show("Данные сохранены для организации" + SaveAppData.getOrganization().getName_organization() ,Notification.Type.HUMANIZED_MESSAGE);

        MainLayout.search_grid.updateGrid();




    }

    public Document createDocument(String _name_documnet, String _id_protocol)
    {
        Document document = new Document();
        document.setName_document(_name_documnet);
        document.setProtocol_id(Long.parseLong(_id_protocol));
        return document;
    }
    public Reason createReason(String _text_reason, String _id_protocol)
    {
        Reason reason = new Reason();
        reason.setText_reason(_text_reason);
        reason.setProtocol_id(Long.parseLong(_id_protocol));
        return reason;
    }
    public Review createReview(String _text_reason, String _id_protocol)
    {
        Review review = new Review();
        review.setText_review(_text_reason);
        review.setProtocol_id(Long.parseLong(_id_protocol));
        return review;
    }


    public boolean checkExistIdProtocol(String _id_protocol)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        PivotTableProtocol pivotTableProtocol = session.get(PivotTableProtocol.class,Long.parseLong(_id_protocol));
        session.clear();
        session.close();
        return pivotTableProtocol != null;
    }
    public boolean checkAllField()
    {
        String id_protocol = MainLayout.id_protocol.getValue().toString().replace("/","");

        if (id_protocol.length()==0)
        {
            Notification.show("Протокол не заполнен", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        else if (checkExistIdProtocol(id_protocol))
        {
            Notification.show("Номер протокола существует", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        else if (((Organization) MainLayout.organization_name.getValue())==null)
        {
            Notification.show("Организация не выбрана", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        else if (((ViewProtocol) MainLayout.view_protocol.getValue())==null)
        {
            Notification.show("Вид документа не выбран не выбрана", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        else if (MainLayout.id_protocol_doc.getValue().toString().length()==0)
        {
            Notification.show("Поле ИП документа не заполнено", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        if (MainLayout.reason.getValue().toString().length()==0)
        {
            Notification.show("Поле Причина не заполнено", Notification.Type.WARNING_MESSAGE);
            MainLayout.reason.setValue("Не заполнено");
//            return false;
        }
        if (MainLayout.review.getValue().toString().length()==0)
        {
            Notification.show("Поле Коментарий не заполнено", Notification.Type.WARNING_MESSAGE);
            MainLayout.review.setValue("Не заполнено");
//            return false;
        }
        if (MainLayout.sum.getValue().toString().length()==0)
        {
            Notification.show("Поле Сумма не заполнено", Notification.Type.WARNING_MESSAGE);
            MainLayout.sum.setValue("0");
//            return false;
        }

        return true;
    }
}
