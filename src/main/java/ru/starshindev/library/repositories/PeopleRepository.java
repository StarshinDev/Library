package ru.starshindev.library.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.starshindev.library.models.Person;
import ru.starshindev.library.models.Subscription;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByUsername(String username);
    Optional<Person> findPersonByEmail(String email);
    List<Person> findByFioContainingIgnoreCase(String name);
    @Modifying
    @Query("update Person p set p.subscription = ?1 where p.id = ?2")
    void updatePersonSubscription(Subscription sub, int id);
}
