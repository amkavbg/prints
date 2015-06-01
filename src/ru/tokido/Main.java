package ru.tokido;

import org.snmp4j.*;
import java.io.IOException;

public class Main {

    private void test() throws IOException {
        Target t = Worker.getTarget("udp:172.16.3.230/161");
        String r = Worker.send(t, "1.3.6.1.2.1.25.3.2.1.3.1");
        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main t = new Main();
        try {
            try {
                Worker.start();
                t.test();
            } finally {
                Worker.stop();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
