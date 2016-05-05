package ru.tokido;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Printer> printerArrayList = new ArrayList<Printer>();
        SnmpQuerier snmpquerier = new SnmpQuerier();
        //OrganizePrint organizePrint = new OrganizePrint();
        Gson gson = new GsonBuilder().create();
        Writer writer = new OutputStreamWriter(new FileOutputStream("Output.json"),"UTF-8");

        //читаем конфиги
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
                    gson.toJson(printer.getIp().toString(), writer);
                    gson.toJson(printer.getModel().toString(), writer);

                } finally {
                    snmpquerier.stop();
                                                                            }
            } catch (IOException e) {
                    System.out.println(e.toString());
            }
        }
        writer.close();

    }

}

