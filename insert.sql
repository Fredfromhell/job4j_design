insert into role(name) values ('Admin');
insert into role(name) values ('User');

select * from role;

insert into users(name, role_id) values ('Alex',1);
insert into users(name, role_id) values ('Jack',2);

select * from users;

insert into rules(name) values ('Administrator');
insert into rules(name) values ('Documetns');
insert into rules(name) values ('Info directory');

select * from rules;

insert into role_rules(role_id, rules_id) values (1,1);
insert into role_rules(role_id, rules_id) values (2,2);
insert into role_rules(role_id, rules_id) values (2,3);

select * from role_rules;

insert into state(name) values ('New');
insert into state(name) values ('in progress');
insert into state(name) values ('done');

select * from state;

insert into category(name) values ('rnd');
insert into category(name) values ('support');
insert into category(name) values ('consultation');

select * from category;

insert into item(name, users_id, state_id, category_id) values ('New task',1,1,1);

select * from item;

insert into comments(name, item_id) values ('Comment',1);

select * from comments;

insert into attachs(name, item_id) values ('File',1);

select * from attachs;












