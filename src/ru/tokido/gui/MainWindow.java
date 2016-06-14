package ru.tokido.gui;

import ru.tokido.engine.PrinterTemplate;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by tokido on 14.06.2016.
 */
public class MainWindow extends JFrame{

    public MainWindow (Map<String, PrinterTemplate> tmp){
        setTitle("Prints 0.2a by tokido");

        final Container container = getContentPane();
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel panel = new JPanel(new GridLayout(0,1,0,3));
        panel.setBorder(BorderFactory.createLineBorder(Color.GREEN)); //

        final JButton jButton = new JButton("Refresh");
            jButton.addChangeListener(e -> {
                //TODO:do jButton code
            });

        //for ()  //TODO:write code which multiple put object to table
        for (PrinterTemplate pt : tmp.values()) {
            JTable table = new JTable(new PTableModel());
            table.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            panel.add(table);
        }

        final JScrollPane jpane = new JScrollPane(panel);
        jpane.setBorder(BorderFactory.createLineBorder(Color.RED));

        mainPanel.add(jpane);
        container.add(mainPanel);
        container.add(jButton, BorderLayout.SOUTH);

        setBounds(200, 200, 900, 700);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}


