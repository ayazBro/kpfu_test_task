# Задание
____________________________
Необходимо выбрать любую предметную область и сформировать минимум 5 таблиц в этой
области и наполнить эти таблицы данными. Следом создать экранную форму, позволяющую делать
различные отборы по этим таблицам. Результат отбора должен быть представлен в формате JSON,
содержащим информацию из всех этих таблиц. Вывести результат на экран.

Примечание: Реализация данного задания возможна и не только через веб-форму. Можно написать
чистый бэкенд и проверку его тогда производить через PostMan.
____________________________
# Реализация
В качестве предметной области была выбрана сфера питания. Реализовал получение информации из таких сущносетй, как:
* клиент(client)
* продукт(product)
* готовое блюдо(dish)
* повар(cook)

Cущности взаимосвязаны и логичны между собой. Для реализации связи были использовны таблицы:
* order(таблица соотносила клиента и блюдо, которое он заказал)
* recept(таблица показывала из каких продуктов сделано блюдо)

### Диаграмма таблицы:
![Alt-тек](https://github.com/ayazBro/kpfu_test_task/blob/main/src/main/resources/client.png)

____________________________
# Стэк
* Java core
* Spring core
* Spring boot
* Hibernate
* Spring data JPA
* PostgreSQL

Примечание: все запросы выполнялись через Postman
____________________________
# Запуск
1. Создать базу данных jdbc:postgresql://localhost:5432/restoran
2. Открыть файл src/main/resources/Script.txt
3. Выполнить скрипт в созданной базе данных(ctrl C + ctrl V)
4. Открыть файл src/main/resources/application.properties
5. Ввести username и password

Примечание: подходит для PostgreSql
____________________________
# Примеры запросов
### Product
Поиск продукта по имени banana
* http://localhost:8080/v1/product?searchBy=name&value=banana

Вывод всех продуктов по возрастанию каллорийности
* http://localhost:8080/v1/product/products?category=kcal
### Cook
Поиск повара по имени Alice Waters
* http://localhost:8080/v1/cook?searchBy=name&value=Alice Waters

Вывод всех поваров по возрастанию рейтинга
* http://localhost:8080/v1/cook/cooks?category=rating

Вывод всех блюд повара с определенным id(id=1)
* http://localhost:8080/v1/cook/1/dishes

### Dish
Поиск блюда с названием chicken with potato
* http://localhost:8080/v1/dish?searchBy=name&value=chicken with potato

Вывод всех блюд по возрастанию цены
* http://localhost:8080/v1/dish/dishes?category=price

Вывод всех ингридиентов(продуктов) блюда с определенным id(id=2)
* http://localhost:8080/v1/dish/2/ingridients

Вывод автора блюда с определенным id(id=3)
* http://localhost:8080/v1/dish/3/cook

### Client
Поиск клиента с именем John
* http://localhost:8080/v1/client?searchBy=name&value=John

Вывод всех клиентов по возратсанию их бюджета
* http://localhost:8080/v1/client/clients?category=budget

Вывод всех блюд, которые заказал клиент с определенным id(id=3)
* http://localhost:8080/v1/client/3/dishes

