package ru.tokido;

public class Printer {
    private String ipDevice;
    private String nameDevice;
    private String blackToner;
    private String blackDrum;

    public void Printer(String ip) {
        ipDevice = ("udp:" + ip + "/161");
    }

    public String getNamedevice() {
        return nameDevice;
    }

    public String getIpdevice() {
        return ipDevice;
    }

    public String getBlackToner() {
        return blackToner;
    }

    public String getBlackDrum(){
        return blackDrum;
    }
}
