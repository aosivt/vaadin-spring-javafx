package aosivt.UI.Panel;

import aosivt.UI.MainLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by oshchepkovayu on 21.10.16.
 */
public class OrganizationPanel extends Panel {

    public OrganizationPanel()
    {

        HorizontalLayout row1_org = new HorizontalLayout(MainLayout.id_protocol,MainLayout.id_protocol_doc);
        HorizontalLayout row2_org = new HorizontalLayout(MainLayout.view_protocol,MainLayout.organization_name);
        row1_org.setSizeFull();
        row2_org.setSizeFull();
        final VerticalLayout organizationPanelLayout = new VerticalLayout(row1_org,row2_org);
        organizationPanelLayout.setSpacing(true);
        organizationPanelLayout.setSizeFull();
        this.setCaption("Информация по документу");
        this.setContent(organizationPanelLayout);

    }
}
