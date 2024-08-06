package ru.itis.translator.service.url;


public interface URLService {
    String buildUrl(String sourceLanguage, String targetLanguage, String word);
}
