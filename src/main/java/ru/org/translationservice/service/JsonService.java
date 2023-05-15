package ru.org.translationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonService {
    private static final Logger LOGGER = Logger.getLogger(JsonService.class);
    private JsonService(){}

    public static Map<String, String> requestToMap(HttpServletRequest request) {
        try {
            String json = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            return new ObjectMapper().readValue(json, HashMap.class);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return new HashMap<>();
    }

    public static String parserMapToJson(Map<String, String> jMap) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(jMap);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return json;
    }
}