package RestApp.src.main.java.ru.umar.onlinestore.restapp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_shirt")
public class T_Shirt {
    @Id
    @Column(name = "id")
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

    public T_Shirt(String name, String color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public T_Shirt(int id, String name, String color, int price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
    }
}
