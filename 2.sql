select * from people
join flat on people.flat_id=flat.id;

select p.name, f.number
from people as p join flat as f on p.flat_id=f.id;

select p.name as Жилец, f.number as Квартира
from people as p join flat as f on p.flat_id=f.id;

select p.name as Жилец, f.number as Квартира
from people p join flat f on p.flat_id=f.id;

