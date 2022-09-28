package ru.umar.onlinestore.restapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.repositories.T_ShirtRepository;
import ru.umar.onlinestore.restapp.util.T_ShirtNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service()
@Transactional(readOnly = true)
public class T_ShirtService {

    private final T_ShirtRepository TShirtRepository;

    @Autowired
    public T_ShirtService(T_ShirtRepository TShirtRepository) {
        this.TShirtRepository = TShirtRepository;
    }

    public List<T_Shirt> findAll() {
        return TShirtRepository.findAll();
    }

    public T_Shirt findOne(int id) {
        Optional<T_Shirt> foundTShirt = TShirtRepository.findById(id);
            return foundTShirt.orElseThrow(T_ShirtNotFoundException::new);
    }

    @Transactional
    public void save(T_Shirt TShirt){
        enrichTShirt(TShirt);
        TShirtRepository.save(TShirt);
    }

    private void enrichTShirt(T_Shirt t_shirt) {
        t_shirt.setCreatedAt(LocalDateTime.now());
        t_shirt.setUpdatedAt(LocalDateTime.now());
        t_shirt.setCreatedWho("ADMIN");
    }
}
