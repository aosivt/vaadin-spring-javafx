package aosivt.UI.Panel;

import aosivt.UI.MainLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by oshchepkovayu on 21.10.16.
 */
public class DatePanel extends Panel {
    public DatePanel()
    {
        final VerticalLayout datePanelLayout = new VerticalLayout(MainLayout.date_open,MainLayout.sum);
        datePanelLayout.setSpacing(true);
        datePanelLayout.setSizeFull();
        this.setCaption("Регистрационные даты(Открытия/Закрытия)");
        this.setContent(datePanelLayout);


    }
}
