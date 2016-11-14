package aosivt.UI;

import aosivt.Entity.PivotTableProtocol;
import aosivt.util.HibernateUtil;
import com.vaadin.ui.Button;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by alex on 31.10.16.
 */
public class ButtonDeleteData extends Button {
    public ButtonDeleteData()
    {
        this.setCaption("Удалить данные");
        this.setSizeFull();
        this.addClickListener(e -> this.deleteData());


    }
    public void deleteData()
    {
        String protocol_id = MainLayout.id_protocol.getValue();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        PivotTableProtocol pivotTableProtocol = session.get(PivotTableProtocol.class,Long.parseLong(protocol_id));

//        session.createQuery("delete PivotTableProtocol where protocol_id = " + protocol_id).executeUpdate();

//        q.executeUpdate();
//        EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
//        entityManager.createQuery()
        session.remove(pivotTableProtocol.getDocument());
        session.remove(pivotTableProtocol.getReason());
        session.remove(pivotTableProtocol.getReview());
        session.delete(pivotTableProtocol);
        transaction.commit();
        session.clear();
        session.close();

        MainLayout.search_grid.updateGrid();
    }

}
