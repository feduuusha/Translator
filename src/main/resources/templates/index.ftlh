<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Переводчик</title>
    <link rel="stylesheet" href="styles.css"> <!-- Подключите свой CSS файл -->
</head>
<body>
<div class="container">
    <h1>Переводчик</h1>
    <form id="myForm" action="/translate" method="get">
        <div class="form-group">
            <label for="sourceLanguage">Выберите исходный язык:</label>
            <select id="sourceLanguage" name="sl">
                <option value="en">Английский</option>
                <option value="ru">Русский</option>
                <option value="fr">Французский</option>
                <option value="de">Немецкий</option>
            </select>
        </div>

        <div class="form-group">
            <label for="targetLanguage">Выберите целевой язык:</label>
            <select id="targetLanguage" name="tl">
                <option value="ru">Русский</option>
                <option value="en">Английский</option>
                <option value="fr">Французский</option>
                <option value="de">Немецкий</option>
            </select>
        </div>

        <div class="form-group">
            <label for="separator">Выберите разделитель слов:</label>
            <select id="separator" name="sp">
                <option value=" ">Пробел</option>
                <option value=":">Двоиточие</option>
                <option value=";">Точка с запятой</option>
                <option value=",">Запятая</option>
            </select>
        </div>

        <div class="form-group">
            <label for="textToTranslate">Введите слова для перевода:</label>
            <textarea id="textToTranslate" name="q" rows="4" cols="50" required></textarea>
        </div>

        <button type="submit">Перевести</button>
    </form>

    <#if translatedText?exists>
    <div class="result">
        <h2>Результат перевода:</h2>
        <p>${translatedText}</p>
    </div>
    </#if>

</div>
</body>
</html>