package ru.itis.translator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String translatedText;
    private String sourceLanguage;
    private String targetLanguage;
    private String separator;
}
