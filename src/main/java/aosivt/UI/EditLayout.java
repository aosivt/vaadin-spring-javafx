package aosivt.UI;

import aosivt.UI.Panel.CommentSumPanel;
import aosivt.UI.Panel.DatePanel;
import aosivt.UI.Panel.OrganizationPanel;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

/**
 * Created by oshchepkovayu on 21.10.16.
 */
public class EditLayout extends GridLayout {

    public  EditLayout()
    {
this.setSizeFull();

this.generateBorderLayout();
        this.generateMatrixGrid(3,3);
        this.setMargin(true);
        this.setMargin(new MarginInfo(true,true));
//this.generateMatrixGrid(this.getRows(),this.getColumns());

    }

    private void generateMatrixGrid(final int rows, final int columns) {
//        this.removeAllComponents();
//        this.setRows(rows);
//        this.setColumns(columns);

        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < this.getColumns(); col++) {

                this.setRowExpandRatio(row, 1.0f);
                this.setColumnExpandRatio(col, 1.0f);
            }
        }
    }

    private void generateBorderLayout() {
        this.removeAllComponents();
        this.setRows(4);
        this.setColumns(3);

        final Component org_content = new OrganizationPanel();
//        this.addComponent(north, 0, 0, 2, 0);
        this.addComponent(org_content);


        final Component date_content = new DatePanel();
        this.addComponent(date_content);


        final Component comment_content = new CommentSumPanel();
//        this.addComponent(east, 2, 1);
        this.addComponent(comment_content);


//        this.addComponent(center, 1, 1);

        ButtonSaveData save_data = new ButtonSaveData();
        ButtonUpdateData updateData = new ButtonUpdateData();
        ButtonDeleteData deleteData = new ButtonDeleteData();
//        save_data.addClickListener(e -> SaveAppData.setComment(MainLayout.sum.getValue())
//        );

        this.addComponent(MainLayout.review,0,2,2,2);
        this.addComponent(deleteData,0,3);
        this.addComponent(updateData,1,3);
        this.addComponent(save_data,2,3);

//        this.setRowExpandRatio(0, 1.0f);
//        this.setRowExpandRatio(1, 5.0f);
//        this.setRowExpandRatio(2, 1.0f);
//
//        this.setColumnExpandRatio(0, 1.0f);
//        this.setColumnExpandRatio(1, 4.0f);
//        this.setColumnExpandRatio(2, 1.0f);
    }


}
