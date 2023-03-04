package ru.starshindev.library.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.starshindev.library.models.Person;
import ru.starshindev.library.services.PersonService;
import ru.starshindev.library.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PersonService service;

    public AuthController(PersonValidator personValidator, PersonService service) {
        this.personValidator = personValidator;
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("newPerson") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("newPerson") @Valid Person person,
                               BindingResult result) {
        personValidator.validate(person, result);
        if (result.hasErrors()) {
            return "auth/registration";
        }
        service.register(person);
        return "redirect:/auth/login";
    }
}
