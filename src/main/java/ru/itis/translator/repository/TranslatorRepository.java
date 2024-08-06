package ru.itis.translator.repository;


public interface TranslatorRepository {
    void saveRequest(String sourceLanguage, String targetLanguage, String text, String translatedText, String ipAddress);
}
