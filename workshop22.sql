create database rsvp;
use rsvp;

create table rsvp(

    id int auto_increment not null,
    name varchar(64) not null,
    email varchar(128) not null,
    phone varchar(64) not null,
    confirmation_date date not null,
    comments text not null,

    primary key (id)
);