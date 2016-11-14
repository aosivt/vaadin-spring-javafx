package aosivt.UI;

import aosivt.Entity.Organization;
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
public class ComboBoxOrganizations extends ComboBox{

    public ComboBoxOrganizations()
    {
        this.setCaption("Наименование организации");
        this.setContainerDataSource(this.getBeanOrganization());
//        this.setRequired(true);
        this.setImmediate(true);
        this.setFilteringMode(FilteringMode.CONTAINS);
//        this.setItemCaptionPropertyId("organization_id");

        //Allow new Items
        this.setNewItemsAllowed(true);
        this.setImmediate(true);

        this.setNullSelectionAllowed(false);

        this.addValueChangeListener(e ->
                Notification.show("Value changed:",
                String.valueOf(((Organization)(e.getProperty().getValue())).getOrganization_id()),
                Notification.Type.TRAY_NOTIFICATION));
        this.addDetachListener (e -> Notification.show("Value changed:!!!!!11111",
        String.valueOf(e.toString()),
        Notification.Type.TRAY_NOTIFICATION));

            this.setNewItemHandler(s ->
            {
                Organization organization = new Organization();
                organization.setName_organization(s.toString());
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.save(organization);
                transaction.commit();
                session.close();
                Notification.show("Value changed:" + s.toString(),

                Notification.Type.TRAY_NOTIFICATION);

                MainLayout.organization_name.setContainerDataSource(this.getBeanOrganization());
                MainLayout.organization_name.setValue(organization);
            }
        );



    }

    public BeanItemContainer<Organization> getBeanOrganization()
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From Organization");
        BeanItemContainer<Organization> itemContainer = new BeanItemContainer<Organization>(Organization.class);

        List<Organization> resultlist = q.list();
        for (Organization next : resultlist) {
            itemContainer.
                    //addContainerProperty(next.getView_protocol_id(),ViewProtocol.class,next);
                            addBean(next);
        }
        session.close();
        return itemContainer;

    }


    public void updateSelected(Organization _org)
    {
        this.select(_org);
    }

}
