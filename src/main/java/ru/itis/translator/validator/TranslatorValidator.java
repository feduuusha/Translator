package ru.itis.translator.validator;

import jakarta.validation.ValidationException;

public interface TranslatorValidator {
    void validateWords(String[] words) throws ValidationException;
}
