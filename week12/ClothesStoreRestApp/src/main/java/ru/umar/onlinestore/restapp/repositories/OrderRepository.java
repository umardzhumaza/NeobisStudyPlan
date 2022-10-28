package RestApp.src.main.java.ru.umar.onlinestore.restapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umar.onlinestore.restapp.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
