package ru.tokido;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tokido.engine.Printer;
import ru.tokido.engine.PrinterTemplate;
import ru.tokido.gui.MainWindow;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        ObjectMapper m = new ObjectMapper();
        Map<String, PrinterTemplate> ptempmap = new HashMap<>();
        java.util.List<String> iplist = new ArrayList<>();
        Scanner in = new Scanner(new File("ip.txt"));
        while (in.hasNextLine()) iplist.add(in.nextLine());
        try {
            JsonNode root = m.readTree(new File("config.json"));
            JsonNode secondroot = root.path("Printers");
            for (JsonNode node : secondroot) {
                PrinterTemplate printerTemplate = new PrinterTemplate();
                printerTemplate.setModel(node.path("desc").asText());
                JsonNode oidroot = node.path("oid");
                Iterator<String> fieldNames = oidroot.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    String fieldValue = oidroot.get(fieldName).asText();
                    printerTemplate.setParameters(fieldName, fieldValue);
                }
                ptempmap.put(printerTemplate.getModel(), printerTemplate);
            }
            System.out.println("Printer template map (ptempmap) contains: " +
                    ptempmap.size() + " template object." + "\n " + ptempmap);   //
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        }


        EventQueue.invokeLater(() -> {
            //TODO: write here all recognize work
            System.out.println("GUI STARTED");
            new MainWindow(ptempmap);
        });

    }
}
