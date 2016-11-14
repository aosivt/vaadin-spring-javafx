package aosivt.UI.Menu;

import aosivt.UI.MainLayout;
import aosivt.WorkingWithExcel.ReportExcel;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oshchepkovayu on 01.11.16.
 */
public class CommandExportToExcel implements  MenuBar.Command  {
    public CommandExportToExcel()
    {

    }

    @Override
    public void menuSelected(MenuBar.MenuItem menuItem) {
        if (MainLayout.place_bd_report.length() == 0)
        {
            MainLayout.place_bd_report = new File(System.getProperty("user.dir")).toString();
        }
        Notification.show(
                MainLayout.place_bd_report +(File.separator)+"Report",
                Notification.Type.TRAY_NOTIFICATION
                        );
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
    if (!(new File(MainLayout.place_bd_report +(File.separator)+"Report").exists()))
    {
        new File(MainLayout.place_bd_report + (File.separator) + "Report").mkdir();
    }
    if (!(new File(MainLayout.place_bd_report
            +(File.separator)+
            "Report" +(File.separator)+
            (format.format(new Date()))).exists()))
    {
        new File((MainLayout.place_bd_report
                +(File.separator)+
                "Report" +(File.separator)+
                (format.format(new Date())))).mkdir();
    }
        this.getDataFromSearchGrid();
    }

    private void getDataFromSearchGrid()
    {
        ReportExcel reportExcel = new ReportExcel();
        SimpleDateFormat format = new SimpleDateFormat("ddMyyyy");

        reportExcel.setOutputFile((format.format(new Date()) +"Отчет.xls"));
        try {
            reportExcel.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
