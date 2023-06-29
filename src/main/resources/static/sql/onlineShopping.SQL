<!-- insert data in to users table for admin
insert into users(id,email,first_name,last_name,password)values(1,'rupesh@gmail.com','rupesh','kumar','$2y$10$szyuFHMIkLPU4iDINcfdZe/oNBb3CSwMbbG7/VOp2Rzarw6MHqE9G');
<!-- password is rupesh their bcrypt value in hashing 10 is '$2y$10$szyuFHMIkLPU4iDINcfdZe/oNBb3CSwMbbG7/VOp2Rzarw6MHqE9G'


<!-- insert role id and name of role in roles table

insert into roles(id,name)values(1,'ROLE_ADMIN'),(2,'ROLE_USER');


<!-- insert user_id and role_id in user_role table
insert into user_role(user_id,role_id)values(1,1),(1,2);


