package ru.starshindev.library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.starshindev.library.models.Person;
import ru.starshindev.library.services.PersonService;

@Component
public class PersonValidator implements Validator {
    private final PersonService service;

    public PersonValidator(PersonService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (service.findPerson(person.getUsername()).isPresent())
            errors.rejectValue("username","" ,"Человек с таким логином уже существует");
        if (service.findEmail(person.getEmail()).isPresent())
            errors.rejectValue("email","" ,"Человек с такой почтой уже существует");
    }
}
