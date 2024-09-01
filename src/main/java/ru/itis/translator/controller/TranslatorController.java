package ru.itis.translator.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.translator.controller.payload.NewTranslateRequestPayload;
import ru.itis.translator.service.TranslatorService;

@Controller
@RequiredArgsConstructor
public class TranslatorController {

    private final TranslatorService service;

    @GetMapping
    public String getTranslate() {
        return "index";
    }

    @PostMapping
    public String translateText(@Valid NewTranslateRequestPayload payload,
                                BindingResult bindingResult,
                                HttpServletRequest request, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors",
                    bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList()
            );
        } else {
            model.addAttribute("response", service.translateWords(
                            payload.sourceLanguage(), payload.targetLanguage(),
                            payload.text(), payload.separator(), request.getRemoteAddr()
                    )
            );
        }
        return "index";
    }
}
