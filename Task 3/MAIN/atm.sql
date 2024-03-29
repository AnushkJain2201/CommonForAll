CREATE DATABASE atmdb;

USE atmdb;

CREATE TABLE ATM
(
    ATM_id int not null auto_increment primary key,
    name char(50) not null unique,
    address char(50) not null,
    pin char(255) not null
);

create TABLE bank_account
(
    bank_account_id int not null auto_increment primary key,
    ATM_id int not null,
    balance int not null default 0,
    constraint fk_bank_account_atm foreign key (ATM_id) references atm (ATM_id)
);

ALTER TABLE atm
DROP COLUMN address;

ALTER TABLE atm
DROP COLUMN name;

ALTER TABLE atm
ADD address varchar(400);

ALTER TABLE atm
ADD name char(50) unique;
