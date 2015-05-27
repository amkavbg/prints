package ru.tokido;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

public class Main {

    private final static String SNMP_COMMUNITY = "public";
    private final static int SNMP_RETRIES = 3;
    private final static long SNMP_TIMEOUT = 1000L;

    private Snmp snmp = null;
    private TransportMapping transport = null;

    private void test() throws IOException {
        Target t = getTarget("udp:172.16.3.230/161");
        String r = send(t, "1.3.6.1.2.1.25.3.2.1.3.1");
        System.out.println(r);
    }

    private String send (Target target, String oid) throws IOException {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);
        ResponseEvent event = snmp.send(pdu, target, null);
        if (event != null) {
            return event.getResponse().get(0).toString();
        } else {
            return "Timeout exceeded";
        }
    }

    private Target getTarget(String address) {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(SNMP_COMMUNITY));
        target.setAddress(targetAddress);
        target.setRetries(SNMP_RETRIES);
        target.setTimeout(SNMP_TIMEOUT);
        target.setVersion(SnmpConstants.version1);
        return target;
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
