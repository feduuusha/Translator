package ru.itis.translator;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class TranslatorControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void indexTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors());
    }

    @Test
    public void translateTest() throws Exception {
        String url = "/translate?sl=en&tl=ru&sp=+&text=Hello+world%2C+this+is+my+first+program".replace("+", " ");
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("translatedText", "Привет мир%2C этот является мой первый программа"))
                .andExpect(MockMvcResultMatchers.model().attribute("sourceLanguage", "en"))
                .andExpect(MockMvcResultMatchers.model().attribute("targetLanguage", "ru"))
                .andExpect(MockMvcResultMatchers.model().attribute("separator", " "));
    }

}