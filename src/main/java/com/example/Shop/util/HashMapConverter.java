package com.example.Shop.util;

import ch.qos.logback.classic.Level;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;


@Converter
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> Info) {

        ObjectMapper objectMapper = new ObjectMapper();
        String InfoJson = null;
        try {
            InfoJson = objectMapper.writeValueAsString(Info);
        } catch (final JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        return InfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String InfoJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> Info = null;
        try {
            Info = objectMapper.readValue(InfoJSON, Map.class);
        } catch (final IOException e) {
            System.out.println(e.getMessage());
        }

        return Info;
    }

}