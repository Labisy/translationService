package ru.org.translationservice.service.hundler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.org.translationservice.model.JsonModel;
import ru.org.translationservice.service.ConnectionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TextHandler {
    private static final Logger LOGGER = Logger.getLogger(TextHandler.class);
    public static final String MANY_SPACES = "\\s+";
    public static final String REGEX = "\\pP";
    public static final String REPLACEMENT = " ";
    private final Map<String, String> jMap;
    private final JsonModel jsonModel;

    public TextHandler(Map<String, String> jMap) {
        this.jMap = jMap;
        this.jsonModel = new JsonModel();
        createJsonModel();
    }

    private String deletePunctuationMarks() {
        return jMap.get("input").replaceAll(REGEX, REPLACEMENT).replaceAll(MANY_SPACES, REPLACEMENT);
    }

    private List<String> stringToList() {
        String s = deletePunctuationMarks();
        return new ArrayList<>(List.of(s.split(REPLACEMENT)));
    }

    public List<String> getTranslateSentence() {
        return stringToList().stream()
                .map(this::translate)
                .toList();
    }

    private String translate(String word) {
        jsonModel.setText(word);
        String jsonPayload = jsonModel.getJsonFormat();

        HttpURLConnection connection = ConnectionService.connectWithTranslator();
        try {

            OutputStream os = Objects.requireNonNull(connection).getOutputStream();
            os.write(jsonPayload.getBytes());
            os.flush();
            os.close();

            BufferedReader rb = checkResponseCode(connection.getResponseCode(), connection);
            StringBuilder stringBuilder = new StringBuilder();
            String sub;
            while ((sub = rb.readLine()) != null) {
                stringBuilder.append(sub);
            }

            connection.disconnect();
            return stringBuilder.toString();
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "TextConverter - translate ", e);
            throw new RuntimeException(e);
        }
    }

    private BufferedReader checkResponseCode(int responseCode, HttpURLConnection connection) throws IOException {
        return new BufferedReader(new InputStreamReader(
                (responseCode == 200) ? connection.getInputStream() : connection.getErrorStream()
        ));
    }

    private void createJsonModel() {
        String[] lang = jMap.get("lang").split("-");
        jsonModel.setFromLang(lang[0]);
        jsonModel.setToLang(lang[1]);
    }
}

