CREATE TABLE User(
          id int primary key auto_increment,
          username varchar(100) not null,
          year_if_birth int not null,
          password varchar(100) NOT NULL,
          role varchar(100) NOT NULL
);