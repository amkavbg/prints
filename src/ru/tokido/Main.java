package ru.tokido;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //snmp hard worker =)
        SnmpQuerier snmpquerier = new SnmpQuerier();


        //читаем конфиг и делаем список из айпи принтеров
        List<String> list = new ArrayList<>();
        Scanner in = new Scanner(new File("ip.txt"));
        while (in.hasNextLine()) list.add(in.nextLine());

        //создаем объекты "принтер" с заполнеными пол€ми IP
        for (String p : list) {
            Printer printer = new Printer();
            printer.setIp(p);
            try {
                try {
                    snmpquerier.start();
                    //обращаемс€ к устройству и вы€сн€ем его модель из OID
                    printer.setModel(snmpquerier.send(p, "1.3.6.1.2.1.25.3.2.1.3.1"));
                    System.out.println(printer + "; ip=" + printer.getIp() + "; model=" + printer.getModel());
                } finally {
                    snmpquerier.stop(); }
            } catch (IOException e) {
                    System.out.println(e.toString());
            }
        }
        //writer.close();

    }

}

