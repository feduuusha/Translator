package ru.itis.translator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "requests")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Request {
    public Request(String ip, String enteredText, String translatedText, String sourceLanguage, String targetLanguage) {
        this.ip = ip;
        this.enteredText = enteredText;
        this.translatedText = translatedText;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ip")
    private String ip;

    @Column(name = "entered_text", columnDefinition = "text")
    private String enteredText;

    @Column(name = "translated_text", columnDefinition = "text")
    private String translatedText;

    @Column(name = "source_language", length = 10)
    private String sourceLanguage;

    @Column(name = "target_language", length = 10)
    private String targetLanguage;
}
