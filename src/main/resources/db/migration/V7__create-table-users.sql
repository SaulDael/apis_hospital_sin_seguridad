create table users(

     id bigint not null auto_increment,
     email varchar(80) not null,
     password varchar(255) not null,
     username varchar(30) not null,

     primary key(id)

);