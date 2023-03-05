package ru.starshindev.library.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.starshindev.library.models.Person;
import ru.starshindev.library.services.PeopleService;
import ru.starshindev.library.services.SubscriptionService;

import java.util.List;

@Controller
@RequestMapping("/library/users")
public class PeopleController {

    private final PeopleService peopleService;
    private final SubscriptionService subService;

    public PeopleController(PeopleService peopleService, SubscriptionService subService) {
        this.peopleService = peopleService;
        this.subService = subService;
    }

    @GetMapping("/all")
    public String allUsers(@RequestParam(value = "filter", required = false, defaultValue = "") String name, Model model) {
        List<Person> users;
        if(!name.isBlank())
            users = peopleService.getAllByName(name);
        else users = peopleService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("filter", name);
        return "app/users/all";
    }

    @GetMapping()
    public String user(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details  = (UserDetails) auth.getPrincipal();
        model.addAttribute("userInfo", peopleService.findPerson(details.getUsername()).get());
        return "app/users/userInfo";
    }

    @GetMapping("/subscribe/{subId}")
    public String subscribe(@PathVariable("subId") int id, Model model) {
//        Person person  = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails details  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("balance", peopleService.findPerson(details.getUsername()).get().getBalance());
        model.addAttribute("subInfo", subService.getSubscriptionById(id));
        return "app/users/paymentPage";
    }

    @PatchMapping("/subscribe/{subId}")
    public String addSubscription(@PathVariable("subId") int id) {
//        Person person  = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails details  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int personId = peopleService.findPerson(details.getUsername()).get().getId();
        peopleService.setSubscription(subService.getSubscriptionById(id), personId);
        return "redirect:/library/users";
    }
}
