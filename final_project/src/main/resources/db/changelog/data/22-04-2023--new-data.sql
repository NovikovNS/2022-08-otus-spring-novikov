--liquibase formatted sql

--changeset novikovns:2023-04-22-001-authors-data
insert into authors (name)
values ('Пушкин'),
       ('Конан Дойль'),
       ('Кинг');

--changeset novikovns:2023-04-22-002-styles-data
insert into styles (name)
values ('Роман'),
       ('Детектив'),
       ('Ужасы');

--changeset novikovns:2023-04-22-003-books-data
insert into books (name, author_id, style_id)
values ('Евгений Онегин', 1, 1),
       ('Шерлок Холмс', 2, 2),
       ('Сияние', 3, 3);

--changeset novikovns:2023-04-22-004-comments-data
insert into comments(comment, book_id)
values ('Неплохая книжка', 1),
       ('Не по мне', 2),
       ('Классная книга', 3);