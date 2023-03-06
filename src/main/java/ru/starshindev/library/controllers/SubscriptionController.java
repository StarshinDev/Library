package ru.starshindev.library.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.starshindev.library.models.Subscription;
import ru.starshindev.library.services.SubscriptionService;
import ru.starshindev.library.util.SubscriptionValidator;

@Controller
@RequestMapping("/library/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subService;
    private final SubscriptionValidator subValidator;

    public SubscriptionController(SubscriptionService subService, SubscriptionValidator subValidator) {
        this.subService = subService;
        this.subValidator = subValidator;
    }

    @GetMapping("/all")
    public String allSub(Model model) {
        model.addAttribute("subscriptions", subService.getAll());
        return "app/subscription/all";
    }

    @GetMapping("/new")
    public String newSub(@ModelAttribute("newSubscription") Subscription sub) {
        return "app/subscription/new";
    }

    @GetMapping("/{subId}/edit")
    public String editSub(@PathVariable("subId") int id, Model model) {
        model.addAttribute("subscription", subService.getSubscriptionById(id));
        return "app/subscription/edit";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("newSubscription") @Valid Subscription sub,
                         BindingResult result) {
        subValidator.validate(sub, result);
        if (result.hasErrors())
            return "app/subscription/new";
        subService.saveSubscription(sub);
        return "redirect:/library/subscriptions/all";
    }

    @PatchMapping
    public String patchSub(@ModelAttribute("subscription") @Valid Subscription sub,
                           BindingResult result) {
        subValidator.validate(sub, result);
        if (result.hasErrors())
            return "app/subscription/edit";
        subService.updateSubscription(sub);
        return "redirect:/library/subscriptions/all";
    }

    @DeleteMapping("/{subId}")
    public String deleteSub(@PathVariable("subId") int id) {
        subService.deleteSubscription(id);
        return "redirect:/library/subscriptions/all";
    }
}
