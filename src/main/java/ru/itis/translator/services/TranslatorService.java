package ru.itis.translator.services;

import ru.itis.translator.models.RequestData;


public interface TranslatorService {
    String translateWords(RequestData requestData);
}
