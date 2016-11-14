package aosivt.ProjectEntityManager;

import aosivt.Entity.*;
import aosivt.util.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by oshchepkovayu on 20.10.16.
 */
public class ProEntityManager {

    private BeanItemContainer<Document> documents_list;
    private BeanItemContainer<Organization> organization_list;
    private BeanItemContainer<PivotTableProtocol> pivot_table_protocols_list;
    private BeanItemContainer<Reason> reason_list;
    private BeanItemContainer<ViewProtocol> protocol_list;

    public ProEntityManager()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From ViewProtocol");
        protocol_list = new BeanItemContainer<ViewProtocol>(ViewProtocol.class);

        List<ViewProtocol> resultlist_view_protocol = q.list();
        for (ViewProtocol next : resultlist_view_protocol) {
            protocol_list.addBean(next);
        }

        q = session.createQuery("From Organization");
        organization_list = new BeanItemContainer<Organization>(Organization.class);

        List<Organization> resultlist_organization = q.list();
        for (Organization next : resultlist_organization) {
            organization_list.addBean(next);
        }

//        q = session.createQuery("From PivotTableProtocol");
//        pivot_table_protocols_list = new BeanItemContainer<PivotTableProtocol>(PivotTableProtocol.class);
//
//        List<PivotTableProtocol> resultlist = q.list();
//        for (PivotTableProtocol next : resultlist) {
//            pivot_table_protocols_list.addBean(next);
//        }
        session.close();
    }

    public BeanItemContainer<Document> getDocuments_list() {
        return documents_list;
    }

    public BeanItemContainer<Organization> getOrganization_list() {
        return organization_list;
    }

    public BeanItemContainer<PivotTableProtocol> getPivot_table_protocols_list() {
        return pivot_table_protocols_list;
    }

    public BeanItemContainer<Reason> getReason_list() {
        return reason_list;
    }

    public BeanItemContainer<ViewProtocol> getProtocol_list() {
        return protocol_list;
    }
}
