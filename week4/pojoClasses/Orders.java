package pojoClasses;

public class Orders {
    private int id;
    private Buyer buyer;
    private Car car;
    private int total_amount;
    private String order_date;
    private String delivery_date;
    private String payment_method;
    private String order_status;

    public Orders(int id, Buyer buyer, Car car, int total_amount, String order_date, String delivery_date, String payment_method, String order_status) {
        this.id = id;
        this.buyer = buyer;
        this.car = car;
        this.total_amount = total_amount;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.payment_method = payment_method;
        this.order_status = order_status;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
