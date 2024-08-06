# Translator

Translator web application for selection at Tinkoff Fintech.

## How to use the application
1. Prepare a NEW postgreSQL DataBase
2. Run this command in Terminal: git clone https://github.com/feduuusha/Translator.git
3. Open project in IntelliJ IDEA Ultimate
4. Click the "Load Maven script" button that appears when you first open the project on the bottom right
5. Open the src/main/resources/application.properties file and change the values of database.url, database.username, database.password to the corresponding values of your postgreSQL DataBase
6. Run TranslatorApplication main method
7. Go to the browser using the link http://localhost:8080, enjoy!

### If you have any problems or questions, please, contact me: 

Telegram: @fedu_usha 

Mail: voropaevfedor2005@mail.ru
        
## About project

This application is based on the Spring Boot framework. 

The application is a simple translator. The translation takes place using the Google Translation API. It is possible to translate into 108 languages.

When creating the project, I tried to follow all the best practices. 

Interacted with the Google Translation API using Rest Template. Each word of the query is translated in a separate stream, the maximum number of streams is 10. 

The template engine was FreeMarker.

All user input is double-validated on the browser side and on the server side.

"@№&=+*{}<>%$#" these characters do not pass validation.

The project handles various errors such as 400, 404, 500 using GlobalExceptionHandler.

Data about user requests is saved to the PostgreSQL database. 

All HTML pages support a light and dark theme depending on the browser theme. 

Logging is well implemented in the project. 

Many tests have been written for each class of the project.

## License
Distributed under the MIT License. [Click](https://github.com/feduuusha/Translator/blob/main/LICENSE) for more information.
