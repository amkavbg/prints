package ru.tokido;

import com.google.gson.*;
import org.w3c.dom.TypeInfo;

/**
 * Created by oparin_ag on 11.05.2016.
 */
public class PrinterDeserializer implements JsonDeserializer<Printer> {

    //@Override
    public Printer deserialize(final JsonElement json, final TypeInfo typeOfT,
                               final JsonDeserializationContext context) throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonElement jsonDescription = jsonObject.get("desc");

        final String desc = jsonDescription.getAsString();




        final Printer printer = new Printer();
        return printer;
    }
}
