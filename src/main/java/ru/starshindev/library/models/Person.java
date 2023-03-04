package ru.starshindev.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    @NotBlank(message = "Поле \"Логин\" не может быть пустым")
    @Size(min = 7, max = 30, message = "Логин должен иметь размер от 7 до 30 символов")
    private String username;

    @Column(name = "user_fio")
    @NotBlank(message = "Поле \"ФИО\" не может быть пустым")
    @Size(min = 3, max = 150, message = "ФИО должно иметь размер от 3 до 150 символов")
    private String fio;

    @Column(name = "user_email")
    @NotBlank(message = "Поле \"Почта\" не может быть пустым")
    @Email(message = "Неверный формат почты")
    @Size(min = 8, max = 100, message = "Почта должно иметь размер от 8 до 100 символов")
    private String email;

    @Column(name = "user_role")
    private String role;

    public Person() {
    }

    public Person(String email, String password, String name, String fio) {
        this.email = email;
        this.password = password;
        this.username = name;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
}
