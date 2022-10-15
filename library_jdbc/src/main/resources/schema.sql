drop table if exists authors;
drop table if exists books;
drop table if exists styles;

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