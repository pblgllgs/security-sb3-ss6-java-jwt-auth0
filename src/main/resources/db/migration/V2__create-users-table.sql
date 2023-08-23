create table users (
    id uuid not null,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);