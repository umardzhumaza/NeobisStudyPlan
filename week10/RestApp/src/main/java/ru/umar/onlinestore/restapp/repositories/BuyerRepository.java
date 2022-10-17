package ru.umar.onlinestore.restapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umar.onlinestore.restapp.models.Buyer;


@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}
