package RestApp.src.main.java.ru.umar.onlinestore.restapp.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class T_ShirtDTO {


    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 100 characters")
    private String name;


    @NotEmpty(message = "Color should not be empty")
    @Size(min = 2, max = 30, message = "Color should be between 2 and 30 characters")
    private String color;


    @Min(value = 0, message = "Price should be greater than 0")
    private int price;

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
}
