--liquibase formatted sql

--changeset novikovns:2023-04-22-001-authors-table
create table authors
(
    id   bigint primary key auto_increment,
    name varchar(255)
);

--changeset novikovns:2023-04-22-002-styles-table
create table styles
(
    id   bigint primary key auto_increment,
    name varchar(255)
);

--changeset novikovns:2023-04-22-003-books-table
create table books
(
    id        bigint primary key auto_increment,
    name      varchar(255),
    author_id bigint references authors (id),
    style_id  bigint references styles (id)
);

--changeset novikovns:2023-04-22-004-comments-table
create table comments
(
    id      bigint primary key auto_increment,
    comment varchar(255),
    book_id bigint references books (id) on delete cascade
);