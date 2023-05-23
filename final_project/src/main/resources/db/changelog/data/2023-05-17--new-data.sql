--liquibase formatted sql

--changeset novikovns:2023-05-17-001-availabilities-data
insert into availabilities (definition)
values ('Лично'),
       ('Друзьям'),
       ('Всем');

--changeset novikovns:2023-05-17-002-values-data
insert into needs (definition)
values ('Ужас как хочется'),
       ('Терпимо'),
       ('Не горит');

--changeset novikovns:2023-05-17-003-wishes-data
insert into wishes (definition, link, price, reason, note, reservation, need_id, availability_id, user_id)
values ('Баскетбольный мяч', 'ссылка на мяч', '1000', 'Любой повод', 'заметка', false, 1, 1, 1),
       ('Прыжок с парашютом', 'ссылка на прыжок','1000', 'Любой повод', 'заметка', false, 2, 2, 1),
       ('Курсы гитары', 'ссылка на курсы', '1000', 'Любой повод', 'заметка', false, 3, 3, 1);

--changeset novikovns:2023-05-17-004-comments-data
insert into comments(comment, wish_id)
values ('Хорошая идея', 1),
       ('Не по мне', 1),
       ('На любителя', 3);

--changeset novikovns:2023-05-17-005-users-data
insert into users(username, password, nickname)
values ('admin', '$2a$10$309SCPiDwZqbyl1MSJH6iu1xkIx/RDIigkyO6TKihdWvxFnhSehMm', 'admin');