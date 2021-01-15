create table company (
    id bigint not null auto_increment,
    code varchar(10) not null,
    name varchar(255) not null,
    address varchar(255) not null,
    representative varchar(255) not null,
    primary key (id)
);