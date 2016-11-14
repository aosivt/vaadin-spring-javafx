package aosivt.WorkingWithExcel;

import aosivt.AppData.GetAppData;
import aosivt.UI.MainLayout;
import com.vaadin.data.util.BeanItemContainer;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.CellFormat;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by alex on 01.11.16.
 */
public class ReportExcel {
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write() throws IOException, WriteException {

        int j = 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String _temp_dir = MainLayout.place_bd_report
                +(File.separator)+
                "Report" +(File.separator)+
                (format.format(new Date()));
        String _temp_input_file = inputFile;
        while (new File(_temp_dir + (File.separator)+ _temp_input_file).exists())
        {
            _temp_input_file = inputFile.split("\\.")[0].toString() + "("+ String.valueOf(j) +")." + inputFile.split("\\.")[1].toString();
            j++;
        }

        inputFile = _temp_dir + (File.separator)+ _temp_input_file;
        _temp_input_file = null;

        File file = new File(inputFile);
        file.createNewFile();
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("ru", "RU"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);

        workbook.write();
        workbook.close();
    }

    private void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);

        //cv.setSize(cv.getFormat().);

        // Write a few headers
        addCaption(sheet, 0, 0, "Header 1");
        addCaption(sheet, 1, 0, "This is another header");
    }
    private void createContent(WritableSheet sheet) throws WriteException,
            RowsExceededException {
        // Write a few number
        Iterator  _temp = (((BeanItemContainer)MainLayout.search_grid.getContainerDataSource())).getItemIds().iterator();

//        ((BeanItemContainer)MainLayout.search_grid.getContainerDataSource()).getFilteredItemIds();

        this.createHeaderFile(sheet);

        GetAppData appData = null;
        int i = 1;
        while (_temp.hasNext())
        {
            appData = ((GetAppData)_temp.next());
            //Номер ИП 0
            this.addLabel(sheet, 0, i, appData.getId_protocol().toString());

            //Наименование организации 3
            this.addLabel(sheet, 3, i, appData.getName_organization());
            //Дата возбуждения 4
            this.addLabel(sheet, 4, i, appData.getDate_in());
            //Дата закрытия 5
            this.addLabel(sheet, 5, i, appData.getDate_out());
            //Сумма 8
            this.addNumber(sheet, 8, i, Double.valueOf(appData.getSum()).isNaN() ?
                                       0.0:
                                       appData.getSum());
            //Документ по ИП 1
            this.addLabel(sheet, 1, i, appData.getDocument() == null ?
                    "Не заполненно":
                    appData.getDocument().getName_document());

            //Причина 6
            this.addLabel(sheet, 6, i, appData.getReason() == null ?
                    "Не заполненно":
                    appData.getReason().getText_reason());

            //Коментарий 7
            this.addLabel(sheet, 7, i, appData.getReview() == null ?
                    "Не заполненно":
                    appData.getReview().getText_review());

            //Вид документа по ИП 2
            this.addLabel(sheet, 2, i, appData.getDocument().getPivotTableProtocol().getViewProtocol() == null ?
                                        "Не заполненно":
                                       appData.getDocument().getPivotTableProtocol().getViewProtocol().getView_protocol());
            i++;

        }
        StringBuffer buf = new StringBuffer();
        buf.append("SUM(I2:I"+i+")");
        Formula f = new Formula(1, i+1, buf.toString());
        sheet.addCell(f);
        addCaption(sheet,0,i+1,"ИТОГО:");
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
                           Double integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        CellFormat cellFormat = label.getCellFormat();

        sheet.addCell(label);
        CellView cell=sheet.getColumnView(0);
        if (s.length()<30 & s.length()>256)
        {
            cell.setSize(s.length()*256 +100);
        }
        else {cell.setSize(30*256 +100);}
        sheet.setColumnView(column,cell);

    }
    private void createHeaderFile(WritableSheet sheet)
    {

        try {
            this.addCaption(sheet, 0, 0, "Номер ИП");
            this.addCaption(sheet, 1, 0, "Документ по ИП");
            this.addCaption(sheet, 2, 0, "Вид документа по ИП");
            this.addCaption(sheet, 3, 0, "Наименование организации");
            this.addCaption(sheet, 4, 0, "Дата возбуждения");
            this.addCaption(sheet, 5, 0, "Дата закрытия");
            this.addCaption(sheet, 6, 0, "Причина");
            this.addCaption(sheet, 7, 0, "Коментарий");
            this.addCaption(sheet, 8, 0, "Сумма");



        } catch (WriteException e) {
            e.printStackTrace();
        }
    }


    /*Workbook workbook = Workbook.getWorkbook(new File("output.xls"));
    Sheet sheet = workbook.getSheet(0);
    Cell cell1 = sheet.getCell(0, 2);
    System.out.println(cell1.getContents());
    Cell cell2 = sheet.getCell(3, 4);
    System.out.println(cell2.getContents());
    workbook.close();*/
}
