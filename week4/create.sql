CREATE DATABASE CarShop;

USE CarShop;

CREATE TABLE Car(
        id int AUTO_INCREMENT PRIMARY KEY,
        model varchar(50) NOT NULL,
        year_of_issue varchar(50) NOT NULL,
        color varchar(50) NOT NULL,
        horse_power int NOT NULL,
        price int NOT NULL,
        max_speed int NOT NULL
);

CREATE TABLE Buyer(
        id int AUTO_INCREMENT PRIMARY KEY,
        name varchar(255) NOT NULL,
        sur_name varchar(255) NOT NULL,
        age int NOT NULL,
        gender char(1) NOT NULL,
        address varchar(255) NOT NULL,
        email varchar(255) NOT NULL,
        phone_number varchar(30) NOT NULL
);

CREATE TABLE Orders(
        id int AUTO_INCREMENT PRIMARY KEY,
        buyer_id int NOT NULL,
        car_id int NOT NULL,
        total_amount int NOT NULL,
        order_date varchar(50) NOT NULL,
        delivery_date varchar(50) NOT NULL,
        payment_method varchar(50) NOT NULL,
        order_status varchar(50) NOT NULL,
        FOREIGN KEY (buyer_id) REFERENCES Buyer(id),
        FOREIGN KEY (car_id) REFERENCES Car(id)
);
