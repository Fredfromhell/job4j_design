create table product(
    id serial primary key,
    name varchar(255)
);

create table batch(
    id serial primary key,
    number int
);

create table product_batch(
    id serial primary key,
    product_id int references product(id) unique,
    batch_id int references batch(id) unique
);

select * from product_batch;