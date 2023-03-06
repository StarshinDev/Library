package ru.starshindev.library.services;

import org.springframework.stereotype.Service;
import ru.starshindev.library.models.Subscription;
import ru.starshindev.library.repositories.SubscriptionRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subRepo;

    public SubscriptionService(SubscriptionRepository subRepo) {
        this.subRepo = subRepo;
    }

    public List<Subscription> getAll() {
        return subRepo.getAllByAvailableIsTrue();
    }

    public Subscription getSubscriptionById(int id) {
        return subRepo.findById(id).orElse(null);
    }

    public void saveSubscription(Subscription sub) {
        sub.setChangeTime(new Date());
        sub.setAvailable(true);
        subRepo.save(sub);
    }

    public void updateSubscription(Subscription sub) {
        sub.setChangeTime(new Date());
        subRepo.save(sub);
    }

    public void deleteSubscription(int id) {
        Subscription sub = getSubscriptionById(id);
        if (sub.getPeople() == null)
            subRepo.deleteById(id);
        else {
            sub.setAvailable(false);
            updateSubscription(sub);
        }
    }

    public Optional<Subscription> checkUnique(int count, float cost, int id) {
        return subRepo.findByBooksCountIsAndSubscriptionCostIsAndIdIsNot(count, cost, id);
    }
}
