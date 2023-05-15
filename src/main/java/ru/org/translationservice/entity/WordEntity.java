package ru.org.translationservice.entity;

import jakarta.persistence.*;

@Entity
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "word", nullable = false)
    private String word;
    @ManyToOne
    @JoinColumn(name = "id_sentence")
    private SentenceEntity sentence;

    public WordEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public SentenceEntity getSentence() {
        return sentence;
    }

    public void setSentence(SentenceEntity sentence) {
        this.sentence = sentence;
    }
}
