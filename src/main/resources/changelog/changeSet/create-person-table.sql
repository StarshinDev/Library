--liquibase formatted sql
--changeSet Vitally:create-request
CREATE TABLE IF NOT EXISTS Person
(
    user_id int primary key generated always as identity,
    user_password varchar not null,
    user_name varchar not null,
    user_fio varchar(150) not null,
    user_email varchar(100) not null,
    user_role varchar(100) not null
);