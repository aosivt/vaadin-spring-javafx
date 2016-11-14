package aosivt.UI;

import aosivt.Entity.Organization;
import aosivt.Entity.PivotTableProtocol;
import aosivt.Entity.ViewProtocol;
import aosivt.util.HibernateUtil;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by alex on 31.10.16.
 */
public class ButtonUpdateData extends Button {
    public ButtonUpdateData()
    {
        this.setCaption("Обновить данные");
        this.setSizeFull();
        this.addClickListener(e -> this.update_date());
    }
    public void update_date()
    {

        if(this.checkAllField())
        {

                String protocol_id = MainLayout.id_protocol.getValue();
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();

                PivotTableProtocol pivotTableProtocol = session.get(PivotTableProtocol.class,Long.parseLong(protocol_id));

            pivotTableProtocol.getDocument().setName_document(MainLayout.id_protocol_doc.getValue().toString());
            pivotTableProtocol.getReason().setText_reason(MainLayout.reason.getValue());
            pivotTableProtocol.getReview().setText_review(MainLayout.review.getValue());
            pivotTableProtocol.setDate_in(MainLayout.date_open.getValue());
            pivotTableProtocol.setDate_out(MainLayout.date_close.getValue());
            pivotTableProtocol.setSum(Double.valueOf(MainLayout.sum.getValue().replace(",", ".")));
            pivotTableProtocol.setOrganization_id(((Organization)MainLayout.organization_name.getValue()).getOrganization_id());
            pivotTableProtocol.setOrganization(((Organization)MainLayout.organization_name.getValue()));

            pivotTableProtocol.setView_protocol_id(((ViewProtocol)MainLayout.view_protocol.getValue()).getView_protocol_id());
            pivotTableProtocol.setViewProtocol(((ViewProtocol)MainLayout.view_protocol.getValue()));

                session.update(pivotTableProtocol);
                transaction.commit();
                session.clear();
                session.close();
//
                MainLayout.search_grid.updateGrid();

        }
    }
    public boolean checkExistIdProtocol(String _id_protocol)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        PivotTableProtocol pivotTableProtocol = session.get(PivotTableProtocol.class,Long.parseLong(_id_protocol));
        session.clear();
        session.close();
        return pivotTableProtocol == null;
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
            Notification.show("Номер протокола не существует", Notification.Type.WARNING_MESSAGE);
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
        else if (MainLayout.reason.getValue().toString().length()==0)
        {
            Notification.show("Поле Причина не заполнено", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        else if (MainLayout.review.getValue().toString().length()==0)
        {
            Notification.show("Поле Коментарий не заполнено", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        else if (MainLayout.review.getValue().toString().length()==0)
        {
            Notification.show("Поле Сумма не заполнено", Notification.Type.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}
