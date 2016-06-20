package ru.tokido.engine;

/**
 * Created by tokido on 5/24/16.
 */
public enum ParametersKey {
    BLACK_TONER_LVL("BlackTonerLvl"),
    BLACK_DRUM_USAGE("BlackDrumUsg");

    private final String paramName;

    ParametersKey(String paramName) {
        this.paramName=paramName;
    }

    public String getParamName() {
        return paramName;
    }
}
