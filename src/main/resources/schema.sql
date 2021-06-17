create schema if not exists carservice;
use carservice;
create table if not exists car
(
    id          bigint  not null auto_increment,
    co2emission decimal(19, 2),
    door_count  integer not null,
    gross_price bigint,
    make        varchar(255),
    model       varchar(255),
    nett_price  bigint,
    version     varchar(255),
    primary key (id)
);
