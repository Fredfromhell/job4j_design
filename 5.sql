create table type( 
id serial primary key,
name text
);

create table product(
id serial primary key,
type_id references type(id),
expired_date date,
price int,
name text
);

insert into type (name) values ('Сыр'), ('Молоко');
insert into product (name, type_id, expired_date, price) values ('Сыр моцарелла',5,'2022-9-10',500), ('Сыр гауда',5, '2022-7-10',300),
('Сыр тельзитир',5,'2022-10-10',400),('Мороженое лакомка',6,'2022-11-11', 100), ('Мороженое стаканчик',6, '2022-12-12',50), ('Молоко 10%',6, '2022-8-14', 80);

select * from product where type_id=5; 
select * from product where name like 'Мороженое%'; 
select * from product where expired_date < '2022.8.9';
select  max(price) from product;

select t.name, count(p.name)
from type as t
join product as p
on p.type_id=t.id
group by t.name;

select * from product where type_id=5 or type_id=6;

select t.name, count(p.name)
from type as t
join product as p
on p.type_id=t.id
group by t.name
having count(p.name) < 10;

select p.name, t.name from product p
join type t on p.type_id=t.id  ; 
