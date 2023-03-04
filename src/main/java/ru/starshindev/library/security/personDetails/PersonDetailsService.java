package ru.starshindev.library.security.personDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.starshindev.library.models.Person;
import ru.starshindev.library.repositories.PeopleRepository;

import java.util.Optional;
@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findPersonByUsername(username);
        if (person.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден");

        return new PersonDetails(person.get());
    }
}
