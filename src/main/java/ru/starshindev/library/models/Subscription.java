package ru.starshindev.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subscription_name")
    @NotBlank(message = "Поле \"Название\" не может быть пустым")
    @Size(min = 5, max = 30, message = "Название должно иметь размер от 5 до 30 символов")
    private String name;

    @Column(name = "book_count")
    @NotNull(message = "Поле \"Количество книг\" не может быть пустым")
    @Max(value = 100, message = "Количество должно быть не больше 100")
    private int booksCount;

    @Column(name = "subscription_cost")
    @NotNull(message = "Поле \"Стоимость\" не может быть пустым")
    @Max(value = 50000, message = "Стоимость не должна превышать 50000.00")
    private float subscriptionCost;

    @Column(name = "last_change_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeTime;

    @Column(name = "available")
    private boolean available;

    @OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY)
    private List<Person> people;

    public Subscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(int booksCount) {
        this.booksCount = booksCount;
    }

    public float getSubscriptionCost() {
        return subscriptionCost;
    }

    public void setSubscriptionCost(float subscriptionCost) {
        this.subscriptionCost = subscriptionCost;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
