package ru.tokido;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

public class Main {

    private Snmp snmp = null;
    private TransportMapping transport = null;

    private void test() throws IOException {
        Target t = getTarget("udp:172.16.3.230/161");
        String r = send(t, "1.3.6.1.2.1.25.3.2.1.3.1");
        System.out.println(r);
    }

    private void start() throws IOException {
        transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }

    private void stop() throws IOException {
        try {
            if (transport != null) {
                transport.close();
                transport = null;
            }
        } finally {
            if (snmp != null) {
                snmp.close();
                snmp = null;
            }
        }
    }

    public static void main(String[] args) {
	    Main t = new Main();
        try {
            try {
                t.start();
                t.test();
            } finally {
                t.stop();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
