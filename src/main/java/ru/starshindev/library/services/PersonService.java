package ru.starshindev.library.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.starshindev.library.models.Person;
import ru.starshindev.library.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PeopleRepository repository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PeopleRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Person> findPerson(String login) {
        return repository.findPersonByUsername(login);
    }
    public Optional<Person> findEmail(String email) {
        return repository.findPersonByEmail(email);
    }

    public List<Person> getAll() {
        return repository.findAll();
    }
    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        repository.save(person);
    }
}
