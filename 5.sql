create table type( 
id serial primary key,
name text
);

create table product(
id serial primary key,
type_id int references type(id),
expired_date date,
price int,
name text
);


insert into type (name) values ('Сыр'), ('Молоко');
insert into product (name, type_id, expired_date, price) values ('Сыр моцарелла',1,'2022-9-10',500), ('Сыр гауда',1, '2022-7-10',300),
('Сыр тельзитир',1,'2022-10-10',400),('Мороженое лакомка',2,'2022-11-11', 100), ('Мороженое стаканчик',2, '2022-12-12',50), ('Молоко 10%',2, '2022-8-14', 80);

select t.name, p.name from type t
join product p on p.type_id=t.id where t.name = 'Сыр'  ;
 
select * from product where name like 'Мороженое%'; 

select * from product where expired_date < current_date;

select p.name, p.price

from product as p  where price = (select  max(price) from product)

select t.name, count(p.name)
from type as t
join product as p
on p.type_id=t.id
group by t.name;

select t.name, p.name from type t
join product p on p.type_id=t.id where t.name = 'Сыр' or t.name = 'Молоко' ;

select t.name, count(p.name)
from type as t
join product as p
on p.type_id=t.id
group by t.name
having count(p.name) < 10;

select p.name, t.name from product p
join type t on p.type_id=t.id  ; 
