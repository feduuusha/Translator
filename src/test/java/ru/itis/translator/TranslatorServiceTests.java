package ru.itis.translator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.translator.models.RequestData;
import ru.itis.translator.services.TranslatorServiceImpl;

@SpringBootTest
public class TranslatorServiceTests {

    @Autowired
    @InjectMocks
    private TranslatorServiceImpl translatorService;


    @Test
    public void translateWordsTest() {
        RequestData data = new RequestData("ru", "en",
                new String[]{"привет", "мир"}, "127.0.0.1");
        Assertions.assertEquals("Hello world", translatorService.translateWords(data));
    }

    @Test
    public void incorrectTextInputTest() {
        RequestData data = new RequestData("ru", "en",
                new String[]{"#"}, "127.0.0.1");
        Assertions.assertThrows(IllegalStateException.class, () -> {translatorService.translateWords(data);});

    }
}
