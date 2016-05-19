package ru.tokido;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //snmp hard worker and other workers =)
        SnmpQuerier snmpquerier = new SnmpQuerier();
        ObjectMapper m = new ObjectMapper();

        //List<Object> plist = new ArrayList<>(); //array template object
        Map<String, PrinterTemplate> ptempmap = new HashMap<>();
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
                System.out.println("Object create: "+ printerTemplate);         //
                printerTemplate.setModel(node.path("desc").asText());
                JsonNode oidroot = node.path("oid");
                Iterator <String> fieldNames = oidroot.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    String fieldValue = oidroot.get(fieldName).asText();
                    printerTemplate.setParameters(fieldName, fieldValue);
                }
                printerTemplate.sayHello();                                     //
                ptempmap.put(printerTemplate.getModel(), printerTemplate);
            }
            System.out.println("ptempmap is: "+ptempmap.size()+" "+ptempmap);   //
        } catch (JsonGenerationException e) {e.printStackTrace();}

        //assign ip to object
        for (String ip : iplist) {
            try {
                try {
                    snmpquerier.start();
                    String pm = snmpquerier.send(ip, "1.3.6.1.2.1.25.3.2.1.3.1");


                } finally {
                    snmpquerier.stop();
                }
            } catch (IOException e) {e.printStackTrace();}
        }
//        for (String p : list) {
//            printerModel.setIp(p);
//            try {
//                try {
//                    snmpquerier.start();
//                    //sending request and get response from device
//                    printerModel.setModel(snmpquerier.send(p, "1.3.6.1.2.1.25.3.2.1.3.1"));
//                    System.out.println(printerModel + "; ip=" + printerModel.getIp() + "; model=" + printerModel.getModel());
//                } finally {
//                    snmpquerier.stop(); }
//            } catch (IOException e) {
//                System.out.println(e.toString());
//            }
//        }
    }
}
