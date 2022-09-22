import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class JdbcConnector{

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()){

                System.out.println("Connection to Store DB succesfull!");
                Update(1,330000);
                Select();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException, IOException{

        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("C:\\Users\\24425\\Desktop\\JavaLearn\\CarShop\\src\\main\\java\\database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

    public static void Add() {
        try{
            String url = "jdbc:mysql://localhost/CarShop?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "916052";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("INSERT Car(id, model, year_of_issue, color, horse_power, price, max_speed) VALUES (1, 'Lamborghini Aventador', 2021, 'Red', 780, 333000 , 355)," +
                        "(2, 'Lamborghini Huracan', 2019, 'Black', 640, 275000 , 325), (3, 'TESLA MODEL X', 2021, 'White', 1020, 159000 , 262)");
                System.out.printf("Added %d rows\n", rows);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static void Delete(int id) {
        try{
            String url = "jdbc:mysql://localhost/CarShop?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "916052";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate("DELETE FROM Car WHERE id="+id+"");
                System.out.printf("Deleted row with id %d \n", id);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static void Select() {
        try{
            String url = "jdbc:mysql://localhost/CarShop?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "916052";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM Car");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String model = resultSet.getString(2);
                    int year_of_issue = resultSet.getInt(3);
                    String color = resultSet.getString(4);
                    int horse_power = resultSet.getInt(5);
                    int price = resultSet.getInt(6);
                    int max_speed = resultSet.getInt(7);
                    System.out.printf("%d. %s - Year of Issue: %d year, Color: %s, Horse power: %d h.p., Price: %d$, Max speed: %dkm/h \n", id, model, year_of_issue, color, horse_power, price, max_speed);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static void Update(int id, int price) {
        try{
            String url = "jdbc:mysql://localhost/CarShop?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "916052";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate("UPDATE Car SET price = "+price+" WHERE id="+id+"");
                System.out.printf("The price of a car with an ID: %d has been changed to %d \n", id, price);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}
