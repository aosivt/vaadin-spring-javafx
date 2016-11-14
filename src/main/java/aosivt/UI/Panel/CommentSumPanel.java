package aosivt.UI.Panel;

import aosivt.UI.MainLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by oshchepkovayu on 21.10.16.
 */
public class CommentSumPanel extends Panel {


public CommentSumPanel()
{

    final VerticalLayout commetsLayout = new VerticalLayout(MainLayout.date_close,MainLayout.reason);
    commetsLayout.setSpacing(true);
    commetsLayout.setSizeFull();
    this.setCaption("Причина и коментарии");
    this.setContent(commetsLayout);

}



}
