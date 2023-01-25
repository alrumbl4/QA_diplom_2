# Проект по автоматизации тестирования API для веб-приложения [Stellar Burgers](https://stellarburgers.nomoreparties.site)
<p align="center">
 <img width="45%" title="Book Store" src="images/logo.png">
 </p>
 
## Покрытый функционал

 > Разработаны автотесты на <code>API</code>.

 ### API

 - [x] Запросы <code>GET</code>, <code>POST</code>, <code>PATCH</code> и <code>DELETE</code>:
     - [x] Создание пользователя:
          - создание уникального пользователя 
          - создание пользователя, который уже зарегистрирован
          - создание пользователя и не заполнение одно из обязательных полей
     - [x] Логин пользователя:
          - логин под существующим пользователем
          - логин с неверным логином и паролем
     - [x] Изменение данных пользователя:
          - с авторизацией
          - без авторизации
     - [x] Создание заказа:
          - с авторизацией
          - без авторизации
          - с ингредиентами
          - без ингредиентов
          - с неверным хешем ингредиентов
     - [x] Получение заказов конкретного пользователя:
          - авторизованный пользователь
          - неавторизованный пользователь
 - [x] Отображение и соответствие <code>statusCode</code> в ответе запроса
 - [x] Отображение и соответствие <code>body</code> в ответа запроса				
 ## Отчет о результатах тестирования в Allure Report
 ### :dart: Главная страница Allure-отчета
 <p align="center">
 <img title="Allure_report" src="images/allure_report.png">
 </p>
 
 ## <img width="4%" title="Technologies" src="images/techno_logo.png"> Технологический стек
 
<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/java_logo.svg">
<img width="6%" title="Java" src="images/Java.svg">
<img width="6%" title="Allure Report" src="images/allure_logo.svg">
<img width="6%" title="Maven" src="images/maven_logo.png">
<img width="6%" title="JUnit4" src="images/junit_logo.png">
<img width="6%" title="GitHub" src="images/github_logo.svg">
<img width="6%" title="Rest Assured" src="images/rest_logo.svg">
</p>

> В данном проекте автотесты написаны на <code>Java</code> с использованием библиотеки <code>REST Assured</code> для API-тестов.
>
> <code>Allure Report</code> формирует отчет о запуске тестов.
>
> Для автоматизированной сборки проекта используется <code>Maven</code>.
>
> В качестве библиотеки для модульного тестирования используется <code>JUnit 4</code>.
>
