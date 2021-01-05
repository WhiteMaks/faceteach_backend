create table users(
    id bigserial primary key,
    username varchar(20),
    password varchar(60),
    role varchar(20)
);