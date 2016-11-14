package aosivt.UI.Menu;

import com.vaadin.ui.MenuBar;

/**
 * Created by oshchepkovayu on 28.10.16.
 */
public class MainMenu extends MenuBar {

    public MainMenu()
    {
        this.setWidth(100.0f,Unit.PERCENTAGE);

//        this.addItem("Администрирование", new MenuItem("Установка БД", new CreateDBCommand()));
        this.createAdminMenuItem();
        this.ImportDataToExcel();

    }

    private void createAdminMenuItem()
    {
        MenuItem admin = this.addItem("Администрирование", null, null);
//        admin.addItem("Пересоздать БД", null, new CreateDBCommand());
        admin.addItem("Сбросить пароль", null, new CommandLogOut());

        return ;
    }
    private void ImportDataToExcel()
    {
        MenuItem importToExcel = this.addItem("Импорт", null, null);
//        importToExcel.addItem("Выгрузить полностью", null, null);
        importToExcel.addItem("Выгрузить выделенное", null, new CommandExportToExcel());
        return ;
    }
}
