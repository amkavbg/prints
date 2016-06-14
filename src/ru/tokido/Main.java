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

        //read config and create array ip
        java.util.List<String> iplist = new ArrayList<>();  //array ip from text file
        Scanner in = new Scanner(new File("ip.txt"));
        while (in.hasNextLine()) iplist.add(in.nextLine());

        //read config.json, create hashmap template\skeleton object with model and oid map
        try {
            JsonNode root = m.readTree(new File("config.json"));
            //System.out.println(root.getNodeType());  //lets see what type the node is //prints OBJECT
            //System.out.println(root.isContainerNode()); //is it a container //prints true
            JsonNode secondroot = root.path("Printers");
            //System.out.println(secondroot.getNodeType());  //prints ARRAY
            //System.out.println(secondroot.isContainerNode());   //true
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
            System.out.println("Printer template map (ptempmap) contains: " + ptempmap.size() + " " +
                    "template object." + "\n " + ptempmap);   //
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
