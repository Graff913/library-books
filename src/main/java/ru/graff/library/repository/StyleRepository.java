package ru.graff.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.graff.library.domain.Style;

public interface StyleRepository extends JpaRepository<Style, Integer> {

    Style findByName(String name);

}
