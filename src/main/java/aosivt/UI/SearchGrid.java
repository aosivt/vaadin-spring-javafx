package aosivt.UI;

import aosivt.AppData.GetAppData;
import aosivt.Entity.PivotTableProtocol;
import aosivt.util.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oshchepkovayu on 21.10.16.
 */
public class SearchGrid extends Grid {

    public  SearchGrid()
    {

        this.setWidth(100, Unit.PERCENTAGE);
        this.setHeight(30, Unit.PICAS);

        BeanItemContainer<GetAppData> grid = this.getBeanGetAppData();

        this.setContainerDataSource(grid);

        this.setFilterGrid(grid);

        this.setColumnReorderingAllowed(true);
        this.setId("search_grid");




        this.setColumnOrder("id_protocol","name_organization","date_in","date_out","sum");
        this.getColumn("id_protocol").setHeaderCaption("Номер ИП");
        this.getColumn("name_organization").setHeaderCaption("Наименование организации");
        this.getColumn("date_in").setHeaderCaption("Дата возбуждения");
        this.getColumn("date_out").setHeaderCaption("Дата закрытия");
        this.getColumn("sum").setHeaderCaption("Сумма");
        this.getColumn("document").setHidden(true);
        this.getColumn("organization").setHidden(true);
        this.getColumn("reason").setHidden(true);
        this.getColumn("review").setHidden(true);
        this.getColumn("viewProtocol").setHeaderCaption("Вид документа");

        this.sort("id_protocol", SortDirection.DESCENDING);

        this.addItemClickListener(itemClickEvent ->
        {
            Notification.show(((GetAppData)itemClickEvent.getItemId()).getId_protocol().toString());
            String protocol_id = ((GetAppData)itemClickEvent.getItemId()).getId_protocol().toString();

            MainLayout.id_protocol.setValue(protocol_id);

            MainLayout.view_protocol.select(this.getContainViewProtocol(((GetAppData) itemClickEvent.getItemId()).getViewProtocol().getView_protocol()));

            MainLayout.organization_name.select(this.getContainOrganization(((GetAppData) itemClickEvent.getItemId()).getName_organization()));

            MainLayout.id_protocol_doc.setValue(((GetAppData) itemClickEvent.getItemId()).getDocument().getName_document());

            MainLayout.reason.setValue(((GetAppData) itemClickEvent.getItemId()).getReason().getText_reason());

            MainLayout.review.setValue(((GetAppData) itemClickEvent.getItemId()).getReview().getText_review());

            MainLayout.sum.setValue(String.valueOf(((GetAppData)itemClickEvent.getItemId()).getSum()));

            if (((GetAppData) itemClickEvent.getItemId()).getDate_in().equals("Не определена"))
            {
                MainLayout.date_open.setValue(null);
            }
            else {MainLayout.date_open.setValue(Date.valueOf(((GetAppData) itemClickEvent.getItemId()).getDate_in().substring(0,10)));}

            if (((GetAppData) itemClickEvent.getItemId()).getDate_out().equals("Не определена"))
            {
                MainLayout.date_close.setValue(null);
            }
            else {MainLayout.date_close.setValue(Date.valueOf(((GetAppData) itemClickEvent.getItemId()).getDate_out().substring(0,10)));}

        }
        );
    }

    public BeanItemContainer<GetAppData> getBeanGetAppData()
    {
        BeanItemContainer<GetAppData> itemContainer = new BeanItemContainer<GetAppData>(GetAppData.class);
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From PivotTableProtocol");

        List<PivotTableProtocol> resultlist = q.list();
        GetAppData appData;
        for (PivotTableProtocol next : resultlist) {
            appData = new GetAppData();
            appData.setSum(next.getSum());
            appData.setDate_in(next.getDate_in()==null?"Не определена":next.getDate_in().toString());
            appData.setDate_out(next.getDate_out()==null?"Не определена":next.getDate_out().toString());
            appData.setId_protocol(next.getProtocol_id());
            appData.setName_organization(next.getOrganization()==null?"Не определена":next.getOrganization().getName_organization());
            appData.setDocument(next.getDocument());
            appData.setReason(next.getReason());
            appData.setReview(next.getReview());
            appData.setOrganization(next.getOrganization());
            appData.setViewProtocol(next.getViewProtocol());
            itemContainer.addBean(appData);
        }
        session.close();

        return itemContainer;
    }
    //Method to add a filter on grid
    public void setFilterGrid(BeanItemContainer<?> beanType) {
        //This create a HeaderRow to add filter fields
        HeaderRow filterRow = this.appendHeaderRow();
        for (Column column : getColumns()) {
            //For each column from the grid
            HeaderCell cellFilter = filterRow.getCell(column.getPropertyId());
            //Add a textfield
            cellFilter.setComponent(createFieldFilter(beanType, column));
        }
    }

    //This create a TextField to filter the information
    private TextField createFieldFilter(final BeanItemContainer<?> container, final Column column) {
        TextField filter = new TextField();
        filter.setImmediate(true);
        filter.addTextChangeListener(new FieldEvents.TextChangeListener(){
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                String newValue = event.getText();
                //Remove the previous filter
                container.removeContainerFilters(column.getPropertyId());
                if (newValue != null && !newValue.isEmpty()) {
                    //Filter the information
                    container.addContainerFilter(new SimpleStringFilter(column.getPropertyId(), newValue, true, false));
                }
                recalculateColumnWidths();
            }
        });
        return filter;
    }
    public void updateGrid()
    {
        MainLayout.reason.setValue("");
        MainLayout.review.setValue("");
        MainLayout.id_protocol.setValue("");
        MainLayout.id_protocol_doc.setValue("");
        MainLayout.sum.setValue("");


        BeanItemContainer<GetAppData> grid = this.getBeanGetAppData();
        this.removeHeaderRow(1);
        this.setContainerDataSource(grid);
        this.setFilterGrid(grid);
    }
    public Object getContainViewProtocol(String _view_protocol)
    {
        Iterator _temp = MainLayout.view_protocol.getItemIds().iterator();
        while (_temp.hasNext())
        {
            Object return_view_protocol = _temp.next();
            if (return_view_protocol.toString().equals(_view_protocol.toString()))
            {
                return return_view_protocol;
            }

        }
        return null;
    }
    public Object getContainOrganization(String _organization)
    {
        Iterator _temp = MainLayout.organization_name.getItemIds().iterator();

        while (_temp.hasNext())
        {
            Object return_organization = _temp.next();
            if (return_organization.toString().equals(_organization.toString()))
            {
                return return_organization;
            }

        }
        return null;
    }
}
