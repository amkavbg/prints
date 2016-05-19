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
        List<Object> plist = new ArrayList<>();
        //read config and create array ip
        List<String> iplist = new ArrayList<>();
        Scanner in = new Scanner(new File("ip.txt"));
        while (in.hasNextLine()) iplist.add(in.nextLine());

        //read config.json, create array template\skeleton object with model and oid map
        try {
            JsonNode root = m.readTree(new File("config.json"));
            //System.out.println(root.getNodeType());  //lets see what type the node is //prints OBJECT
            //System.out.println(root.isContainerNode()); //is it a container //prints true
            JsonNode secondroot = root.path("Printers");
            //System.out.println(secondroot.getNodeType());  //prints ARRAY
            //System.out.println(secondroot.isContainerNode());   //true
            for (JsonNode node : secondroot) {
                PrinterModel printerModel = new PrinterModel();
                System.out.println("Object create: "+printerModel);
                printerModel.setModel(node.path("desc").asText());
                //System.out.println(printer.getModel());
                JsonNode oidroot = node.path("oid");
                Iterator <String> fieldNames = oidroot.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    String fieldValue = oidroot.get(fieldName).asText();
                    printerModel.setParameters(fieldName, fieldValue);

                }
                printerModel.sayHello(); //test
                plist.add(printerModel);
            }
            System.out.println("plist is: "+plist.size()+"  "+ plist);
        } catch (JsonGenerationException e) {e.printStackTrace();}

        //assign field objects
        for (String ip : iplist) {
            try {
                try {
                    snmpquerier.start();
                    String pm = snmpquerier.send(ip, "1.3.6.1.2.1.25.3.2.1.3.1");
                    //put here check

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
