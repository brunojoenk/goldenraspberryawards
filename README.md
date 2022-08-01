## goldenraspberryawards
- Heroku app link: https://goldenraspberryawards.herokuapp.com/

#### To deploy on Heroku
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

#### Tools
- [Java 11](https://docs.oracle.com/en/java/javase/11/);
- [Spring-Boot 2.7.2](https://spring.io/projects/spring-boot);
- [Apache Maven 3.6](https://maven.apache.org/ref/3.6.0/);
- [Banco H2](https://www.h2database.com/html/main.html);
- [JUnit 5](https://junit.org/junit5/);
- [Lombok](https://projectlombok.org/);

#### Run locally
```sh
mvn clean install
```

#### Endpoints
- http://localhost:8080 - Root
- http://localhost:8080/h2-console - H2 Database
- http://localhost:8080/awards/movies/min-max - API to get min max interval of producers winners
