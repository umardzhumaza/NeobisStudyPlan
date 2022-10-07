package ru.umar.onlinestore.restapp.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;
    @ManyToOne
    @JoinColumn(name = "t_shirt_id", referencedColumnName = "id")
    private T_Shirt t_shirt;
    @Column(name = "order_date")
    private String order_date;

    public Order(){}

    public Order(int id, Buyer buyer, T_Shirt t_shirt, String order_date) {
        this.id = id;
        this.buyer = buyer;
        this.t_shirt = t_shirt;
        this.order_date = order_date;
    }

    public Order(Buyer buyer, T_Shirt t_shirt, String order_date) {
        this.buyer = buyer;
        this.t_shirt = t_shirt;
        this.order_date = order_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public T_Shirt getT_shirt() {
        return t_shirt;
    }

    public void setT_shirt(T_Shirt t_shirt) {
        this.t_shirt = t_shirt;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}
