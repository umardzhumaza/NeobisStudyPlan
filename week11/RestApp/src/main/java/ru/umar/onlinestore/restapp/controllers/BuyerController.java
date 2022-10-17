package ru.umar.onlinestore.restapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.umar.onlinestore.restapp.models.Buyer;
import ru.umar.onlinestore.restapp.services.BuyerService;
import ru.umar.onlinestore.restapp.util.NotCreatedException;
import ru.umar.onlinestore.restapp.util.NotFoundException;
import ru.umar.onlinestore.restapp.util.ErrorResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping()
    public List<Buyer> getBuyers(){
        return buyerService.findAll(); //Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public Buyer getBuyer(@PathVariable("id") int id){
        //Status 200
        return buyerService.findOne(id); //Jackson конвертирует в JSON
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Buyer create(@RequestBody @Valid Buyer buyer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new NotCreatedException(errorMessage.toString());
        }

        // Отправляем http ответ с пустым телом и со статусом 200
        return buyerService.save(buyer);
    }

    @PutMapping("/update/{id}")
    public Buyer update(@PathVariable("id") int id, @RequestBody @Valid Buyer buyer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            throw new NotCreatedException(errorMessage.toString());
        }
            buyer.setId(id);
            return buyerService.update(buyer);
    }

    @DeleteMapping("/delete/{id}")
    public Buyer delete(@PathVariable("id") int id){
        ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);

        return buyerService.delete(id);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotFoundException e){
        ErrorResponse response = new ErrorResponse(
                "Buyer with this id wasn't found"
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
}
