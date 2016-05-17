package ru.tokido;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
/*
        //snmp hard worker =)
        SnmpQuerier snmpquerier = new SnmpQuerier();

        //?????? ?????? ? ?????? ?????? ?? ???? ?????????
        List<String> list = new ArrayList<>();
        Scanner in = new Scanner(new File("ip.txt"));
        while (in.hasNextLine()) list.add(in.nextLine());

        //??????? ??????? "???????" ? ??????????? ?????? IP
        for (String p : list) {
            PrinterModel printerModel = new PrinterModel();
            printerModel.setIp(p);
            try {
                try {
                    snmpquerier.start();
                    //?????????? ? ?????????? ? ???????? ??? ?????? ?? OID
                    printerModel.setModel(snmpquerier.send(p, "1.3.6.1.2.1.25.3.2.1.3.1"));
                    System.out.println(printerModel + "; ip=" + printerModel.getIp() + "; model=" + printerModel.getModel());
                } finally {
                    snmpquerier.stop(); }
            } catch (IOException e) {
                    System.out.println(e.toString());
            }
        }
*/
        try {

            ObjectMapper m = new ObjectMapper();

            JsonNode root = m.readTree(new File("config.json"));
                System.out.println(root.getNodeType());  //lets see what type the node is //prints OBJECT
                System.out.println(root.isContainerNode()); //is it a container //prints true

            JsonNode secondroot = root.path("Printers");
                System.out.println(secondroot.getNodeType());  //prints ARRAY
                System.out.println(secondroot.isContainerNode());   //true

            for (JsonNode node : secondroot) {
                PrinterModel printer = new PrinterModel();
                printer.setModel(node.path("desc").asText());
                System.out.println(printer.getModel());

                JsonNode oidroot = node.path("oid");
                Iterator<String> fieldNames = oidroot.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    System.out.println(fieldName);
                }
                //printer.sayHello();
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        }
    }
}
