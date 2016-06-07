package ru.tokido;

import java.io.IOException;
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
    //private int TonerLvlRepr = 0;

    private SnmpQuerier snmpq;

    //mega default
    public Printer(PrinterTemplate template, String ip,SnmpQuerier snmpq){
        this.template=template;
        this.ip =ip;
        this.snmpq=snmpq;
    }

    public void recognize () throws IOException {
        Map<String,String> paramToOid = template.getParameters();
        for (Map.Entry<String,String> entry : paramToOid.entrySet()) {
            String wv = "none";
            String key = entry.getKey();
            String oid = entry.getValue();

            if (oid.equals(wv)) {
                //System.out.println("Key: "+key+", Val: "+wv);
                paramValues.put(key,wv);
            }
            else {
                //String oid = entry.getValue();
                String oidValue = snmpq.send(ip, oid);
                //System.out.println("Key: "+key+", Val: "+oidValue);
                paramValues.put(key, oidValue);
            }
        }

        //TODO:write private method for TonerLVL (use P=A\B*100%) applicable to printer, who usage 2 OID for toner lvl

        String max;
        String cur;
        for (Map.Entry<String,String> entry : paramValues.entrySet()){
            String maxkey = "BlackTonerLevelMAX";
            String curkey = "BlackTonerLevelCurrent";

            String key = entry.getKey();
            String parm = entry.getValue();

            if (key.equals(maxkey)) max = parm;
            if (key.equals(curkey)) cur = parm;
        }
        //String p = cur / max * 100;
        paramValues.put("BlackTonerLevel",)
        //System.out.println("recognize!      " + template);

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
