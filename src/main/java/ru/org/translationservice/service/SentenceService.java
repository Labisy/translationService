package ru.org.translationservice.service;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.org.translationservice.entity.SentenceEntity;
import ru.org.translationservice.entity.WordEntity;
import ru.org.translationservice.repo.SentenceRepo;
import ru.org.translationservice.repo.WordRepo;
import ru.org.translationservice.service.hundler.TextHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SentenceService {
    @Autowired
    private SentenceRepo sentenceRepo;
    @Autowired
    private WordRepo wordRepo;

    public String getTranslate(HttpServletRequest request) {
        Map<String, String> jMap = JsonService.requestToMap(request);
        TextHandler converter = new TextHandler(jMap);
        List<String> words = converter.getTranslateSentence();
        String output = String.join(" ", words);
        SentenceEntity sentence = saveSentence(jMap, output);
        saveWords(words, sentence);

        return JsonService.parserMapToJson(new LinkedHashMap<>(Map.of(
                "ip", jMap.get("ip"), "output", output
        )));
    }

    private SentenceEntity saveSentence(Map<String, String> jMap, String output) {
        Date date = Calendar.getInstance().getTime();
        SentenceEntity sentence = new SentenceEntity();
        sentence.setDate(date);
        sentence.setIp(jMap.get("ip"));
        sentence.setParam(jMap.get("lang"));
        sentence.setInput(jMap.get("input"));
        sentence.setOutput(output);
        sentenceRepo.save(sentence);
        return sentence;
    }

    private void saveWords(List<String> words, SentenceEntity sentence) {
        WordEntity wordEntity = new WordEntity();
        for (String word : words) {
            wordEntity.setWord(word);
            wordEntity.setSentence(sentence);
            wordRepo.save(wordEntity);
        }
    }
}
