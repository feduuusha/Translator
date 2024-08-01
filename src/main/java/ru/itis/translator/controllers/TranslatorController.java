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
    public String products() {
        return "index";
    }
    @GetMapping("/translate")
    public String translate(@RequestParam(value = "sl") final String sl,
                            @RequestParam(value = "tl") final String tl,
                            @RequestParam(value = "q") final String q,
                            @RequestParam(value = "sp") final String sp,
                            HttpServletRequest request,
                            Model model) {
        RequestData requestData = new RequestData(sl, tl, q.split(sp), request.getRemoteAddr());
        model.addAttribute("translatedText", service.translateWords(requestData).toString());
        return "index";
    }
}
