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
import ru.umar.onlinestore.restapp.repositories.T_ShirtRepository;
import ru.umar.onlinestore.restapp.services.T_ShirtService;
import ru.umar.onlinestore.restapp.util.T_ShirtErrorResponse;
import ru.umar.onlinestore.restapp.util.T_ShirtNotCreatedException;
import ru.umar.onlinestore.restapp.util.T_ShirtNotFoundException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/t_shirt")
public class T_ShirtController {

    private final T_ShirtService TShirtService;
    private final ModelMapper modelMapper;

    @Autowired
    public T_ShirtController(T_ShirtService TShirtService, ModelMapper modelMapper) {
        this.TShirtService = TShirtService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<T_ShirtDTO> getT_Shirts(){
        return TShirtService.findAll().stream().map(this::convertToTShirtDTO)
                .collect(Collectors.toList()); //Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public T_ShirtDTO getT_Shirt(@PathVariable("id") int id){
        //Status 200
        return convertToTShirtDTO(TShirtService.findOne(id)); //Jackson конвертирует в JSON
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid T_ShirtDTO TShirtDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new T_ShirtNotCreatedException(errorMessage.toString());
        }
        TShirtService.save(convertToTShirt(TShirtDTO));
        // Отправляем http ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid T_Shirt t_shirt, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new T_ShirtNotCreatedException(errorMessage.toString());
        }
        t_shirt.setId(id);
        TShirtService.save(t_shirt);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        TShirtService.delete(id);

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

    private T_Shirt convertToTShirt(T_ShirtDTO tShirtDTO) {return modelMapper.map(tShirtDTO, T_Shirt.class);}
    private T_ShirtDTO convertToTShirtDTO(T_Shirt t_shirt){return modelMapper.map(t_shirt, T_ShirtDTO.class);}

}
