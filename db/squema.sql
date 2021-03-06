create table users
(
    id       serial primary key,
    name     varchar(60) not null,
    email    VARCHAR(60) not null unique,
    password VARCHAR(30) not null
);

create table cars
(
    id            serial primary key,
    model         varchar(40) not null,
    type_car_body varchar(15) not null,
    color         varchar(15) not null,
);

create table advertisement
(
    id          serial primary key,
    id_user     INTEGER REFERENCES users (id),
    id_car      INTEGER REFERENCES cars (id),
    photo       text,
    published   timestamp,
    description text,
    status      boolean
);
