package RestApp.src.main.java.ru.umar.onlinestore.restapp.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;
    @ManyToOne
    @JoinColumn(name = "t_shirt_id", referencedColumnName = "id")
    private T_Shirt t_shirt;
    @Column(name = "order_date")
    private String order_date;

    public Order(Buyer buyer, T_Shirt t_shirt, String order_date) {
        this.buyer = buyer;
        this.t_shirt = t_shirt;
        this.order_date = order_date;
    }
}
