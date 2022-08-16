-- 1. Создать таблицы и заполнить их начальными данными

create table departments (
id serial primary key,
    name varchar(255)
);

create table employees (
id serial primary key,
    name varchar(255),
    dep_id int references departments(id)
);

insert into departments (name) values ('it'),('buh'),('adm'), ('comers'),('war');
insert into employees (name, dep_id) values ('John',1),('Fred',2),('Ivan',3),('Roman',4);

-- 2. Выполнить запросы с left, rigth, full, cross соединениями

select * from departments d left join employees e on e.dep_id = d.id;
select * from departments d right join employees e on e.dep_id = d.id;
select * from departments d full join employees e on e.dep_id = d.id;
select * from departments d cross join employees e;

-- 3. Используя left join найти департаменты, у которых нет работников

select * from departments d left join employees e on e.dep_id = d.id where e.id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).

select * from departments d left join employees e on e.dep_id = d.id;

select d.id, d.name, e.id, e.name, e.dep_id 
from employees e right
join departments d on d.id = e.dep_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары

create table teens (
id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Fred', 'man'), ('John','man'), ('Jeny','woman'), ('Stef','woman');

select t1.name,t1.gender, t2.name, t2.gender, (t1.gender ||'+'|| t2.gender) as "m+w" 
from teens t1 cross join teens t2 
where t1.gender != t2.gender;



