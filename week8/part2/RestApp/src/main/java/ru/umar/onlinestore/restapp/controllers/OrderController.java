package ru.umar.onlinestore.restapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.umar.onlinestore.restapp.models.Order;
import ru.umar.onlinestore.restapp.services.OrderService;
import ru.umar.onlinestore.restapp.util.ErrorResponse;
import ru.umar.onlinestore.restapp.util.NotCreatedException;
import ru.umar.onlinestore.restapp.util.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Order> getOrders(){
        return orderService.findAll(); //Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") int id){
        //Status 200
        return orderService.findOne(id); //Jackson конвертирует в JSON
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestParam int buyer_id,
                                             @RequestParam int t_shirt_id,
                                             @RequestParam String order_date){

        orderService.save(t_shirt_id, buyer_id, order_date);
        // Отправляем http ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestParam(value = "buyer_id") int buyer_id,
                                             @RequestParam(value = "t_shirt_id") int t_shirt_id,
                                             @RequestParam(value = "order_date") String order_date){

        orderService.update(id, buyer_id, t_shirt_id, order_date);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        orderService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotFoundException e){
        ErrorResponse response = new ErrorResponse(
                "Order with this id wasn't found",
                System.currentTimeMillis()
        );
        //В Http ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //NOT_FOUND - 404 статус
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotCreatedException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        //В Http ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
