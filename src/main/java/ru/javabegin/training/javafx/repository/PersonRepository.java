package ru.javabegin.training.javafx.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.training.javafx.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findByFioContainingIgnoreCase(String fio);

}
