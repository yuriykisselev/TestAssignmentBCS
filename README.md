## Тестовое задание
[Описание тестового задания](bcs-test-assignment.md)

## О приложении
Приложение для группировки акций по секторам
 - *Принимает на вход список акций*
 - *Возвращает рассчитанную стоимость акций по секторам*

## Загрузка
* Скачивание проекта через git
```bash
cd dir_for_project
git clone https://github.com/yuriykisselev/TestAssignmentBCS.git
```
* Скачивание проекта через браузер (zip файл)

## Сборка проекта
* Командой:
```bash
gradle clean build
```
* Плагины IDE

## Запуск
* Запуск Application средствами IDE
* Запуск сгенерированного Jar (после сборки лежит в build/libs/rest-endpoint.jar)

## Проверка работы приложения
* Открыть swagger по адресу http://localhost:8080/swagger-ui.html и отправить запрос **обязательно с телом (json)** 

* Отправить POST запрос на url http://localhost:8080/calculate через любой инструмент 
> Удобно использовать Postman, SoapUI или Insomnia


## Контакты
Киселев Юрий Васильевич 
email: [yuriykisselev@gmail.com](yuriykisselev@gmail.com)

