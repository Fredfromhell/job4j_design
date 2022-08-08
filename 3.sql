insert into devices (name, price) values ('mobile', 50000),('tablet', 100000),('pc', 1200000);
insert into people (name) values ('Fred'), ('Alex'),('Jack');
insert into devices_people (device_id, people_id) values (1,1), (1,2),(1,3), (2,1), (2,2), (3,1); 

select avg(price) as Средняя_цена from devices;

select p.name, avg (d.price)
from devices_people as dp
join people as p on dp.people_id=p.id
join devices as d on dp.device_id=d.id
group by p.name;

select p.name, avg (d.price)
from devices_people as dp
join people as p on dp.people_id=p.id
join devices as d on dp.device_id=d.id
group by p.name
having avg(d.price) > 5000;




