package ru.itis.translator;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TranslatorControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void indexTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("index"));
    }

    @Test
    public void translateTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/translate")
                        .param("sl", "en")
                        .param("tl", "ru")
                        .param("sp", " ")
                        .param("text", "Hello world, this is my first program")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attribute("translatedText", "Привет мир, этот является мой первый программа"))
                .andExpect(model().attribute("sourceLanguage", "en"))
                .andExpect(model().attribute("targetLanguage", "ru"))
                .andExpect(model().attribute("separator", " "))
                .andExpect(view().name("index"));
    }

    @Test
    public void translateTest2() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/translate")
                            .param("sl", "ru")
                            .param("tl", "en")
                            .param("sp", ":")
                            .param("text", "каждый:охотник:желает:знать:где:сидит:фазан")
                            .accept(MediaType.TEXT_HTML))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("translatedText", "every hunter wishes know Where is sitting pheasant"))
                    .andExpect(model().attribute("sourceLanguage", "ru"))
                    .andExpect(model().attribute("targetLanguage", "en"))
                    .andExpect(model().attribute("separator", ":"))
                    .andExpect(view().name("index"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}