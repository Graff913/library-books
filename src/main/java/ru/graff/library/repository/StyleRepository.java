package ru.graff.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.graff.library.domain.Style;

public interface StyleRepository extends MongoRepository<Style, String> {

    Style findByName(String name);

}
