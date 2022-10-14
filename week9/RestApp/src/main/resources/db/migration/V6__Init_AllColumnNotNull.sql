alter table t_shirt
    modify name varchar(30) not null;

alter table t_shirt
    modify color varchar(30) not null;

alter table t_shirt
    modify price int not null;

alter table orders
    modify buyer_id int not null;

alter table orders
    modify t_shirt_id int not null;

alter table buyer
    modify name varchar(30) not null;

alter table buyer
    modify sur_name varchar(30) not null;

alter table buyer
    add unique(email);

alter table buyer
    modify phone_number varchar(255) not null;