package aosivt.UI;

import aosivt.UI.Panel.SearchPanel;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.GridLayout;

/**
 * Created by oshchepkovayu on 21.10.16.
 */
public class SearchLayout extends GridLayout {

    public SearchLayout()
    {
        this.setSizeFull();
        this.setMargin(true);
        this.setMargin(new MarginInfo(true,true));
        this.addComponent(new SearchPanel());
    }
}
