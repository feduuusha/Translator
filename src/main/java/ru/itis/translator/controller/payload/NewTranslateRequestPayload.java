package ru.itis.translator.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewTranslateRequestPayload(
        @NotBlank(message = "{translator.sourceLanguage.isBlanc}")
        @Size(max = 10, message = "{translator.language.sizeIsNotValid}")
        String sourceLanguage,
        @NotBlank(message = "{translator.targetLanguage.isBlanc}")
        @Size(max = 10, message = "{translator.language.sizeIsNotValid}")
        String targetLanguage,
        @Size(min = 1, max = 1, message = "{translator.separator.sizeIsNotValid}")
        String separator,
        @NotBlank(message = "{translator.text.IsBlank}")
        @Size(max = 1000, message = "{translator.text.sizeIsNotValid}")
        @Pattern(regexp = "^[^@№&^=+*{}'\\[\\]<>%$#]+$", message = "{translator.text.IsNotValidText}")
        String text
) {
}
