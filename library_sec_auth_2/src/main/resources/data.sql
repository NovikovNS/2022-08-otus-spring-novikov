insert into styles (name) values ('Роман');
insert into styles (name) values ('Детектив');
insert into styles (name) values ('Ужасы');

insert into authors (name) values ('Пушкин');
insert into authors (name) values ('Конан Дойль');
insert into authors (name) values ('Кинг');

insert into books (name, author_id, style_id) values ('Евгений Онегин', 1, 1);
insert into books (name, author_id, style_id) values ('Шерлок Холмс', 2, 2);
insert into books (name, author_id, style_id) values ('Сияние', 3, 3);

insert into comments(comment, book_id) values ('Неплохая книжка', 1);
insert into comments(comment, book_id) values ('Не по мне', 2);
insert into comments(comment, book_id) values ('Классная книга', 3);

insert into users(name, password) values ('admin', '$2a$10$309SCPiDwZqbyl1MSJH6iu1xkIx/RDIigkyO6TKihdWvxFnhSehMm');
insert into users(name, password) values ('user', '$2a$10$5QAYEomPKsIV4Wei4Qq3BuUn0hNcQr5mwYnQYtdxa/jgd0tqUcDS2');
insert into users(name, password) values ('blocked_user', '$2a$10$roODCgantQmYqnnrq0nc5./K1aNkQgvRk/I23yfMTTfpG3yMHi1mG');

insert into user_roles(user_id, role) values (1, 'ADMIN');
insert into user_roles(user_id, role) values (2, 'USER');
insert into user_roles(user_id, role) values (3, 'BLOCKED_USER')
