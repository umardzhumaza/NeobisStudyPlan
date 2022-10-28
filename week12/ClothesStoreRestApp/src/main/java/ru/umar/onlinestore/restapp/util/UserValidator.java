package RestApp.src.main.java.ru.umar.onlinestore.restapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import RestApp.src.main.java.ru.umar.onlinestore.restapp.models.User;
import RestApp.src.main.java.ru.umar.onlinestore.restapp.services.UserValidateService;

@Component
public class UserValidator implements Validator {

    private final UserValidateService userValidateService;

    @Autowired
    public UserValidator(UserValidateService userValidateService) {this.userValidateService = userValidateService;}

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            userValidateService.getUser(user).orElseThrow(Exception::new);
        } catch (Exception e) {
            return;
        }
        errors.rejectValue("username", "", "Человек с таким именем уже существует");
    }
}
