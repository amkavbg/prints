package ru.tokido;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tokido.engine.Printer;
import ru.tokido.engine.PrinterTemplate;
import ru.tokido.engine.SnmpQuerier;
import ru.tokido.gui.MainWindow;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        EventQueue.invokeLater(() -> {
            //TODO: write here all recognize work
            new MainWindow();
        });

        SnmpQuerier snmpquerier = new SnmpQuerier();
        ObjectMapper m = new ObjectMapper();
        Map<String, PrinterTemplate> ptempmap = new HashMap<>();
        Map<String, Printer> pmap = new HashMap<>();  //map printer object with completed filed #model,#oid map and other

        //read config and create array ip
        List<String> iplist = new ArrayList<>();  //array ip from text file
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
                //System.out.println("Object create: " + printerTemplate);         //
                printerTemplate.setModel(node.path("desc").asText());
                //System.out.println(node.path("desc"));
                JsonNode oidroot = node.path("oid");
                Iterator <String> fieldNames = oidroot.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    String fieldValue = oidroot.get(fieldName).asText();
                    printerTemplate.setParameters(fieldName, fieldValue);
                }
                ptempmap.put(printerTemplate.getModel(), printerTemplate);
            }
            System.out.println("Printer template map (ptempmap) contains: "+ptempmap.size()+" " +
                    "template object."+"\n "+ptempmap);   //
        } catch (JsonGenerationException e) {e.printStackTrace();}

        //assign ip to object
        for (String ip : iplist) {
            try {
                try {
                    snmpquerier.start();
                    String pmodel = snmpquerier.send(ip, "1.3.6.1.2.1.25.3.2.1.3.1");
                    Printer p = new Printer(ptempmap.get(pmodel),ip,snmpquerier);
                    p.recognize();
                    //p.print(); //TODO: write this method
                    pmap.put(ip, p);
                } finally {snmpquerier.stop();}
            } catch (IOException e) {e.printStackTrace();}
        }

        for (Printer printer : pmap.values()) {
            System.out.println("Params for Printer ["+ printer.getIp()+"] "+printer.getModel()+
                    " NetName :"+printer.getValueByKey("NetName"));
            for (String paramKey : printer.getParamKeys()) {
                String valueByKey = printer.getValueByKey(paramKey);
                System.out.println(
                        "        Param key: "+paramKey+" :"+ valueByKey
                );
            }
        }
        //TODO:for future GUI, map of fully completed objects
        System.out.println("pmap is: "+pmap.size()+"\n "+pmap);
    }
}
