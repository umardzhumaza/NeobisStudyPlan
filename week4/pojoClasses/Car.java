package pojoClasses;

public class Car {
    private int id;
    private String model;
    private int year_of_issue;
    private String color;
    private int horse_power;
    private int price;
    private int max_speed;

    public Car(int id, String model, int year_of_issue, String color, int horse_power, int price, int max_speed) {
        this.id = id;
        this.model = model;
        this.year_of_issue = year_of_issue;
        this.color = color;
        this.horse_power = horse_power;
        this.price = price;
        this.max_speed = max_speed;
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

    public int getYear_of_issue() {
        return year_of_issue;
    }

    public void setYear_of_issue(int year_of_issue) {
        this.year_of_issue = year_of_issue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getHorse_power() {
        return horse_power;
    }

    public void setHorse_power(int horse_power) {
        this.horse_power = horse_power;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
    }
}
