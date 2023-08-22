# TestProjectAston

***
Разработайте и внедрите RESTful API для создания банковских счетов и взаимодействия с ними.

***
Варианты запуска приложения: 
1. с помощью Maven:
      mvn install
      java -jar target/demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar

2. С помощью Docker
     docker run -it $(docker build .)
3. Запустить через класс DemoApplication


Для запуска необходимо указать переменные окружения:
DB_CONN_URL=    
DB_CONN_USERNAME=    
DB_CONN_PASSWORD=
***

Скрипты создания базы находятся в файле db/scripts/init_schema.sql.
Можно запустить создание базы с помощью Docker Compose


***

Коллекция постман в файле New Collection.postman_collection.json