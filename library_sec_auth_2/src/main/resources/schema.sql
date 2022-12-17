drop table if exists authors;
drop table if exists books;
drop table if exists styles;
drop table if exists comments;
drop table if exists users;
drop table if exists user_roles;

create table authors
(
    id   bigint primary key auto_increment,
    name varchar(255)
);

create table styles
(
    id   bigint primary key auto_increment,
    name varchar(255)
);

create table books
(
    id        bigint primary key auto_increment,
    name      varchar(255),
    author_id bigint references authors (id),
    style_id  bigint references styles (id)
);

create table comments
(
    id      bigint primary key auto_increment,
    comment varchar(255),
    book_id bigint references books (id) on delete cascade
);

create table users
(
    id       bigint primary key auto_increment,
    name     varchar(255),
    password varchar(255)
);

create table user_roles
(
    id      bigint primary key auto_increment,
    user_id bigint references users (id),
    role    varchar(255)
);