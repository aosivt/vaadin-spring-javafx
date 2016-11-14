package aosivt.UI.Menu;

import aosivt.Entity.ViewProtocol;
import aosivt.ProjectEntityManager.ProEntityManager;
import aosivt.UI.MainLayout;
import aosivt.util.HibernateUtil;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by oshchepkovayu on 28.10.16.
 */
public class CreateDBCommand implements  MenuBar.Command {
    public CreateDBCommand()
    {

    }

    @Override
    public void menuSelected(MenuBar.MenuItem menuItem) {
        Notification.show("Action " + menuItem.getText(),
                Notification.Type.TRAY_NOTIFICATION);
        this.createDB();
    }

    private void createDB()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.setHibernateFlushMode(FlushMode.MANUAL);
        Transaction transaction = session.beginTransaction();
        ViewProtocol viewProtocol_resolution = new ViewProtocol();
        ViewProtocol viewProtocol_damage = new ViewProtocol();
        ViewProtocol viewProtocol_more = new ViewProtocol();

        viewProtocol_resolution.setView_protocol_id(Long.parseLong("1"));
        viewProtocol_resolution.setView_protocol("Постановление");
        viewProtocol_damage.setView_protocol_id(Long.parseLong("2"));
        viewProtocol_damage.setView_protocol("Ущерб");
        viewProtocol_more.setView_protocol_id(Long.parseLong("3"));
        viewProtocol_more.setView_protocol("Другое");

        session.save(viewProtocol_resolution);
        session.save(viewProtocol_damage);
        session.save(viewProtocol_more);
//        insertIntoDBProtocolView(viewProtocol_resolution);
//        insertIntoDBProtocolView(viewProtocol_damage);
//        insertIntoDBProtocolView(viewProtocol_more);
//        session.flush();
        transaction.commit();
//        session.getTransaction().commit();
        session.close();
        ProEntityManager manager = new ProEntityManager();
        MainLayout.view_protocol.setContainerDataSource(manager.getProtocol_list());
        MainLayout.view_protocol.setSizeFull();
    }
    private void insertIntoDBProtocolView(ViewProtocol _new_protocol_view)
    {
//        http://javatalks.ru/topics/34733
    }
}
