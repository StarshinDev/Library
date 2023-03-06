package ru.starshindev.library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.starshindev.library.models.Subscription;
import ru.starshindev.library.services.SubscriptionService;

@Component
public class SubscriptionValidator implements Validator {

    private final SubscriptionService subService;

    public SubscriptionValidator(SubscriptionService subService) {
        this.subService = subService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Subscription.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Subscription sub = (Subscription) target;
        if (subService.checkUnique(sub.getBooksCount(), sub.getSubscriptionCost(), sub.getId()).isPresent())
            errors.rejectValue("booksCount","" ,"Тип абонемента с подобным планом уже существует");
    }
}
