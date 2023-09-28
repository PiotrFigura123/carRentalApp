--liquibase formatted sql
--changeset pfigura:1

CREATE TABLE cars
(
    car_id        serial      not null,
    model         varchar(20) NOT NULL,
    engine        varchar(5)  NOT NULL,
    petrol        varchar     NOT NULL,
    available     boolean     NOT NULL,
    creation_date timestamp   null,
    update_date   timestamp   null,
    city_id   integer   null,
    mark_id   integer   null,
    plate_id   integer   null

);

CREATE TABLE plate_numbers
(
    plate_id        serial      not null,
    registration    varchar(10) NOT NULL
);

CREATE TABLE car_marks
(
    mark_id        serial      not null,
    mark           varchar(10) NOT NULL
);

CREATE TABLE cities
(
    city_id        serial      not null,
    city_name      varchar(10) NOT NULL
);

CREATE TABLE users
(
    user_id        serial      not null,
    login           varchar(20) NOT NULL unique,
    password varchar(20) not null,
    email varchar(20) not null,
    registration_date timestamp not null,
    role varchar(10) not null
);

CREATE TABLE carRental(
    rental_id serial not null,
    car_id  integer not null,
    user_id integer not null,
    start_date timestamp not null,
    end_date timestamp not null,
    reservation_date timestamp not null
);

--PK
alter table only cars
    add constraint car_pkey primary key (car_id);
alter table only plate_numbers
    add constraint plate_number_pkey primary key (plate_id);
alter table only car_marks
    add constraint car_marks_pkey primary key (mark_id);
alter table only cities
    add constraint cities_pkey primary key (city_id);