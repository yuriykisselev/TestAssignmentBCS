## О приложении
Приложение для группировки акций по секторам
 - *Принимает на вход список акций*
 - *Возвращает рассчитанную стоимость акций по секторам*

## Загрузка
- Скачивание проекта через git
```bash
cd dir_for_project
git clone https://github.com/yuriykisselev/TestAssignmentBCS.git
```
- Скачивание проекта через браузер (zip файл)

## Сборка проекта
- Командой:
```bash
mvn clean install spring-boot:repackage
```
- Стандартный maven package 

## Запуск
- Запуск Application средствами IDE
- Запуск сгенерированного Jar (после сборки лежит в корне папки target)

## Проверка работы приложения
Использовать http запросы get или post **обязательно с телом (json)**
- Основной URL: http://localhost:8080/calculate
- Проверка получения данных из https://iexcloud.io/ через REST: 
http://localhost:8080/getMarketData

> Удобно использовать SoapUI или Insomnia

## Тестирование
- Запускается автоматически при сборке проекта
- Запускается вручную средствами IDE

## Контакты
Киселев Юрий Васильевич 
email: [yuriykisselev@gmail.com](yuriykisselev@gmail.com)

