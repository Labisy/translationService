package ru.org.translationservice.type;

public enum DataType {
    CLIENT_ID("FREE_TRIAL_ACCOUNT"),
    CLIENT_SECRET("PUBLIC_SECRET"),
    URL("http://api.whatsmate.net/v1/translation/translate");
    private String info;

    DataType(String info) {
        this.info = info;
    }

    public String get() {
        return info;
    }
}
