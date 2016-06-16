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
        final JPanel panel = new JPanel(new GridLayout(0,1,5,5));
            panel.setBorder(BorderFactory.createLineBorder(Color.GREEN)); //

        final JButton jButton = new JButton("Refresh");
            jButton.addChangeListener(e -> {
                //TODO:do jButton code
            });

        //for ()  //TODO:write code which multiple put object to table
        for (PrinterTemplate pt : tmp.values()) {
            JTable table = new JTable(new PTableModel(pt));
                table.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            System.out.println("Object: "+pt.getModel()+ " TABLE SIZE: "+table.getPreferredSize());
            panel.add(table);
        }
        final JScrollPane jpane = new JScrollPane(panel);
            jpane.setBorder(BorderFactory.createLineBorder(Color.RED));

        //mainPanel.add(jpane);
        container.add(mainPanel.add(jpane));
        container.add(jButton, BorderLayout.SOUTH);

        setBounds(200, 200, 900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}


