package ru.umar.onlinestore.restapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.repositories.T_ShirtRepository;
import ru.umar.onlinestore.restapp.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service()
@Transactional(readOnly = true)
public class T_ShirtService {

    private final T_ShirtRepository t_shirtRepository;

    @Autowired
    public T_ShirtService(T_ShirtRepository t_shirtRepository) {
        this.t_shirtRepository = t_shirtRepository;
    }

    public List<T_Shirt> findAll() {
        return t_shirtRepository.findAll();
    }

    public T_Shirt findOne(int id) {
        Optional<T_Shirt> foundTShirt = t_shirtRepository.findById(id);
            return foundTShirt.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public T_Shirt save(T_Shirt TShirt){
        enrichTShirt(TShirt);
        return t_shirtRepository.save(TShirt);
    }

    @Transactional
    public T_Shirt update(T_Shirt t_shirt){
        Optional<T_Shirt> foundT_Shirt = t_shirtRepository.findById(t_shirt.getId());
        if(foundT_Shirt.isPresent())
            t_shirtRepository.save(t_shirt);

        return foundT_Shirt.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public T_Shirt delete(int id){
        Optional<T_Shirt> foundT_Shirt = t_shirtRepository.findById(id);
        if(foundT_Shirt.isPresent())
            t_shirtRepository.deleteById(id);

        return foundT_Shirt.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteAll(){
        t_shirtRepository.deleteAll();
    }

    private void enrichTShirt(T_Shirt t_shirt) {
        t_shirt.setCreatedAt(LocalDateTime.now());
        t_shirt.setUpdatedAt(LocalDateTime.now());
        t_shirt.setCreatedWho("ADMIN");
    }
}
