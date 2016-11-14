package aosivt.UI.Panel;

import aosivt.UI.MainLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

/**
 * Created by oshchepkovayu on 21.10.16.
 */
public class SearchPanel extends Panel {
    public SearchPanel()
    {
        final HorizontalLayout sLayout = new HorizontalLayout(MainLayout.search_grid);
        sLayout.setSpacing(true);
        sLayout.setSizeFull();
        this.setCaption("Сводная таблица");
        this.setContent(sLayout);

    }
}
