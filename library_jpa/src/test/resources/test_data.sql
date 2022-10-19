insert into styles (name) values ('Роман');
insert into styles (name) values ('Детектив');

insert into authors (name) values ('Пушкин');
insert into authors (name) values ('Конан Дойль');

insert into books (name, author_id, style_id) values ('Евгений Онегин', 1, 1);
insert into books (name, author_id, style_id) values ('Шерлок Холмс', 2, 2);

insert into comments(comment, book_id) values ('Неплохая книжка', 1);
insert into comments(comment, book_id) values ('Супер, я в восторге', 2);
