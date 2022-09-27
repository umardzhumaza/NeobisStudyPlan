package ru.umar.onlinestore.restapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.umar.onlinestore.restapp.models.T_Shirt;


@Repository
public interface T_ShirtRepository extends JpaRepository<T_Shirt, Integer> {

}
