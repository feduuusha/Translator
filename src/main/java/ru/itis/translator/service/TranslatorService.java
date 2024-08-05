package ru.itis.translator.service;

import ru.itis.translator.model.RequestData;


public interface TranslatorService {
    String translateWords(RequestData requestData);
}
