package ru.umar.onlinestore.restapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    @Column(name = "username")
    private String username;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value = 2021, message = "Год рождения не долже быть больше 2021")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 6,message = "Пароль должен содержать больше 6 символов")
    private String password;

    @Column(name = "role")
    private String role;

    public User(String username, int yearOfBirth, String password) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
    }
}
