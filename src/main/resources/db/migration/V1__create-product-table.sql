create table products (
    id uuid not null,
    name varchar(255),
    price numeric(38,2),
    primary key (id)
);

alter table if exists products
add constraint name_UK unique (name);