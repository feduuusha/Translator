package ru.itis.translator.repository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Slf4j
@Repository
public class TranslatorRepositoryImpl implements TranslatorRepository {
    private static final String SQL_CREATE_TABLE = "create table if not exists requests (id serial primary key, ip varchar(45), " +
            "entered_text text, translated_text text, source_language varchar(10), target_language varchar(10));";
    private static final String SQL_INSERT_VALUES = "insert into requests (ip, entered_text, translated_text, source_language, target_language) VALUES (?, ?, ?, ?, ?)";
    @Value("${database.url}")
    private String url;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;

    @PostConstruct
    private void init() {
        try (Connection connection = getConnection();
             Statement createTableStatement = connection.createStatement()) {
            createTableStatement.executeUpdate(SQL_CREATE_TABLE);
            log.info("The \"requests\" table has been created or already exists");
        } catch (SQLException e) {
            log.error("Failed to initialize the database", e);
            throw new IllegalArgumentException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public void saveRequest(String sourceLanguage, String targetLanguage, String text, String translatedText, String ipAddress) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_VALUES)) {
            preparedStatement.setString(1, ipAddress);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, translatedText);
            preparedStatement.setString(4, sourceLanguage);
            preparedStatement.setString(5, targetLanguage);
            log.debug("Saving to the database complete");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to save request data", e);
            throw new IllegalStateException(e);
        }
    }
}
