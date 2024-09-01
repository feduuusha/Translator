package ru.itis.translator.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.translator.entity.Request;

public interface TranslatorRepository extends JpaRepository<Request, Integer> {
}
