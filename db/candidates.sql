create table candidates
(
    id         serial primary key,
    name       varchar(60) not null,
    experience text,
    salary     INTEGER     not null
);