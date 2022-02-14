package project.models;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    protected LocalDateDeserializer() {
        super(LocalDateTime.class);
    }


    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        // TODO Auto-generated method stub
        String input = p.getText().replace(" ", "T");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);

        return localDateTime;
    }

}