package aosivt.UI.PopupViewLayout;

import aosivt.Entity.ConfigurationProperty;
import aosivt.UI.MainLayout;
import aosivt.util.HibernateUtil;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by oshchepkovayu on 09.11.16.
 */
public class PopupLayout extends VerticalLayout{
    PasswordField pass;
    TextField restore_pass;
    TextField place_dir_report;
    Button save_to_db ;
    boolean check_pass = false;
    String place_dir;


            public PopupLayout()
            {
                this.pass = new PasswordField("Пароль доступа");
                this.place_dir_report = new TextField("Корневая папка отчетов");
                this.restore_pass = new TextField("Задать пароль доступа");
                this.save_to_db = new Button("Сохранить");
                this.pass.addValueChangeListener(valueChangeEvent ->
                        {
                            if (this.pass.getValue().equals("init start"))
                            {
                                this.addComponent(this.restore_pass);
                                this.addComponent(this.place_dir_report);
                                this.addComponent(this.save_to_db);
                            }
                            else {
//                                MainLayout.view_edit_form = this.pass.getValue().equals("Rjycnfynby");
                                MainLayout.view_edit_form = this.checkPassInsideBD(this.pass.getValue());
//                                this.setCheck_pass(this.checkPassInsideBD(this.pass.getValue()));
                                MainLayout.string_view_edit_form = this.pass.getValue();
                                MainLayout.place_bd_report = this.getPlace_dir();

                                Notification.show(MainLayout.view_edit_form ? "Да" : "Нет", Notification.Type.TRAY_NOTIFICATION);
                                String t = Page.getCurrent().getLocation().toString();
//                                java.security.MessageDigest.getInstance("MD5").digest(this.pass.getValue().getBytes()));

//                                Page.getCurrent().setLocation(t + this.pass.getValue());

                                getUI().getPage().reload();
                            }
                        }
                );
                this.addAttachListener(attachEvent -> {
                    this.pass.focus();
                    Notification.show(new File(".").getAbsolutePath(), Notification.Type.TRAY_NOTIFICATION);
                });

                this.save_to_db.addClickListener(clickEvent -> createPassAndDirReport(this.restore_pass.getValue(),this.place_dir_report.getValue()));

                this.addComponent(this.pass);
            }

    public void createPassAndDirReport(String _input_pass, String _input_dir_report)
    {
        if (checkAllField(_input_pass, _input_dir_report)) {
            if (!checkPassInsideBD(_input_pass)) {
                ConfigurationProperty conf = new ConfigurationProperty();
                conf.setPass(_input_pass);
                conf.setDir_report(_input_dir_report);

                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.save(conf);
                transaction.commit();
                session.close();

            }
            else {Notification.show("МДА надо чего нить другое придумать!!!", Notification.Type.WARNING_MESSAGE);}
        }
    }

    public boolean checkPassInsideBD(String _input_pass)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List result = session
                .createQuery("from ConfigurationProperty where pass=:pass")
                .setParameter("pass", _input_pass)
                .list();
        if (!result.isEmpty())
        {
//            MainLayout.place_bd_report = ((ConfigurationProperty)result.get(0)).getDir_report();
            this.setPlace_dir(((ConfigurationProperty)result.get(0)).getDir_report());
            this.setCheck_pass(true);

        }
        return !result.isEmpty();
    }

    public boolean checkAllField(String _input_pass, String _input_dir_report)
    {
        if (_input_pass.length()==0)
        {
            Notification.show("Необходимо заполнять поле с паролем доступа", Notification.Type.WARNING_MESSAGE);
            return false;
        }
        else if (_input_dir_report.length()==0)
        {
            Notification.show("Необходимо заполнять поле с местом хранения отчетов", Notification.Type.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean isCheck_pass() {
        return check_pass;
    }

    public void setCheck_pass(boolean check_pass) {
        this.check_pass = check_pass;
    }

    public String getPlace_dir() {
        return place_dir;
    }

    public void setPlace_dir(String place_dir) {
        this.place_dir = place_dir;
    }
}


