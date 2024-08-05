package ru.itis.translator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestData {
    private String sourceLanguage;
    private String targetLanguage;
    private String[] words;
    private String ipAddress;
}
