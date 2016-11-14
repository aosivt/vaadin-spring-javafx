package aosivt.UI.Menu;

import aosivt.UI.MainLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;

/**
 * Created by oshchepkovayu on 09.11.16.
 */
public class CommandLogOut implements  MenuBar.Command {
    public CommandLogOut()
    {

    }

    @Override
    public void menuSelected(MenuBar.MenuItem menuItem) {
        Notification.show(this.logout(),
                Notification.Type.TRAY_NOTIFICATION);

//       getUI().getPage().reload();
        com.vaadin.ui.JavaScript.getCurrent().execute("window.location.reload();");
    }

    public String logout()
    {
        MainLayout.string_view_edit_form = "";
        MainLayout.view_edit_form = false;

        return "Перезагрузка";
    }
}
