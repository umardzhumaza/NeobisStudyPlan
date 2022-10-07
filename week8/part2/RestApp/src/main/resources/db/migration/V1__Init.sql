drop table if exists orders;
drop table if exists buyer;
drop table if exists t_shirt;
create table buyer (id integer not null auto_increment,name varchar(30), sur_name varchar(30), email varchar(255), phone_number varchar(255), primary key (id)) engine=InnoDB;
create table orders (id integer not null auto_increment, buyer_id integer, t_shirt_id integer, primary key (id), order_date varchar(255))  engine=InnoDB;
create table t_shirt (id integer not null auto_increment, name varchar(30), color varchar(30), price integer, created_at datetime, updated_at datetime, created_who varchar(255), primary key (id)) engine=InnoDB;
alter table orders add constraint FK8bfdq6yuliu59tbo7go78xt51 foreign key (buyer_id) references buyer (id);
alter table orders add constraint FKpux7o1v0h6yw9m520venbij7n foreign key (t_shirt_id) references t_shirt (id);
INSERT INTO Buyer(email, name, phone_number, sur_name) VALUE ('alex@gmail.com', 'Alex', '+1 124 556 987', 'Prat');