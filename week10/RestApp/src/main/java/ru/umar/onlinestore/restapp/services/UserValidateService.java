package ru.umar.onlinestore.restapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.umar.onlinestore.restapp.models.User;
import ru.umar.onlinestore.restapp.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserValidateService {
    private final UserRepository userRepository;

    @Autowired
    public UserValidateService(UserRepository userRepository) {this.userRepository = userRepository;}

    public Optional<User> getUser(User user){
        Optional<User> optionalPerson = userRepository.findByUsername(user.getUsername());
        return optionalPerson;
    }
}
