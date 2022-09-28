package ru.umar.onlinestore.restapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.repositories.T_ShirtRepository;
import ru.umar.onlinestore.restapp.services.T_ShirtService;
import ru.umar.onlinestore.restapp.util.T_ShirtErrorResponse;
import ru.umar.onlinestore.restapp.util.T_ShirtNotCreatedException;
import ru.umar.onlinestore.restapp.util.T_ShirtNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/t_shirt")
public class T_ShirtController {
    private final T_ShirtService TShirtService;
    @Autowired
    private T_ShirtRepository t_shirtRepository;

    @Autowired
    public T_ShirtController(T_ShirtService TShirtService) {
        this.TShirtService = TShirtService;
    }

    @GetMapping()
    public List<T_Shirt> getT_Shirts(){
        return TShirtService.findAll(); //Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public T_Shirt getT_Shirt(@PathVariable("id") int id){
        //Status 200
        return TShirtService.findOne(id); //Jackson конвертирует в JSON
    }

    @PostMapping()
    public T_Shirt create(@RequestBody @Valid T_Shirt TShirt, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new T_ShirtNotCreatedException(errorMessage.toString());
        }
        TShirtService.save(TShirt);
        // Отправляем http ответ с пустым телом и со статусом 200
        return TShirt;
    }

    @PutMapping("/update/{id}")
    public T_Shirt update(@PathVariable("id") int id, @RequestBody @Valid T_Shirt t_shirt, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new T_ShirtNotCreatedException(errorMessage.toString());
        }
        t_shirt.setId(id);

        return t_shirtRepository.save(t_shirt);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        t_shirtRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<T_ShirtErrorResponse> handleException(T_ShirtNotFoundException e){
        T_ShirtErrorResponse response = new T_ShirtErrorResponse(
                "T-Shirt with this id wasn't found",
                System.currentTimeMillis()
        );
        //В Http ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //NOT_FOUND - 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<T_ShirtErrorResponse> handleException(T_ShirtNotCreatedException e){
        T_ShirtErrorResponse response = new T_ShirtErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        //В Http ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
