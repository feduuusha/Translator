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
public class TranslatorExceptionHandlerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void error404Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/something").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void error400BlankTextTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/translate?sl=en&tl=ru&sp=+&text=").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void error400NoRequestParameterTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/translate?sl=en&tl=ru&text=100").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void error500Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/translate?sl=en&tl=hui&sp=5&text=123").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }
}
