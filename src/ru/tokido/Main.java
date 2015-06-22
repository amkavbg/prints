package ru.tokido;

import java.io.IOException;

public class Main {

    private void test() throws IOException {
//        String r = SnmpQuerier.send(t, "1.3.6.1.2.1.25.3.2.1.3.1");
//        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main t = new Main();
        try {
            try {
//                SnmpQuerier.start();
                t.test();
            } finally {
//                SnmpQuerier.stop();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
