package ru.umar.onlinestore.restapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.umar.onlinestore.restapp.dto.AuthenticationDTO;
import ru.umar.onlinestore.restapp.dto.UserDTO;
import ru.umar.onlinestore.restapp.models.User;
import ru.umar.onlinestore.restapp.services.RegistrationService;
import ru.umar.onlinestore.restapp.util.JWTUtil;
import ru.umar.onlinestore.restapp.util.UserValidator;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserValidator userValidator;
    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserValidator userValidator, RegistrationService registrationService, AuthenticationManager authenticationManager, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            return Map.of("Message", "Incorrect credentials");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")User user){
        return "auth/registration";
    }

    @PostMapping("registration")
    public Map<String, String> performRegistration(@RequestBody @Valid UserDTO userDTO,
                                                   BindingResult bindingResult){

        User user = convertToPerson(userDTO);
        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            return Map.of("message", "Ошибка!");
        }

        registrationService.register(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of("jwt-token", token);
    }

    public User convertToPerson(UserDTO userDTO){
        return this.modelMapper.map(userDTO, User.class);
    }
}
