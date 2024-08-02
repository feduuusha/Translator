package ru.itis.translator.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.translator.models.RequestData;
import ru.itis.translator.services.TranslatorService;

@Controller
public class TranslatorController {
    private final TranslatorService service;

    @Autowired
    public TranslatorController(TranslatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/translate")
    public String translate(@RequestParam(value = "sl") final String sourceLanguage,
                            @RequestParam(value = "tl") final String targetLanguage,
                            @RequestParam(value = "text") final String text,
                            @RequestParam(value = "sp") final String separator,
                            HttpServletRequest request,
                            Model model) {
        RequestData requestData = new RequestData(sourceLanguage, targetLanguage, text.split(separator), request.getRemoteAddr());
        model.addAttribute("translatedText", service.translateWords(requestData));
        model.addAttribute("sourceLanguage", sourceLanguage);
        model.addAttribute("targetLanguage", targetLanguage);
        model.addAttribute("separator", separator);
        return "index";
    }
}
