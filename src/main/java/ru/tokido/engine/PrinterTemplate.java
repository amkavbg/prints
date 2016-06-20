package ru.tokido.engine;

import java.util.HashMap;
import java.util.Map;

public class PrinterTemplate {

    private String Model = "none";
    //map relations between oid and description
    private Map<String, String> parameters = new HashMap<>();

    //mega default
    public PrinterTemplate(){

    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(String fieldName, String fieldValue) {
        parameters.put(fieldName,fieldValue);
    }
}
