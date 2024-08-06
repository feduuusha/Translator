package ru.itis.translator.validator;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TranslatorValidatorImpl implements TranslatorValidator {
    @Override
    public void validateWords(String[] words) throws ValidationException {
        Pattern pattern = Pattern.compile("^[^@№&=+*{}<>%$#]+$");
        Matcher matcher;
        for (String word : words) {
            matcher = pattern.matcher(word);
            if (!matcher.matches()) {
                log.error("Bad Request: " + word + " not passed validation", new ValidationException());
                throw new ValidationException();
            }
        }
        log.debug("Validation complete: " + String.join(" ", words));
    }
}
