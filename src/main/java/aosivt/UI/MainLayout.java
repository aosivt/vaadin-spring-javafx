package aosivt.UI;

import aosivt.UI.Menu.MainMenu;
import aosivt.UI.PopupViewLayout.PopupLayout;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

/**
 * Created by oshchepkovayu on 20.10.16.
 */
public class MainLayout extends VerticalLayout{

//    protected Layout layout;

    public static TextField sum;
    public static TextField id_protocol;
    public static TextField id_protocol_doc;

    public static ComboBoxOrganizations organization_name;
    public static TextField reason;
    public static TextField review;

    public static DateField date_open;
    public static DateField date_close;
    public static ComboBoxViewProtocol view_protocol;
    public static SearchTable search_table;
    public static SearchGrid search_grid;

    public static SearchLayout searchLayout;

    public static boolean view_edit_form;
    public boolean test_view_edit_form = false;
    public static String string_view_edit_form;
    public String test_string_view_edit_form = "";
    public static String place_bd_report;
    public MainLayout()
    {
//        Notification.show(test_string_view_edit_form, Notification.Type.TRAY_NOTIFICATION);


        PopupView pop = new PopupView("Доступ к редактированию", new PopupLayout());

        pop.addPopupVisibilityListener(popupVisibilityEvent ->
                {
                PasswordField pass = (PasswordField)((PopupLayout)pop.getContent().getPopupComponent()).getComponent(0);
                Notification.show(pass.getValue(), Notification.Type.TRAY_NOTIFICATION);
                }
        );
//        if (test_string_view_edit_form.length()==0)
//        {
//            test_string_view_edit_form = MainLayout.string_view_edit_form;
//            test_view_edit_form = MainLayout.view_edit_form;
//            MainLayout.view_edit_form = false;
//            MainLayout.string_view_edit_form = "";
//        }
//        Notification.show(test_string_view_edit_form + "||" + ((PopupLayout)pop.getContent().getPopupComponent()).getPlace_dir(), Notification.Type.TRAY_NOTIFICATION);
        if (Page.getCurrent().getLocation().getPath().toString().replace("/","").equals("init start"))
        {

        }
////        view_edit_form =
//        view_edit_form = string_view_edit_form.equals("Rjycnfynby");
//        Notification.show();




        this.init_all_field();
//        if (!((PopupLayout)pop.getContent().getPopupComponent()).checkPassInsideBD(Page.getCurrent().getLocation().getPath().toString().replace("/","")))
        if (!view_edit_form)
        {
            this.addComponent(pop);
            searchLayout.setHeight(100, Unit.PICAS);
            search_grid.setHeight(100,Unit.PICAS);
        }
        else
        {
            this.addComponent(new MainMenu());
            this.addComponent(new EditLayout());
            MainLayout.view_edit_form = false;
            MainLayout.string_view_edit_form = "";
        }




        this.addComponent(searchLayout);



    }

    public void init_all_field()
        {
            id_protocol = new TextField("Номер ИП");
            id_protocol.setSizeFull();
            id_protocol.setId("protocol");
            id_protocol_doc = new TextField("Документ по ИП");
//            id_protocol_doc.setValue("1");
            id_protocol_doc.setSizeFull();


            view_protocol = new ComboBoxViewProtocol();

//            view_protocol.setValue(((ViewProtocol)view_protocol.getItemIds().iterator().next()).getView_protocol());
//            view_protocol.select(((ViewProtocol)view_protocol.getItemIds().iterator().next()));
            view_protocol.setSizeFull();

            organization_name = new ComboBoxOrganizations();
            organization_name.setSizeFull();

            sum = new TextField("Сумма");
//            sum.setValue("1");
            sum.setSizeFull();

            reason = new TextField("Причина");
//            reason.setValue("1");
            reason.setSizeFull();

            review = new TextField("Коментарий");
//            review.setValue("1");
            review.setSizeFull();


            date_open = new DateField("Дата возбуждения ИП");
            date_open.setSizeFull();
//            date_open.setValue(new Date());

            date_close = new DateField("Дата прекращения ИП");
            date_close.setSizeFull();
//            date_close.setValue(new Date());



//        search_table = new SearchTable();
            search_grid = new SearchGrid();

            searchLayout = new SearchLayout();
        }

    public String getTest_string_view_edit_form() {
        return test_string_view_edit_form;
    }

    public void setTest_string_view_edit_form(String test_string_view_edit_form) {
        this.test_string_view_edit_form = test_string_view_edit_form;
    }
}
