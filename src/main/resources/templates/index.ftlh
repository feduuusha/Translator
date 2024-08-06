<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Переводчик</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <a href="/">
        <h1 id="main-ref">Переводчик</h1>
    </a>
    <form id="myForm" action="/translate" method="get">
        <div class="form-group">
            <label for="sourceLanguage">Выберите исходный язык:</label>
            <select id="sourceLanguage" name="sl">
                <option aria-label="Азербайджанский" value="az">Азербайджанский</option><option aria-label="Албанский" value="sq">Албанский</option><option aria-label="Амхарский" value="am">Амхарский</option><option aria-label="Английский" value="en" selected="selected">Английский</option><option aria-label="Арабский" value="ar">Арабский</option><option aria-label="Армянский" value="hy">Армянский</option><option aria-label="Ассамский" value="as">Ассамский</option><option aria-label="Африкаанс" value="af">Африкаанс</option><option aria-label="Башкирский" value="ba">Башкирский</option><option aria-label="Бенгальский" value="bn">Бенгальский</option><option aria-label="Бирманский" value="my">Бирманский</option><option aria-label="Болгарский" value="bg">Болгарский</option><option aria-label="Боснийский" value="bs">Боснийский</option><option aria-label="Валлийский" value="cy">Валлийский</option><option aria-label="Венгерский" value="hu">Венгерский</option><option aria-label="Вьетнамский" value="vi">Вьетнамский</option><option aria-label="Гаитянский" value="ht">Гаитянский</option><option aria-label="Галисийский" value="gl">Галисийский</option><option aria-label="Греческий" value="el">Греческий</option><option aria-label="Грузинский" value="ka">Грузинский</option><option aria-label="Гуджарати" value="gu">Гуджарати</option><option aria-label="Датский" value="da">Датский</option><option aria-label="Зулу" value="zu">Зулу</option><option aria-label="Иврит" value="he">Иврит</option><option aria-label="Игбо" value="ig">Игбо</option><option aria-label="Индонезийский" value="id">Индонезийский</option><option aria-label="Ирландский" value="ga">Ирландский</option><option aria-label="Исландский" value="is">Исландский</option><option aria-label="Испанский" value="es">Испанский</option><option aria-label="Итальянский" value="it">Итальянский</option><option aria-label="Йоруба" value="yo">Йоруба</option><option aria-label="Казахский" value="kk">Казахский</option><option aria-label="Каннада" value="kn">Каннада</option><option aria-label="Кантонский (Традиционная Китайская)" value="yue">Кантонский (Традиционная Китайская)</option><option aria-label="Каталанский" value="ca">Каталанский</option><option aria-label="Киньяруанда" value="rw">Киньяруанда</option><option aria-label="Киргизский" value="ky">Киргизский</option><option aria-label="Китайский (Традиционная Китайская)" value="zh-Hant">Китайский (Традиционная Китайская)</option><option aria-label="Китайский (Упрощенная Китайская)" value="zh-Hans">Китайский (Упрощенная Китайская)</option><option aria-label="Корейский" value="ko">Корейский</option><option aria-label="Коса" value="xh">Коса</option><option aria-label="Курдский (Северная)" value="kmr">Курдский (Северная)</option><option aria-label="Курдский (Центральная)" value="ku">Курдский (Центральная)</option><option aria-label="Кхмерский" value="km">Кхмерский</option><option aria-label="Лаосский" value="lo">Лаосский</option><option aria-label="Латышский" value="lv">Латышский</option><option aria-label="Лингала" value="ln">Лингала</option><option aria-label="Литовский" value="lt">Литовский</option><option aria-label="Майтхили" value="mai">Майтхили</option><option aria-label="Македонский" value="mk">Македонский</option><option aria-label="Малагасийский" value="mg">Малагасийский</option><option aria-label="Малайский" value="ms">Малайский</option><option aria-label="Малаялам" value="ml">Малаялам</option><option aria-label="Мальдивский" value="dv">Мальдивский</option><option aria-label="Мальтийский" value="mt">Мальтийский</option><option aria-label="Маори" value="mi">Маори</option><option aria-label="Маратхи" value="mr">Маратхи</option><option aria-label="Немецкий" value="de">Немецкий</option><option aria-label="Непальский" value="ne">Непальский</option><option aria-label="Нидерландский" value="nl">Нидерландский</option><option aria-label="Норвежский Букмол" value="nb">Норвежский Букмол</option><option aria-label="Ория" value="or">Ория</option><option aria-label="Панджаби" value="pa">Панджаби</option><option aria-label="Персидский" value="fa">Персидский</option><option aria-label="Польский" value="pl">Польский</option><option aria-label="Португальский (Бразилия)" value="pt">Португальский (Бразилия)</option><option aria-label="Португальский (Португалия)" value="pt-PT">Португальский (Португалия)</option><option aria-label="Пушту" value="ps">Пушту</option><option aria-label="Румынский" value="ro">Румынский</option><option aria-label="Русский" value="ru">Русский</option><option aria-label="Самоанский" value="sm">Самоанский</option><option aria-label="Сербский (Кириллица)" value="sr-Cyrl">Сербский (Кириллица)</option><option aria-label="Сербский (Латиница)" value="sr-Latn">Сербский (Латиница)</option><option aria-label="Сингальский" value="si">Сингальский</option><option aria-label="Синдхи" value="sd">Синдхи</option><option aria-label="Словацкий" value="sk">Словацкий</option><option aria-label="Словенский" value="sl">Словенский</option><option aria-label="Сомали" value="so">Сомали</option><option aria-label="Суахили" value="sw">Суахили</option><option aria-label="Таитянский" value="ty">Таитянский</option><option aria-label="Тайский" value="th">Тайский</option><option aria-label="Тамильский" value="ta">Тамильский</option><option aria-label="Татарский" value="tt">Татарский</option><option aria-label="Телугу" value="te">Телугу</option><option aria-label="Тибетский" value="bo">Тибетский</option><option aria-label="Тигринья" value="ti">Тигринья</option><option aria-label="Тонганский" value="to">Тонганский</option><option aria-label="Турецкий" value="tr">Турецкий</option><option aria-label="Туркменский" value="tk">Туркменский</option><option aria-label="Узбекский" value="uz">Узбекский</option><option aria-label="Уйгурский" value="ug">Уйгурский</option><option aria-label="Украинский" value="uk">Украинский</option><option aria-label="Урду" value="ur">Урду</option><option aria-label="Фарерский" value="fo">Фарерский</option><option aria-label="Фиджи" value="fj">Фиджи</option><option aria-label="Филиппинский" value="fil">Филиппинский</option><option aria-label="Финский" value="fi">Финский</option><option aria-label="Французский" value="fr">Французский</option><option aria-label="Французский (Канада)" value="fr-CA">Французский (Канада)</option><option aria-label="Хауса" value="ha">Хауса</option><option aria-label="Хинди" value="hi">Хинди</option><option aria-label="Хорватский" value="hr">Хорватский</option><option aria-label="Чешский" value="cs">Чешский</option><option aria-label="Шведский" value="sv">Шведский</option><option aria-label="Шона" value="sn">Шона</option><option aria-label="Эстонский" value="et">Эстонский</option><option aria-label="Юкатекский Майя" value="yua">Юкатекский Майя</option><option aria-label="Японский" value="ja">Японский</option>
            </select>
        </div>

        <div class="form-group">
            <label for="targetLanguage">Выберите целевой язык:</label>
            <select id="targetLanguage" name="tl">
                <option aria-label="Азербайджанский" value="az">Азербайджанский</option><option aria-label="Албанский" value="sq">Албанский</option><option aria-label="Амхарский" value="am">Амхарский</option><option aria-label="Английский" value="en">Английский</option><option aria-label="Арабский" value="ar">Арабский</option><option aria-label="Армянский" value="hy">Армянский</option><option aria-label="Ассамский" value="as">Ассамский</option><option aria-label="Африкаанс" value="af">Африкаанс</option><option aria-label="Башкирский" value="ba">Башкирский</option><option aria-label="Бенгальский" value="bn">Бенгальский</option><option aria-label="Бирманский" value="my">Бирманский</option><option aria-label="Болгарский" value="bg">Болгарский</option><option aria-label="Боснийский" value="bs">Боснийский</option><option aria-label="Валлийский" value="cy">Валлийский</option><option aria-label="Венгерский" value="hu">Венгерский</option><option aria-label="Вьетнамский" value="vi">Вьетнамский</option><option aria-label="Гаитянский" value="ht">Гаитянский</option><option aria-label="Галисийский" value="gl">Галисийский</option><option aria-label="Греческий" value="el">Греческий</option><option aria-label="Грузинский" value="ka">Грузинский</option><option aria-label="Гуджарати" value="gu">Гуджарати</option><option aria-label="Датский" value="da">Датский</option><option aria-label="Зулу" value="zu">Зулу</option><option aria-label="Иврит" value="he">Иврит</option><option aria-label="Игбо" value="ig">Игбо</option><option aria-label="Индонезийский" value="id">Индонезийский</option><option aria-label="Ирландский" value="ga">Ирландский</option><option aria-label="Исландский" value="is">Исландский</option><option aria-label="Испанский" value="es">Испанский</option><option aria-label="Итальянский" value="it">Итальянский</option><option aria-label="Йоруба" value="yo">Йоруба</option><option aria-label="Казахский" value="kk">Казахский</option><option aria-label="Каннада" value="kn">Каннада</option><option aria-label="Кантонский (Традиционная Китайская)" value="yue">Кантонский (Традиционная Китайская)</option><option aria-label="Каталанский" value="ca">Каталанский</option><option aria-label="Киньяруанда" value="rw">Киньяруанда</option><option aria-label="Киргизский" value="ky">Киргизский</option><option aria-label="Китайский (Традиционная Китайская)" value="zh-Hant">Китайский (Традиционная Китайская)</option><option aria-label="Китайский (Упрощенная Китайская)" value="zh-Hans">Китайский (Упрощенная Китайская)</option><option aria-label="Корейский" value="ko">Корейский</option><option aria-label="Коса" value="xh">Коса</option><option aria-label="Курдский (Северная)" value="kmr">Курдский (Северная)</option><option aria-label="Курдский (Центральная)" value="ku">Курдский (Центральная)</option><option aria-label="Кхмерский" value="km">Кхмерский</option><option aria-label="Лаосский" value="lo">Лаосский</option><option aria-label="Латышский" value="lv">Латышский</option><option aria-label="Лингала" value="ln">Лингала</option><option aria-label="Литовский" value="lt">Литовский</option><option aria-label="Майтхили" value="mai">Майтхили</option><option aria-label="Македонский" value="mk">Македонский</option><option aria-label="Малагасийский" value="mg">Малагасийский</option><option aria-label="Малайский" value="ms">Малайский</option><option aria-label="Малаялам" value="ml">Малаялам</option><option aria-label="Мальдивский" value="dv">Мальдивский</option><option aria-label="Мальтийский" value="mt">Мальтийский</option><option aria-label="Маори" value="mi">Маори</option><option aria-label="Маратхи" value="mr">Маратхи</option><option aria-label="Немецкий" value="de">Немецкий</option><option aria-label="Непальский" value="ne">Непальский</option><option aria-label="Нидерландский" value="nl">Нидерландский</option><option aria-label="Норвежский Букмол" value="nb">Норвежский Букмол</option><option aria-label="Ория" value="or">Ория</option><option aria-label="Панджаби" value="pa">Панджаби</option><option aria-label="Персидский" value="fa">Персидский</option><option aria-label="Польский" value="pl">Польский</option><option aria-label="Португальский (Бразилия)" value="pt">Португальский (Бразилия)</option><option aria-label="Португальский (Португалия)" value="pt-PT">Португальский (Португалия)</option><option aria-label="Пушту" value="ps">Пушту</option><option aria-label="Румынский" value="ro">Румынский</option><option aria-label="Русский" value="ru" selected="selected">Русский</option><option aria-label="Самоанский" value="sm">Самоанский</option><option aria-label="Сербский (Кириллица)" value="sr-Cyrl">Сербский (Кириллица)</option><option aria-label="Сербский (Латиница)" value="sr-Latn">Сербский (Латиница)</option><option aria-label="Сингальский" value="si">Сингальский</option><option aria-label="Синдхи" value="sd">Синдхи</option><option aria-label="Словацкий" value="sk">Словацкий</option><option aria-label="Словенский" value="sl">Словенский</option><option aria-label="Сомали" value="so">Сомали</option><option aria-label="Суахили" value="sw">Суахили</option><option aria-label="Таитянский" value="ty">Таитянский</option><option aria-label="Тайский" value="th">Тайский</option><option aria-label="Тамильский" value="ta">Тамильский</option><option aria-label="Татарский" value="tt">Татарский</option><option aria-label="Телугу" value="te">Телугу</option><option aria-label="Тибетский" value="bo">Тибетский</option><option aria-label="Тигринья" value="ti">Тигринья</option><option aria-label="Тонганский" value="to">Тонганский</option><option aria-label="Турецкий" value="tr">Турецкий</option><option aria-label="Туркменский" value="tk">Туркменский</option><option aria-label="Узбекский" value="uz">Узбекский</option><option aria-label="Уйгурский" value="ug">Уйгурский</option><option aria-label="Украинский" value="uk">Украинский</option><option aria-label="Урду" value="ur">Урду</option><option aria-label="Фарерский" value="fo">Фарерский</option><option aria-label="Фиджи" value="fj">Фиджи</option><option aria-label="Филиппинский" value="fil">Филиппинский</option><option aria-label="Финский" value="fi">Финский</option><option aria-label="Французский" value="fr">Французский</option><option aria-label="Французский (Канада)" value="fr-CA">Французский (Канада)</option><option aria-label="Хауса" value="ha">Хауса</option><option aria-label="Хинди" value="hi">Хинди</option><option aria-label="Хорватский" value="hr">Хорватский</option><option aria-label="Чешский" value="cs">Чешский</option><option aria-label="Шведский" value="sv">Шведский</option><option aria-label="Шона" value="sn">Шона</option><option aria-label="Эстонский" value="et">Эстонский</option><option aria-label="Юкатекский Майя" value="yua">Юкатекский Майя</option><option aria-label="Японский" value="ja">Японский</option>
            </select>
        </div>

        <div class="form-group">
            <label for="separator">Выберите разделитель слов:</label>
            <select id="separator" name="sp">
                <option value=" ">Пробел</option>
                <option value=":">Двоеточие</option>
                <option value=";">Точка с запятой</option>
                <option value=",">Запятая</option>
            </select>
        </div>

        <div class="form-group">
            <label for="textToTranslate">Введите слова для перевода:</label>
            <textarea id="textToTranslate" name="text" rows="4" cols="50" required="" placeholder="Введите слова здесь..." style="margin-top: 0; margin-bottom: 0; height: 66px; border: 1px solid rgb(204, 204, 204);"></textarea>
        </div>

        <button type="submit">Перевести</button>
    </form>

    <#if translatedText??>
        <div class="result">
            <h2>Результат перевода:</h2>
            <p id="translate">${translatedText}</p>

        </div>

        <button class="copyButton" id="copyButton">Скопировать в буфер обмена</button>
    </#if>


    <#if sourceLanguage??>
        <script>
            const objSelect1 = document.getElementById("sourceLanguage");
            const langCode1 = "${sourceLanguage}";
            objSelect1.value = langCode1;
        </script>
    </#if>

    <#if targetLanguage??>
        <script>
            const objSelect2 = document.getElementById("targetLanguage");
            const langCode2 = "${targetLanguage}";
            objSelect2.value = langCode2;
        </script>
    </#if>

    <#if separator??>
        <script>
            const objSelect3 = document.getElementById("separator");
            const separator = "${separator}";
            objSelect3.value = separator;
        </script>
    </#if>

    <script>
        document.getElementById('textToTranslate').addEventListener('input', function() {
            const regex = /^[^@№^&=+*{}<>%$#]*$/;
            const textarea = document.getElementById('textToTranslate');
            if (!regex.test(textarea.value)) {
                textarea.style.border = '4px solid red';
            } else {
                textarea.style.border = '1px solid #ccc';
            }
        })
        document.getElementById('myForm').addEventListener('submit', function(event) {
            const textarea = document.getElementById('textToTranslate')
            const regex = /^[^@№^&=+*{}<>%$#]*$/;

            if (!regex.test(textarea.value)) {
                textarea.style.border = '4px solid red';
                event.preventDefault();
                alert("Введены недопустимые символы")
            } else {
                textarea.style.border = '1px solid #ccc';
            }
        });
    </script>

    <script>
        <#if translatedText??>
            document.getElementById('copyButton').addEventListener('click', function() {
                const textarea = document.getElementById('translate');
                navigator.clipboard.writeText(textarea.textContent).then(function() {
                    alert('Текст скопирован в буфер обмена!');
                }, function(err) {
                    console.error('Ошибка при копировании текста: ', err);
                });
            });
        </#if>
    </script>

</div>

</body>
</html>