 create table role(
     id serial primary key,
     name varchar(255)
 );
 
 create table access(
     id serial primary key,
     name varchar(255)
 );
 
 create table role_access(
     id serial primary key,
     role_id int references role(id),
     access_id int references access(id)
 );
 
insert into role(name) values ('Administrator');
insert into role(name) values ('Support');
insert into role(name) values ('User');

insert into access(name) values ('Options');
insert into access(name) values ('Directory');
insert into access(name) values ('Documents');

insert into role_access(role_id, access_id) values (1, 1);
insert into role_access(role_id, access_id) values (1, 2);
insert into role_access(role_id, access_id) values (1, 3);
insert into role_access(role_id, access_id) values (2, 1);
insert into role_access(role_id, access_id) values (2, 2);
insert into role_access(role_id, access_id) values (3, 3);

select * from role_access

