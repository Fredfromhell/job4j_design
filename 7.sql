-- 1. Написать SQL скрипты по созданию таблиц и их заполнением:

create table car_bodies (
id serial primary key,
    name varchar(255)
);

create table car_engines (
id serial primary key,
    name varchar(255)
);

create table car_transmissions (
id serial primary key,
    name varchar(255)
);

create table cars (
id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values ('sedan'), ('pickup'), ('coupe'), ('hatchback');
insert into car_engines (name) values ('1.6'), ('2d'), ('4.5t'), ('1.2'), ('8t');
insert into car_transmissions (name) values ('mt'), ('at'), ('dsg');
insert into cars (name,body_id,engine_id,transmission_id) values ('skoda rapid',1,1,1),('toyota tundra',2,2,1),('porshe 911',3,3,2),('audi a3',4,1,3),('car', null,null,null),('tractor', null,null,null);

-- 2. Создать SQL запросы:

select c.id, c.name, cb.name, ce.name, ct.name from cars c 
left join car_bodies cb on c.body_id = cb.id 
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

select cb.name from car_bodies cb 
left join cars c on c.body_id = cb.id where c.id is null; 

select ce.name from car_engines ce 
left join cars c on c.body_id = ce.id where c.id is null; 

select ct.name from car_transmissions ct 
left join cars c on c.body_id = ct.id where c.id is null; 








