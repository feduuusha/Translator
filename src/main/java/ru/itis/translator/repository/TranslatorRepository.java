package ru.itis.translator.repository;

import ru.itis.translator.model.RequestData;

import java.util.List;

public interface TranslatorRepository {
    void saveRequest(RequestData requestData, List<String> translatedWords);
}
