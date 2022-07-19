create table brand(
    id serial primary key,
    name varchar(255)
);

create table model(
    id serial primary key,
    name varchar(255),
    brand_id int references brand(id)
);

insert into brand(name) values ('DELL');
insert into model(name, brand_id) VALUES ('Alienware', 1);

