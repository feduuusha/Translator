package ru.itis.translator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TranslatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslatorApplication.class, args);
	}

}
