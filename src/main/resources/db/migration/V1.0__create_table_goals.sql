create table goals (
id varchar(36) primary key,
description varchar(255) not null,
type varchar(40) not null,
is_completed boolean not null
);