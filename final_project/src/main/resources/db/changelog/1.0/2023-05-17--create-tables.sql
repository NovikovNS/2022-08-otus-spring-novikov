--liquibase formatted sql

--changeset novikovns:2023-05-17-001-availabilities-table
create table availabilities
(
  id         bigint primary key auto_increment,
  definition varchar(255)
);

--changeset novikovns:2023-05-17-002-values-table
create table needs
(
  id         bigint primary key auto_increment,
  definition varchar(255)
);

--changeset novikovns:2023-05-17-003-wishes-table
create table wishes
(
  id              bigint primary key auto_increment,
  definition      varchar(255),
  link            varchar(255),
  price           varchar(255),
  reason          varchar(255),
  note            varchar(255),
  reservation     boolean,
  user_id         bigint,
  need_id         bigint references needs (id),
  availability_id bigint references availabilities (id)
);

--changeset novikovns:2023-05-17-004-comments-table
create table comments
(
  id      bigint primary key auto_increment,
  comment varchar(255),
  wish_id bigint references wishes (id) on delete cascade
);

--changeset novikovns:2023-05-17-005-users-table
create table users
(
    id       bigint primary key auto_increment,
    username     varchar(255),
    lastName     varchar(255),
    email     varchar(255),
    password varchar(255),
    nickname varchar(255)
);