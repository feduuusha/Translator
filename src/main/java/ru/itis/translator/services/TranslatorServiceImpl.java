package ru.itis.translator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.translator.models.RequestData;
import ru.itis.translator.repositories.TranslatorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
        List<Future<String>> futures = Arrays.stream(requestData.getWords())
                .map(word -> executorService.submit(new TranslationTask(buildUrl(requestData, word))))
                .collect(Collectors.toList());

        List<String> translatedWords = collectTranslations(futures);
        repository.saveRequest(requestData, translatedWords);
        return String.join(" ", translatedWords);
    }

    private String buildUrl(RequestData requestData, String word) {
        return BASE_URL + "&sl=" + requestData.getSourceLanguage() + "&tl=" + requestData.getTargetLanguage() + "&q=" + word;
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
            throw new IllegalStateException("Error while translating", e);
        }
    }

    private class TranslationTask implements Callable<String> {
        private final String url;

        public TranslationTask(String url) {
            this.url = url;
        }

        @Override
        public String call() {
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
            if (entity.getStatusCode().is2xxSuccessful()) {
                return parseTranslation(entity.getBody());
            } else {
                throw new IllegalStateException("Failed to fetch translation: " + entity.getStatusCode());
            }
        }

        private String parseTranslation(String responseBody) {
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                return jsonNode.get(0).get(0).get(0).asText();
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Error parsing translation response", e);
            }
        }
    }
}