create table pc (
id serial primary key,
processor varchar(30),
ram int,
gpu text);

insert into pc(processor, ram, gpu) values ('Intel core i7', '16', 'rtx 3090ti');

update pc set ram = '32';

delete from pc;