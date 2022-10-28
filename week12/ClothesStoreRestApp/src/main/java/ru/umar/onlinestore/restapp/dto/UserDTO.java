package RestApp.src.main.java.ru.umar.onlinestore.restapp.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    private String username;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value = 2021, message = "Год рождения не долже быть больше 2021")
    private int yearOfBirth;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 6,message = "Пароль должен содержать больше 6 символов")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
