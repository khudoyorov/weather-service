delete from weather_test.public.users where 1 = 1;
delete from weather_test.public.authorities where 1 = 1;
delete from weather_test.public.roles where 1 = 1;
delete from weather_test.public.city where 1 = 1;

select setval('users_id_seq', 1, false);
select setval('authorities_id_seq', 1, false);
select setval('roles_id_seq', 1, false);

insert into weather_test.public.authorities(id, name)
values (1, 'USER'),(2, 'ADMIN');

insert into weather_test.public.roles(user_id, authority_id)
values (2, 2);