package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final BasketService basketService;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder, BasketService basketService) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.basketService = basketService;
    }

    public Person findByLogin(Person person){
        Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
        return person_db.orElse(null);
    }
    public Person findByName(String name){
        return personRepository.findByLogin(name).orElseThrow();
    }
    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        person.setBasket(basketService.createBasket());
        personRepository.save(person);
    }


}
