package bcclient.gui.popups;

import javax.swing.*;
import java.awt.*;
import bcclient.*;

/**
 * Display the Log Dialog from the View Menu
 */
public class LogDialog extends JDialog {

    private JScrollPane scrollPane;
    private JPanel contentPane;

    public LogDialog(BugChipLog log) {
        this.setLayout( new BorderLayout());
        this.setTitle("BugChipper Log");
        this.setSize(250,500);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        scrollPane = new JScrollPane(log);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.setPreferredSize(new Dimension(400,400));

        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
