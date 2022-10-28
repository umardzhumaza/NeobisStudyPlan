package RestApp.src.main.java.ru.umar.onlinestore.restapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.umar.onlinestore.restapp.models.Buyer;
import RestApp.src.main.java.ru.umar.onlinestore.restapp.repositories.BuyerRepository;
import ru.umar.onlinestore.restapp.util.NotCreatedException;
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
        Optional<Buyer> foundBuyer = buyerRepository.findById(id);
        return foundBuyer.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Buyer save(Buyer buyer){
        return buyerRepository.save(buyer);
    }

    @Transactional
    public Buyer update(Buyer buyer){
        Optional<Buyer> foundBuyer = buyerRepository.findById(buyer.getId());
        if(foundBuyer.isPresent())
            buyerRepository.save(buyer);

        return foundBuyer.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Buyer delete(int id){
        Optional<Buyer> foundBuyer = buyerRepository.findById(id);
        if(foundBuyer.isPresent())
            buyerRepository.deleteById(id);

        return foundBuyer.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteAll(){
        buyerRepository.deleteAll();
    }
}
