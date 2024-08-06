package ru.itis.translator.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.translator.service.TranslatorService;

@Slf4j
@Controller
public class TranslatorController {
    private final TranslatorService service;

    @Autowired
    public TranslatorController(TranslatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        log.debug("Request for " + request.getRequestURI() + " from IP:" + request.getRemoteAddr());
        return "index";
    }

    @Validated
    @GetMapping("/translate")
    public String translate(@RequestParam(value = "sl") @NotBlank final String sourceLanguage,
                            @RequestParam(value = "tl") @NotBlank final String targetLanguage,
                            @RequestParam(value = "text") @NotBlank final String text,
                            @RequestParam(value = "sp") @Length(min = 1, max = 1) final String separator,
                            HttpServletRequest request, Model model) {
        log.debug("Request for " + request.getRequestURI() + "?" + request.getQueryString() + " from IP:" + request.getRemoteAddr());
        model.addAttribute("translatedText",
                service.translateWords(sourceLanguage, targetLanguage, text, separator, request.getRemoteAddr()));
        model.addAttribute("sourceLanguage", sourceLanguage);
        model.addAttribute("targetLanguage", targetLanguage);
        model.addAttribute("separator", separator);
        return "index";
    }
}
