package ru.org.translationservice.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import ru.org.translationservice.type.DataType;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionService {
    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class);
    private static final String X_WM_CLIENT_ID = "X-WM-CLIENT-ID";
    private static final String X_WM_CLIENT_SECRET = "X-WM-CLIENT-SECRET";
    private static final String POST = "POST";
    private static final String CONTENT_TYPE = "Content-Type";

    private ConnectionService() {
    }

    /**
     * @return возвращает соединение с API переводчика
     */
    public static HttpURLConnection connectWithTranslator() {
        URL url = null;
        try {
            url = new URL(DataType.URL.get());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(POST);
            connection.setRequestProperty(X_WM_CLIENT_ID, DataType.CLIENT_ID.get());
            connection.setRequestProperty(X_WM_CLIENT_SECRET, DataType.CLIENT_SECRET.get());
            connection.setRequestProperty(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return connection;
        } catch (MalformedURLException e) {
            LOGGER.log(Level.ERROR, "URL Error", e);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return null;
    }
}