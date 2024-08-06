package ru.itis.translator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.translator.repository.TranslatorRepository;
import ru.itis.translator.service.url.URLService;
import ru.itis.translator.validator.TranslatorValidator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TranslatorServiceImpl implements TranslatorService {
    private final TranslatorRepository repository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ExecutorService executorService;
    private final TranslatorValidator validator;
    private final URLService urlService;

    @Autowired
    public TranslatorServiceImpl(TranslatorRepository repository, RestTemplate restTemplate,
                                 ObjectMapper objectMapper, ExecutorService executorService,
                                 TranslatorValidator validator, URLService urlService) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.executorService = executorService;
        this.validator = validator;
        this.urlService = urlService;
    }

    @Override
    public String translateWords(String sourceLanguage, String targetLanguage, String text, String separator, String ipAddress) {
        String[] words = text.split(separator);
        validator.validateWords(words);
        List<Future<String>> futures = Arrays.stream(words)
                .map(word -> executorService.submit(
                        new TranslationTask(urlService.buildUrl(sourceLanguage, targetLanguage, word))))
                .collect(Collectors.toList());

        List<String> translatedWords = collectTranslations(futures);
        log.debug("Translation complete: " + String.join(" ", translatedWords));
        repository.saveRequest(sourceLanguage, targetLanguage, text, String.join(" ", translatedWords), ipAddress);
        return String.join(" ", translatedWords);
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