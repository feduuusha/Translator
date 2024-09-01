package ru.itis.translator.service;


import ru.itis.translator.model.Response;

public interface TranslatorService {
    Response translateWords(String sourceLanguage, String targetLanguage, String text, String separator, String ipAddress);
}
