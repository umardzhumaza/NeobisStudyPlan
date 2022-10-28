package RestApp.src.main.java.ru.umar.onlinestore.restapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buyer")
public class Buyer {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 100 characters")
    private String name;
    @Column(name = "sur_name")
    @NotEmpty(message = "Sur name should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 100 characters")
    private String surName;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "phone_number")
    @NotEmpty(message = "Phone number should not be empty")
    private String phoneNumber;

    public Buyer(String name, String surName, String email, String phoneNumber) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
