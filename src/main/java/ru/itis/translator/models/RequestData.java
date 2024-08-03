package ru.itis.translator.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestData {
    @NotBlank
    private String sourceLanguage;
    @NotBlank
    private String targetLanguage;
    @Pattern(regexp = "^[^@№^&=+*{}<>%$#]*$")
    private String[] words;
    @Pattern(regexp = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$")
    private String ipAddress;
}
