package aosivt.UI;

import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

/**
 * Created by oshchepkovayu on 19.10.16.
 */
public class TextFieldIdentityProtocol extends TextField{

public TextFieldIdentityProtocol() {  }

    public TextFieldIdentityProtocol(TextField t)
    {
        t.setTextChangeEventMode(TextChangeEventMode.LAZY);
        t.setNullRepresentation("");
        t.setImmediate(true);


    }
}