package ru.itis.translator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.translator.models.RequestData;
import ru.itis.translator.repositories.TranslatorRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class TranslatorServiceImpl implements TranslatorService{
    private final TranslatorRepository repository;

    @Autowired
    public TranslatorServiceImpl(TranslatorRepository repository) {
        this.repository = repository;
    }

    private static final String BASE_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&dt=t";
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    @Override
    public String translateWords(RequestData requestData) {
        String url = BASE_URL + "&sl=" + requestData.getSourceLanguage() + "&tl=" + requestData.getTargetLanguage();
        List<Task> tasks = new LinkedList<>();
        for (String word : requestData.getWords()) {
            tasks.add(new Task(url + "&q=" + word));
        }
        try {
            List<Future<String>> futures = executor.invokeAll(tasks);
            List<String> translatedWords = futures.stream().map(Future::resultNow).toList();
            repository.saveRequest(requestData, translatedWords);
            return String.join(" ", translatedWords);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Task implements Callable<String> {
        private final String url;

        public Task(String url) {
            this.url = url;
        }

        @Override
        public String call() {
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
            if (entity.getStatusCode().is2xxSuccessful()) {
                try {
                    return mapper.readTree(entity.getBody()).get(0).get(0).get(0).asText();
                } catch (JsonProcessingException e) {
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
