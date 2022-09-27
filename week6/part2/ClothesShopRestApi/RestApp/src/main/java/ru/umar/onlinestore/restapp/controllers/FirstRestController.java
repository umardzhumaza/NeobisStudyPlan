package ru.umar.onlinestore.restapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Теперь над каждым методом есть @ResponseBody + @Controller
@RequestMapping("/api")
public class FirstRestController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello world!";
    }
}
