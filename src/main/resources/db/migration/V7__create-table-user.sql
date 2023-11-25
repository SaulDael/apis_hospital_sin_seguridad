create table users(

     id bigint not null auto_increment,
     username varchar(250) not null unique,
     password varchar(250) not null ,
     firstname varchar(250) not null ,
     lastsname varchar(250) not null ,

     primary key(id)


);