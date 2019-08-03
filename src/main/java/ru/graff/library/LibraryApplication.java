package ru.graff.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "ru.graff.library.repository")
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LibraryApplication.class);
    }

}
