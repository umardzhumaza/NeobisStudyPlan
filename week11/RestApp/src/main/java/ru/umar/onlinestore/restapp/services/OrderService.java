package ru.umar.onlinestore.restapp.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.umar.onlinestore.restapp.models.Buyer;
import ru.umar.onlinestore.restapp.models.Order;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.repositories.BuyerRepository;
import ru.umar.onlinestore.restapp.repositories.OrderRepository;
import ru.umar.onlinestore.restapp.repositories.T_ShirtRepository;
import ru.umar.onlinestore.restapp.util.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final T_ShirtRepository t_shirtRepository;
    private final BuyerRepository buyerRepository;

    public OrderService(OrderRepository orderRepository, T_ShirtRepository t_shirtRepository, BuyerRepository buyerRepository) {
        this.orderRepository = orderRepository;
        this.t_shirtRepository = t_shirtRepository;
        this.buyerRepository = buyerRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findOne(int id) {
        Optional<Order> foundTShirt = orderRepository.findById(id);
        return foundTShirt.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public ResponseEntity<String> save(int buyer_id, int t_shirt_id, String order_date){
        Optional<T_Shirt> t_shirt = t_shirtRepository.findById(t_shirt_id);
        Optional<Buyer> buyer = buyerRepository.findById(buyer_id);
        if(t_shirt.isEmpty() || buyer.isEmpty())
            return new ResponseEntity<>("T_Shirt or Buyer wasn't found!", HttpStatus.NOT_FOUND);

        Order order = new Order(buyer.get(), t_shirt.get(), order_date);
        orderRepository.save(order);
        return new ResponseEntity<>("Order was successfully saved!", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> save(int id, int buyer_id, int t_shirt_id, String order_date){
        Optional<T_Shirt> t_shirt = t_shirtRepository.findById(t_shirt_id);
        Optional<Buyer> buyer = buyerRepository.findById(buyer_id);
        if(t_shirt.isEmpty() || buyer.isEmpty())
            return new ResponseEntity<>("T_Shirt or Buyer wasn't found!", HttpStatus.NOT_FOUND);

        Order order = new Order(id, buyer.get(), t_shirt.get(), order_date);
        orderRepository.save(order);
        return new ResponseEntity<>("Order was successfully saved!", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> update(int id, int buyer_id, int t_shirt_id, String order_date){
        Optional<T_Shirt> t_shirt = t_shirtRepository.findById(t_shirt_id);
        Optional<Buyer> buyer = buyerRepository.findById(buyer_id);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty() || t_shirt.isEmpty() || buyer.isEmpty())
            return new ResponseEntity<>("Order wasn't found!", HttpStatus.NOT_FOUND);

        Order newOrder = new Order();
        newOrder.setId(id);
        newOrder.setBuyer(buyer.get());
        newOrder.setT_shirt(t_shirt.get());
        newOrder.setOrder_date(order_date);

        orderRepository.save(newOrder);
        return new ResponseEntity<>("Order was updated!", HttpStatus.OK);
    }

    @Transactional
    public Order delete(int id){
        Optional<Order> foundOrder = orderRepository.findById(id);
        if(foundOrder.isPresent())
            orderRepository.deleteById(id);

        return foundOrder.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteAll(){
        orderRepository.deleteAll();
    }
}

