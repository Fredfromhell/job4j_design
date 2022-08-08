create table flat(
    id serial primary key,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    flat_id int references flat(id) unique
);

insert into flat(number) values (1);
insert into flat(number) values (2);
insert into flat(number) values (3);

insert into people(name, flat_id) values ('Ivan', 1);
insert into people(name, flat_id) values ('Boris', 2);
insert into people(name, flat_id) values ('Petr', 3);
insert into people(name) values ('Vasya');
insert into people(name) values ('Anya');

select * from people
join flat on people.flat_id=flat.id;

select p.name, f.number
from people as p join flat as f on p.flat_id=f.id;

select p.name as Жилец, f.number as Квартира
from people as p join flat as f on p.flat_id=f.id;

select p.name as Жилец, f.number as Квартира
from people p join flat f on p.flat_id=f.id;

