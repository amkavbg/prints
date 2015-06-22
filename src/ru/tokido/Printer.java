package ru.tokido;

import java.util.Map;

public class Printer {
    private String deviceIp;
    private String deviceName;
    private Map<String, String> oidToDescription;

    public void Printer(String deviceIp,String deviceName) {
        this.deviceIp = deviceIp;
        this.deviceName = deviceName;
    }

    public void addOid(String oid, String description){
        oidToDescription.put(oid,description);
    }

    public String getDeviceName(){
        return nameDevice;
    }

    public String getDeviceIp(){
        return ipDevice;
    }

}
