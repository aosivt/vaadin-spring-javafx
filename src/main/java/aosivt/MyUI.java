package aosivt;

import aosivt.Entity.ViewProtocol;
import aosivt.UI.MainLayout;
import aosivt.util.HibernateUtil;
import com.sun.glass.ui.Application;
import com.sun.glass.ui.Window;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@JavaScript({
        "vaadin://jquery-3.1.1.js",

        "vaadin://test_con.js"
})
@SpringUI(path = "")
@EnableAutoConfiguration
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {


//        test_insert();
        final VerticalLayout layout = new MainLayout();

        com.vaadin.ui.JavaScript.getCurrent().execute("test_con();");

        setContent(layout);

    }


}