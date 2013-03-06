package bugchipper.gui.tabs;

import javax.swing.*;
import java.awt.*;
import bugchipper.gui.*;
import bugchipper.gui.eventhandlers.*;

public class TabPane extends JPanel {

    JTabbedPane pane;
    TabButton button;

    public TabPane (JTabbedPane inp_pane) {
        super(new FlowLayout(FlowLayout.LEFT,0,0));
        
        pane = inp_pane;
        setOpaque(false);

        JLabel text = new JLabel() {
                public String getText() {
                    int i = pane.indexOfTabComponent(TabPane.this);
                    if (i != -1) {
                        return pane.getTitleAt(i);
                    }
                    return null;
                }
            };
        this.add(text);
        text.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        button = new TabButton(pane, this);
        this.add(button);
        setBorder(BorderFactory.createEmptyBorder(2,0,0,0));

        buttonHandler bh = new buttonHandler();
        button.addActionListener(bh);
    }
}
