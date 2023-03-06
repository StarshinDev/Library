--liquibase formatted sql
--changeSet Vitally:create-request
CREATE TABLE IF NOT EXISTS Subscription
(
    subscription_id int primary key generated always as identity,
    subscription_name varchar(30) not null,
    book_count smallint not null,
    subscription_cost numeric(15, 2) not null,
    available boolean default true not null,
    last_change_time timestamp default current_timestamp not null
);