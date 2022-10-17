package ru.umar.onlinestore.restapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.umar.onlinestore.restapp.dto.T_ShirtDTO;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.services.T_ShirtService;
import ru.umar.onlinestore.restapp.util.ErrorResponse;
import ru.umar.onlinestore.restapp.util.NotCreatedException;
import ru.umar.onlinestore.restapp.util.NotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/t_shirt")
public class T_ShirtController {

    private final T_ShirtService t_shirtService;
    private final ModelMapper modelMapper;

    @Autowired
    public T_ShirtController(T_ShirtService t_shirtService, ModelMapper modelMapper) {
        this.t_shirtService = t_shirtService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<T_ShirtDTO> getT_Shirts(){
        return t_shirtService.findAll().stream().map(this::convertToTShirtDTO)
                .collect(Collectors.toList()); //Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public T_ShirtDTO getT_Shirt(@PathVariable("id") int id){
        //Status 200
        return convertToTShirtDTO(t_shirtService.findOne(id)); //Jackson конвертирует в JSON
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public T_Shirt create(@RequestBody @Valid T_ShirtDTO TShirtDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new NotCreatedException(errorMessage.toString());
        }
        // Отправляем http ответ с пустым телом и со статусом 200
        return t_shirtService.save(convertToTShirt(TShirtDTO));
    }



    @PutMapping("/update/{id}")
    public T_Shirt update(@PathVariable("id") int id, @RequestBody @Valid T_Shirt t_shirt, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new NotCreatedException(errorMessage.toString());
        }
        t_shirt.setId(id);
        return t_shirtService.update(t_shirt);
    }

    @DeleteMapping("/delete/{id}")
    public T_Shirt delete(@PathVariable("id") int id){
        return t_shirtService.delete(id);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotFoundException e){
        ErrorResponse response = new ErrorResponse(
                "T-Shirt with this id wasn't found"
        );
        //В Http ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //NOT_FOUND - 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotCreatedException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage()
        );
        //В Http ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private T_Shirt convertToTShirt(T_ShirtDTO tShirtDTO) {return modelMapper.map(tShirtDTO, T_Shirt.class);}
    private T_ShirtDTO convertToTShirtDTO(T_Shirt t_shirt){return modelMapper.map(t_shirt, T_ShirtDTO.class);}

}
