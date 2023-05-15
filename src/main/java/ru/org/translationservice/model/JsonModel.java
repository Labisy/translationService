package ru.org.translationservice.model;

import lombok.Data;

@Data
public class JsonModel {
    private String fromLang;
    private String toLang;
    private String text;

    public String getJsonFormat() {
        return "{" +
                "\"fromLang\":\"" +
                fromLang +
                "\"," +
                "\"toLang\":\"" +
                toLang +
                "\"," +
                "\"text\":\"" +
                text +
                "\"" +
                "}";
    }

}