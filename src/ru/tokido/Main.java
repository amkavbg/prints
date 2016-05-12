package ru.tokido;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
/*
        //snmp hard worker =)
        SnmpQuerier snmpquerier = new SnmpQuerier();

        //читаем конфиг и делаем список из айпи принтеров
        List<String> list = new ArrayList<>();
        Scanner in = new Scanner(new File("ip.txt"));
        while (in.hasNextLine()) list.add(in.nextLine());

        //создаем объекты "принтер" с заполнеными пол€ми IP
        for (String p : list) {
            PrinterModel printerModel = new PrinterModel();
            printerModel.setIp(p);
            try {
                try {
                    snmpquerier.start();
                    //обращаемс€ к устройству и вы€сн€ем его модель из OID
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
            PrinterModel printer = new PrinterModel();
            ObjectMapper m = new ObjectMapper();
            JsonNode rootNode = m.readTree(new File("config.json"));




        } catch (JsonGenerationException e) {
            e.printStackTrace();
        }
    }
}
