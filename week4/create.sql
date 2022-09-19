CREATE DATABASE CarShop;

USE CarShop;

CREATE TABLE Car(
        id int AUTO_INCREMENT PRIMARY KEY,
        model varchar(50) NOT NULL,
        yearOfIssue varchar(50) NOT NULL,
        color varchar(50),
        horsePower int,
        price int NOT NULL,
        maxSpeed int
);

CREATE TABLE Buyer(
        id int AUTO_INCREMENT PRIMARY KEY,
        name varchar(255) NOT NULL,
        surName varchar(255) NOT NULL,
        age int,
        gender char(1) NOT NULL,
        address varchar(255),
        email varchar(255),
        phoneNumber varchar(30) NOT NULL
);

CREATE TABLE Orders(
        id int AUTO_INCREMENT PRIMARY KEY,
        BuyerId int,
        CarId int,
        totalAmount int,
        orderDate varchar(50) NOT NULL,
        deliveryDate varchar(50) NOT NULL,
        paymentMethod varchar(50) NOT NULL,
        orderStatus varchar(50) NOT NULL,
        FOREIGN KEY (BuyerId) REFERENCES Buyer(id),
        FOREIGN KEY (CarId) REFERENCES Car(id)
);
