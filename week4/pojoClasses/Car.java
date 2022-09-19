package pojoClasses;

public class Car {
    private int id;
    private String model;
    private int yearOfIssue;
    private String color;
    private int horsePower;
    private int price;
    private int maxSpeed;

    public Car(int id, String model, int yearOfIssue, String color, int horsePower, int price, int maxSpeed) {
        this.id = id;
        this.model = model;
        this.yearOfIssue = yearOfIssue;
        this.color = color;
        this.horsePower = horsePower;
        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
