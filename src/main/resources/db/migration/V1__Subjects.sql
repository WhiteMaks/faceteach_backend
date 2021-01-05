create table subjects(
    id bigserial not null primary key,
    name varchar(50)
);

insert into subjects (name) values ('Русский язык');
insert into subjects (name) values ('Математика');
insert into subjects (name) values ('Информатика');
insert into subjects (name) values ('Алгебра');
insert into subjects (name) values ('Геометрия');
insert into subjects (name) values ('История');
insert into subjects (name) values ('Общевствознание');
insert into subjects (name) values ('Литература');
insert into subjects (name) values ('Биология');