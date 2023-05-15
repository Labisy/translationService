package ru.org.translationservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.org.translationservice.service.SentenceService;

@RestController
@RequestMapping("/sentence")
public class SentenceController {
    private static final Logger LOGGER = Logger.getLogger(SentenceController.class);
    @Autowired
    private SentenceService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RequestMapping("/translate")
    @ResponseBody
    public String translate(HttpServletRequest request) {
        LOGGER.log(Level.INFO, "start SentenceController - getSentence");

        return service.getTranslate(request);
    }
}
