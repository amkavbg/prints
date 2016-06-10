package ru.tokido;

import org.snmp4j.smi.OctetString;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tokido on 23.05.2016.
 */
public class Printer {
    private Map<String,String> paramValues=new HashMap<>();
    private String ip = "00:00:00:00:00";
    private PrinterTemplate template;
    private SnmpQuerier snmpq;

    public Printer(PrinterTemplate template, String ip,SnmpQuerier snmpq){
        this.template=template;
        this.ip =ip;
        this.snmpq=snmpq;
    }

    public void recognize () throws IOException {
        String maxkey = "BlackTonerLevelMAX";
        String curkey = "BlackTonerLevelCurrent";
        Map<String, String> paramToOid = template.getParameters();
        for (Map.Entry<String, String> entry : paramToOid.entrySet()) {
            String wv = "none";
            String key = entry.getKey();
            String oid = entry.getValue();
            if (oid.equals(wv)) {
                paramValues.put(key, wv);
            } else {
                String oidValue = snmpq.send(ip, oid);
                paramValues.put(key, oidValue);
            }
        }
        String max = null;
        String cur = null;
        for (Map.Entry<String, String> entry : paramValues.entrySet()) {
            String key = entry.getKey();
            String parm = entry.getValue();
            if (key.equals(maxkey)) max = parm;
            if (key.equals(curkey)) cur = parm;
        }
        realtonerlvl(max,cur);
    }

    private void realtonerlvl (String m, String c) {
        if (m != null && c != null) {
            float a = new Float(c);
            float b = new Float(m);
            float p = ((a / b) * 100);
            paramValues.put("BlackTonerLevel", String.valueOf((int)p));
        }
        else {}
    }

    public String getValueByKey(String key){
        return paramValues.get(key);
    }

    public Set<String> getParamKeys(){
        return paramValues.keySet();
    }

    public String getIp() {
        return ip;
    }

    public String getModel(){
        return template.getModel();
    }
}
