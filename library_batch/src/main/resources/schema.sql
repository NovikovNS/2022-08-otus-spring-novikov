drop table if exists authors;
drop table if exists books;
drop table if exists styles;
drop table if exists comments;

drop table if exists Batch_JOB_EXECUTION_CONTEXT;
drop table if exists Batch_JOB_EXECUTION_PARAMS;
drop table if exists Batch_JOB_EXECUTION_SEQ;
drop table if exists Batch_JOB_SEQ;
drop table if exists Batch_STEP_EXECUTION_CONTEXT;
drop table if exists Batch_STEP_EXECUTION_SEQ;
drop table if exists Batch_STEP_EXECUTION;
drop table if exists Batch_JOB_EXECUTION;
drop table if exists Batch_JOB_INSTANCE;

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