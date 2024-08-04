package ru.itis.translator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.translator.models.RequestData;
import ru.itis.translator.repositories.TranslatorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TranslatorServiceImpl implements TranslatorService {
    private final TranslatorRepository repository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ExecutorService executorService;

    private static final String BASE_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&dt=t";

    @Autowired
    public TranslatorServiceImpl(TranslatorRepository repository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    @Override
    public String translateWords(RequestData requestData) {
        validateWords(requestData.getWords());
        List<Future<String>> futures = Arrays.stream(requestData.getWords())
                .map(word -> executorService.submit(new TranslationTask(buildUrl(requestData, word))))
                .collect(Collectors.toList());

        List<String> translatedWords = collectTranslations(futures);
        log.debug("Translation complete: " + String.join(" ", translatedWords));
        repository.saveRequest(requestData, translatedWords);
        return String.join(" ", translatedWords);
    }

    private static void validateWords(String[] words) {
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

    private String buildUrl(RequestData requestData, String word) {
        String completedURL = BASE_URL + "&sl=" + requestData.getSourceLanguage() +
                "&tl=" + requestData.getTargetLanguage() + "&q=" + word;
        log.debug("URL of the API request: " + completedURL);
        return completedURL;
    }

    private List<String> collectTranslations(List<Future<String>> futures) {
        return futures.stream()
                .map(this::getTranslation)
                .collect(Collectors.toList());
    }

    private String getTranslation(Future<String> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error while translating", e);
            throw new IllegalStateException(e);
        }
    }

    private class TranslationTask implements Callable<String> {
        private final String url;

        private TranslationTask(String url) {
            this.url = url;
        }

        @Override
        public String call() {
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
            if (entity.getStatusCode().is2xxSuccessful()) {
                return parseTranslation(entity.getBody());
            } else {
                log.error("Failed to fetch translation: " + entity.getStatusCode());
                throw new IllegalStateException();
            }
        }

        private String parseTranslation(String responseBody) {
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                log.debug("Successful API response: " + jsonNode.get(0).get(0).get(0).asText());
                return jsonNode.get(0).get(0).get(0).asText();
            } catch (JsonProcessingException e) {
                log.error("Error parsing translation response", e);
                throw new IllegalStateException(e);
            }
        }
    }
}