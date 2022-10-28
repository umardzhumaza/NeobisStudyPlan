package RestApp.src.main.java.ru.umar.onlinestore.restapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
