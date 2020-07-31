package ru.javabegin.training.javafx.service;

import com.google.common.collect.Lists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javabegin.training.javafx.entity.Person;
import ru.javabegin.training.javafx.repository.PersonRepository;

@Service
public class AddressBookService implements AddressBook {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void add(Person person) {
        personRepository.save(person);
    }

    @Override
    public void update(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public ObservableList<Person> findAll() {
        return FXCollections.observableArrayList(Lists.newArrayList(personRepository.findAll()));
    }

    @Override
    public ObservableList<Person> find(String text) {
        return FXCollections.observableArrayList(Lists.newArrayList(personRepository.findByFioContainingIgnoreCase(text)));
    }


}
