CREATE DATABASE CarShop;

USE CarShop;

CREATE TABLE Car(
        id int AUTO_INCREMENT PRIMARY KEY,
        model varchar(50) NOT NULL,
        year_of_issue varchar(50) NOT NULL,
        color varchar(50),
        horse_power int,
        price int NOT NULL,
        max_speed int
);

CREATE TABLE Buyer(
        id int AUTO_INCREMENT PRIMARY KEY,
        name varchar(255) NOT NULL,
        sur_name varchar(255) NOT NULL,
        age int,
        gender char(1) NOT NULL,
        address varchar(255),
        email varchar(255),
        phone_number varchar(30) NOT NULL
);

CREATE TABLE Orders(
        id int AUTO_INCREMENT PRIMARY KEY,
        buyer_id int,
        car_id int,
        total_amount int,
        order_date varchar(50) NOT NULL,
        delivery_date varchar(50) NOT NULL,
        payment_method varchar(50) NOT NULL,
        order_status varchar(50) NOT NULL,
        FOREIGN KEY (BuyerId) REFERENCES Buyer(id),
        FOREIGN KEY (CarId) REFERENCES Car(id)
);
