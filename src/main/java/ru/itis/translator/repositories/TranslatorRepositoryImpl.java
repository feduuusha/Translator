package ru.itis.translator.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.itis.translator.models.RequestData;

import java.sql.*;
import java.util.List;

@Repository
public class TranslatorRepositoryImpl implements TranslatorRepository {
    private static final String SQL_CREATE_TABLE = "create table requests (id serial primary key, ip varchar(45), " +
            "entered_text text, translated_text text);";
    private static final String SQL_DROP_TABLE = "drop table if exists requests";
    private static final String SQL_INSERT_VALUES = "insert into requests (ip, entered_text, translated_text) VALUES (?, ?, ?)";
    @Value("${database.url}")
    private String url;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;

    @PostConstruct
    private void init() {
        try (Connection connection = getConnection();
             Statement deleteTableStatement = connection.createStatement();
             PreparedStatement createTableStatement = connection.prepareStatement(SQL_CREATE_TABLE)) {
            deleteTableStatement.executeUpdate(SQL_DROP_TABLE);
            createTableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public void saveRequest(RequestData requestData, List<String> translatedWords) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_VALUES)) {
            preparedStatement.setString(1, requestData.getIp());
            preparedStatement.setString(2, String.join(" ", requestData.getWords()));
            preparedStatement.setString(3, String.join(" ", translatedWords));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
