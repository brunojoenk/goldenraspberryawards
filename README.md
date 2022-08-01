## Golden Raspberry Awards
- Heroku app link: https://goldenraspberryawards.herokuapp.com/

#### Tools
- [Java 11](https://docs.oracle.com/en/java/javase/11/);
- [Spring-Boot 2.7.2](https://spring.io/projects/spring-boot);
- [Apache Maven 3.6](https://maven.apache.org/ref/3.6.0/);
- [Banco H2](https://www.h2database.com/html/main.html);
- [JUnit 5](https://junit.org/junit5/);
- [Lombok](https://projectlombok.org/);
- [Opencsv](http://opencsv.sourceforge.net/);
- [Swagger](https://github.com/springfox/springfox#getting-started);

#### Run locally
```sh
mvn clean install
mvn spring-boot:run
```

#### Endpoints
- http://localhost:8080 - Root
- http://localhost:8080/h2-console - H2 Database
- http://localhost:8080/awards/movies/min-max - API to get min max interval of producers winners
- http://localhost:8080/swagger-ui/index.html#/movie-controller - Swagger - All APIs from MovieController