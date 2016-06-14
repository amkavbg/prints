package ru.tokido.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tokido on 14.06.2016.
 */
public class MainWindow extends JFrame{


    public MainWindow (){
        setTitle("Prints 0.2a by tokido");

        final JPanel jPanel = new JPanel(new GridLayout(0,1,0,3));
        //for ()
        //TODO:write method which multi put object to table

        final JButton jButton = new JButton("Refresh");
        jButton.addChangeListener(e -> {
        //TODO:do jButton code
        });

        //jPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        Container container = getContentPane();
        container.add(jPanel, BorderLayout.CENTER);
        container.add(jButton, BorderLayout.SOUTH);
        setBounds(200, 200, 900, 700);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


