drop table if exists meetups cascade;
create table meetups
(
    id             bigserial not null,
    description    varchar(100),
    location       varchar(500),
    organizer      varchar(255),
    scheduled_time timestamp,
    topic          varchar(255),
    primary key (id)
);