package ru.org.translationservice.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class SentenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "param", nullable = false)
    private String param;
    @Column(name = "input_word", nullable = false)
    private String input;
    @Column(name = "translated_word", nullable = false)
    private String output;
    @Column(name = "date", nullable = false)
    private String ip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentence")
    private List<WordEntity> words;

    public SentenceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<WordEntity> getWords() {
        return words;
    }

    public void setWords(List<WordEntity> words) {
        this.words = words;
    }
}
