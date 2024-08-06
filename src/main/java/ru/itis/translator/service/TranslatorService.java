package ru.itis.translator.service;


public interface TranslatorService {
    String translateWords(String sourceLanguage, String targetLanguage, String text, String separator, String ipAddress);
}
