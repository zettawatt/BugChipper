package bcclient;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.*;

public class BugChipLog extends JTextArea {
    Mediator mdtr;
    String data;
    SimpleDateFormat ft;
    
    public BugChipLog(Mediator inp_mdtr) {
        mdtr = inp_mdtr;
        mdtr.registerBugChipLog(this);

        ft = new SimpleDateFormat ("MM.dd.yy 'at' kk:mm:ss : ");

        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.addData("Started Bugchipper");
    }

    public void addData (String inp_data) {
        data = inp_data;
        Date date = new Date();
        this.append(ft.format(date) + data + "\n");
    }
}
