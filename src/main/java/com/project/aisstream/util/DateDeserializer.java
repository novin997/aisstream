package com.project.aisstream.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateDeserializer extends StdDeserializer<Date> {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<Date> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JacksonException {
        try {
            String data = parser.getText();
            data = data.substring(0, 23);
            System.out.println(data);
            return df.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
