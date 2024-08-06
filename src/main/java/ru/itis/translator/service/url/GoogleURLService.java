package ru.itis.translator.service.url;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoogleURLService implements URLService {
    @Value("${api.base.url}")
    private String BASE_URL;

    @Override
    public String buildUrl(String sourceLanguage, String targetLanguage, String word) {
        String completedURL = BASE_URL + "&sl=" + sourceLanguage +
                "&tl=" + targetLanguage + "&q=" + word;
        log.debug("URL of the API request: " + completedURL);
        return completedURL;
    }
}
