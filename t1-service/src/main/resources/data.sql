insert into CLIENT (id, name, email) values (1, 'John Victor', 'test1@gmail.com');
insert into CLIENT (id, name, email) values (2, 'Eduardo Riboleta', 'test2@gmail.com');
insert into CLIENT (id, name, email) values (3, 'Bruno Hoffmann', 'test3@gmail.com');
insert into CLIENT (id, name, email) values (4, 'Matheus Rosa', 'test4@gmail.com');	
insert into CLIENT (id, name, email) values (5, 'Junior Jocemar', 'test5@gmail.com');
insert into CLIENT (id, name, email) values (6, 'Eduardo Garcia', 'test6@gmail.com');
insert into CLIENT (id, name, email) values (7, 'Julia Riboleta', 'test7@gmail.com');
insert into CLIENT (id, name, email) values (8, 'Marcele Riboleta', 'test8@gmail.com');
insert into CLIENT (id, name, email) values (9, 'Eveli Riboleta', 'test9@gmail.com');
insert into CLIENT (id, name, email) values (10, 'Valcir Riboleta', 'test10@gmail.com');

insert into APP (id, name, monthly_cost) values (1, 'Netflix', 10.0);
insert into APP (id, name, monthly_cost) values (2, 'MiauTV', 20.0);
insert into APP (id, name, monthly_cost) values (3, 'PrimeVideo', 30.0);
insert into APP (id, name, monthly_cost) values (4, 'MAX', 40.0);
insert into APP (id, name, monthly_cost) values (5, 'X (Antigo Twitter)', 50.0);

insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (1, 1, '2021-01-01', '2024-07-23', 'true');
insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (1, 2, '2021-02-01', '2024-06-28', 'true');
insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (1, 3, '2021-03-01', '2024-06-09', 'false');
insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (1, 4, '2021-04-01', '2024-08-02', 'true');
insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (1, 5, '2021-05-01', '2024-10-04', 'true');
insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (2, 1, '2021-05-01', '2021-10-04', 'false');
insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (2, 2, '2021-06-01', '2021-11-04', 'false');
insert into SUBSCRIPTION (client_id, app_id, start_date, end_date, status) values (2, 3, '2021-07-01', '2021-12-04', 'false');