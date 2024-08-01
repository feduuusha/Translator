package ru.itis.translator.repositories;

import ru.itis.translator.models.RequestData;

import java.util.List;

public interface TranslatorRepository {
    void saveRequest(RequestData requestData, List<String> translatedWords);
}
