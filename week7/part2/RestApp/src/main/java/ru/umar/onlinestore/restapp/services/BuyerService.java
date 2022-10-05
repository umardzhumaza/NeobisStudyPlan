package ru.umar.onlinestore.restapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.umar.onlinestore.restapp.models.Buyer;
import ru.umar.onlinestore.restapp.repositories.BuyerRepository;
import ru.umar.onlinestore.restapp.util.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BuyerService {

    private final BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    public Buyer findOne(int id) {
        Optional<Buyer> foundTShirt = buyerRepository.findById(id);
        return foundTShirt.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void save(Buyer buyer){
        buyerRepository.save(buyer);
    }

    @Transactional
    public void delete(int id){
        buyerRepository.deleteById(id);
    }
}
