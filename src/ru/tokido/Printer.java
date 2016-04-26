package ru.tokido;

import java.util.Map;

public class Printer {

    //general variables
    private String Ip = "none";
    private String Name = "none";
    private String Model = "none";
    //black variables
    private int BlackTonerLvl = 0;
    private int BlackDrumUsg = 0;
    //cyan variables
    private int CyanTonerLvl = 0;
    private int CyanDrumUsg = 0;
    //magenta variables
    private int MagentaTonerLvl = 0;
    private int MagentaDrumUsg = 0;
    //yellow variables
    private int YellowTonerLvl = 0;
    private int YellowDrumUsg = 0;
    //paper impression variables
    private int TotalPaperPrinted = 0;
    private int BlackPaperPrinted = 0;
    private int ColorPaperPrinted = 0;
    private int CopiesMade = 0;
    //other variables
    private int Fuser = 0;
    private int Belt = 0;
    //error variables
    private String Error = "none";
    private String Status = "none";
    //map relations between oid and description
    private Map<String, String> oidToDescription;

    //mega default
    public Printer(){}

    //default black
    public Printer(String ip, String name, int blackTonerLvl, String error, String status) {
        Ip = ip;
        Name = name;
        BlackTonerLvl = blackTonerLvl;
        Error = error;
        Status = status;
    }
    //default color
    public Printer(String ip, String name, int blackTonerLvl, int cyanTonerLvl, int magentaTonerLvl, int yellowTonerLvl, String error, String status) {
        Ip = ip;
        Name = name;
        BlackTonerLvl = blackTonerLvl;
        CyanTonerLvl = cyanTonerLvl;
        MagentaTonerLvl = magentaTonerLvl;
        YellowTonerLvl = yellowTonerLvl;
        Error = error;
        Status = status;
    }

    //getter and setter
    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getBlackTonerLvl() {
        return BlackTonerLvl;
    }

    public void setBlackTonerLvl(int blackTonerLvl) {
        BlackTonerLvl = blackTonerLvl;
    }

    public int getBlackDrumUsg() {
        return BlackDrumUsg;
    }

    public void setBlackDrumUsg(int blackDrumUsg) {
        BlackDrumUsg = blackDrumUsg;
    }

    public int getCyanTonerLvl() {
        return CyanTonerLvl;
    }

    public void setCyanTonerLvl(int cyanTonerLvl) {
        CyanTonerLvl = cyanTonerLvl;
    }

    public int getCyanDrumUsg() {
        return CyanDrumUsg;
    }

    public void setCyanDrumUsg(int cyanDrumUsg) {
        CyanDrumUsg = cyanDrumUsg;
    }

    public int getMagentaTonerLvl() {
        return MagentaTonerLvl;
    }

    public void setMagentaTonerLvl(int magentaTonerLvl) {
        MagentaTonerLvl = magentaTonerLvl;
    }

    public int getMagentaDrumUsg() {
        return MagentaDrumUsg;
    }

    public void setMagentaDrumUsg(int magentaDrumUsg) {
        MagentaDrumUsg = magentaDrumUsg;
    }

    public int getYellowTonerLvl() {
        return YellowTonerLvl;
    }

    public void setYellowTonerLvl(int yellowTonerLvl) {
        YellowTonerLvl = yellowTonerLvl;
    }

    public int getYellowDrumUsg() {
        return YellowDrumUsg;
    }

    public void setYellowDrumUsg(int yellowDrumUsg) {
        YellowDrumUsg = yellowDrumUsg;
    }

    public int getTotalPaperPrinted() {
        return TotalPaperPrinted;
    }

    public void setTotalPaperPrinted(int totalPaperPrinted) {
        TotalPaperPrinted = totalPaperPrinted;
    }

    public int getBlackPaperPrinted() {
        return BlackPaperPrinted;
    }

    public void setBlackPaperPrinted(int blackPaperPrinted) {
        BlackPaperPrinted = blackPaperPrinted;
    }

    public int getColorPaperPrinted() {
        return ColorPaperPrinted;
    }

    public void setColorPaperPrinted(int colorPaperPrinted) {
        ColorPaperPrinted = colorPaperPrinted;
    }

    public int getCopiesMade() {
        return CopiesMade;
    }

    public void setCopiesMade(int copiesMade) {
        CopiesMade = copiesMade;
    }

    public int getFuser() {
        return Fuser;
    }

    public void setFuser(int fuser) {
        Fuser = fuser;
    }

    public int getBelt() {
        return Belt;
    }

    public void setBelt(int belt) {
        Belt = belt;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Map<String, String> getOidToDescription() {
        return oidToDescription;
    }

    public void setOidToDescription(Map<String, String> oidToDescription) {
        this.oidToDescription = oidToDescription;
    }

    public void sayHello() { System.out.println(Ip); }
}
