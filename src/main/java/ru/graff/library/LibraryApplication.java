package ru.graff.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LibraryApplication.class);
    }

}
