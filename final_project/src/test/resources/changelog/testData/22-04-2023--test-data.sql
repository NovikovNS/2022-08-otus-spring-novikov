--liquibase formatted sql

--changeset novikovns:2023-04-22-001-authors-test-data
insert into authors (name)
values ('Пушкин'),
       ('Конан Дойль');

--changeset novikovns:2023-04-22-002-styles-test-data
insert into styles (name)
values ('Роман'),
       ('Детектив');

--changeset novikovns:2023-04-22-003-books-test-data
insert into books (name, author_id, style_id)
values ('Евгений Онегин', 1, 1),
       ('Шерлок Холмс', 2, 2);

--changeset novikovns:2023-04-22-004-comments-test-data
insert into comments(comment, book_id)
values ('Неплохая книжка', 1),
       ('Супер, я в восторге', 2);