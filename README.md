# Проект по автоматизации тестирования API для веб-приложения [Stellar Burgers](https://stellarburgers.nomoreparties.site)
<p align="center">
 <img width="45%" title="Book Store" src="images/logo.png">
 </p>
## Покрытый функционал

 > Разработаны автотесты на <code>API</code>.

 ### API

 - [x] Запросы <code>GET</code>, <code>POST</code>, <code>PATCH</code> и <code>DELETE</code><br>
       Создание пользователя:<br>
           • создание уникального пользователя<br>
           • создание пользователя, который уже зарегистрирован<br>
           • создание пользователя и не заполнение одно из обязательных полей<br>
       Логин пользователя:<br>
           • логин под существующим пользователем<br>
           • логин с неверным логином и паролем<br>
       Изменение данных пользователя:<br>
           • с авторизацией<br>
           • без авторизации<br>
       Создание заказа:<br>
           • с авторизацией<br>
           • без авторизации<br>
           • с ингредиентами<br>
           • без ингредиентов<br>
           • с неверным хешем ингредиентов<br>
       Получение заказов конкретного пользователя:<br>
           • авторизованный пользователь<br>
           • неавторизованный пользователь<br>
 - [x] Отображение и соответствие <code>statusCode</code> в ответе запроса
 - [x] Отображение и соответствие <code>body</code> в ответа запроса
 ## Отчет о результатах тестирования в Allure Report
 ### :dart: Главная страница Allure-отчета
 <p align="center">
 <img title="Allure_report" src="images/allure_report.png">
 </p>
 
