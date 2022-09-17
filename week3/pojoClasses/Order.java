public class Order {
    private int id;
    private int totalAmount;
    private String orderDate;
    private String deliveryDate;
    private String paymentMethod;
    private String orderStatus;

    public Order(int id, int totalAmount, String orderDate, String deliveryDate, String paymentMethod, String orderStatus) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
