package ru.starshindev.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.starshindev.library.models.Subscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> getAllByAvailableIsTrue();
    Optional<Subscription> findByBooksCountIsAndSubscriptionCostIsAndIdIsNot(int count, float cost, int id);

}
