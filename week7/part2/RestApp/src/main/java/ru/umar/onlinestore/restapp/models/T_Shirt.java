package ru.umar.onlinestore.restapp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "t_shirt")
public class T_Shirt {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 100 characters")
    private String name;

    @Column(name = "color")
    @NotEmpty(message = "Color should not be empty")
    @Size(min = 2, max = 30, message = "Color should be between 2 and 30 characters")
    private String color;

    @Column(name = "price")
    @Min(value = 0, message = "Price should be greater than 0")
    private int price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_who")
    private String createdWho;

    public T_Shirt() {

    }

    public T_Shirt(String name, String color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
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

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}

    public String getCreatedWho() {return createdWho;}

    public void setCreatedWho(String createdWho) {this.createdWho = createdWho;}
}
