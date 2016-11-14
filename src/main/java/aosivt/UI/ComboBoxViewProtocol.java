package aosivt.UI;

import aosivt.Entity.ViewProtocol;
import aosivt.util.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by oshchepkovayu on 19.10.16.
 */
public class ComboBoxViewProtocol extends ComboBox {

    public ComboBoxViewProtocol()
    {
        this.setCaption("Вид документа по ИП");

        BeanItemContainer<ViewProtocol> itemContainer = this.getBeanViewProtocol();
        this.setContainerDataSource(itemContainer);
        this.setRequired(true);
        this.setImmediate(true);
        this.setFilteringMode(FilteringMode.CONTAINS);
        this.setImmediate(true);
        this.setNullSelectionAllowed(false);
        this.setPageLength(10);
//        this.setItemCaptionPropertyId("view_protocol");


        //выбор первого значения


//        this.updateSelected(((ViewProtocol)this.getItemIds().iterator().next()));
//        this.select(((ViewProtocol)this.getItemIds().iterator().next()));
//        this.select(itemContainer.getIdByIndex(0));

        //Allow new Items
        this.setNewItemsAllowed(true);
        this.setImmediate(true);


//        this.addValueChangeListener(e -> Notification.show("Value changed:",
//                String.valueOf(((ViewProtocol)(e.getProperty().getValue())).getView_protocol_id()),
//                Notification.Type.TRAY_NOTIFICATION));

        this.setNewItemHandler(s ->
                {
                    ViewProtocol viewProtocol = new ViewProtocol();
                    viewProtocol.setView_protocol(s.toString());
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();
                    session.save(viewProtocol);
                    transaction.commit();
                    session.close();
                    Notification.show("Value changed:" + s.toString(),

                            Notification.Type.TRAY_NOTIFICATION);

                    MainLayout.view_protocol.setContainerDataSource(this.getBeanViewProtocol());
//                    MainLayout.viewProtocol.setValue(organization);
                }
        );

    }

    public BeanItemContainer<ViewProtocol> getBeanViewProtocol()
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From ViewProtocol");
        BeanItemContainer<ViewProtocol> itemContainer = new BeanItemContainer<ViewProtocol>(ViewProtocol.class);

        List<ViewProtocol> resultlist = q.list();
        for (ViewProtocol next : resultlist) {
            itemContainer.
                    //addContainerProperty(next.getView_protocol_id(),ViewProtocol.class,next);
                    addBean(next);
        }
        session.close();
        return itemContainer;

    }
    public void updateSelected(ViewProtocol _viewProtocol)
    {
        this.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        this.setItemCaptionPropertyId("name");

        this.select(_viewProtocol);
    }

}
//http://askubuntu.com/questions/788457/how-to-install-pgadmin-4-in-server-mode-on-ubuntu-16-04