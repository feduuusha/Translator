package ru.itis.translator.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
public class TranslationTask implements Callable<String> {

    private final String url;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

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