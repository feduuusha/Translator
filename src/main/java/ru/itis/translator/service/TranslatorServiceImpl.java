package ru.itis.translator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.translator.entity.Request;
import ru.itis.translator.model.Response;
import ru.itis.translator.repository.TranslatorRepository;
import ru.itis.translator.service.util.TranslationTask;
import ru.itis.translator.service.url.URLService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranslatorServiceImpl implements TranslatorService {

    private final TranslatorRepository repository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ExecutorService executorService;
    private final URLService urlService;

    @Override
    public Response translateWords(String sourceLanguage, String targetLanguage, String text, String separator, String ipAddress) {
        String[] words = text.split(separator);
        List<Future<String>> futures = Arrays.stream(words)
                .map(word ->
                        executorService.submit(new TranslationTask(
                                urlService.buildUrl(sourceLanguage, targetLanguage, word),
                                restTemplate, objectMapper
                        )))
                .toList();

        String translatedText = String.join(" ", collectTranslations(futures));
        log.debug("Translation complete: " + translatedText);
        Request request = new Request(ipAddress, text, translatedText, sourceLanguage, targetLanguage);
        Response response = new Response(translatedText, sourceLanguage, targetLanguage, separator);
        repository.save(request);
        return response;
    }

    private List<String> collectTranslations(List<Future<String>> futures) {
        return futures.stream()
                .map(this::getTranslation)
                .toList();
    }

    private String getTranslation(Future<String> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error while translating", e);
            throw new IllegalStateException(e);
        }
    }
}