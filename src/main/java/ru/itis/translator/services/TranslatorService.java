package ru.itis.translator.services;

import ru.itis.translator.models.RequestData;

import java.util.List;

public interface TranslatorService {
    String translateWords(RequestData requestData);
}
