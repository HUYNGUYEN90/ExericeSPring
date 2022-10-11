drop database if exists trainee_db;
create database trainee_db;

use trainee_db;

create table desktop(
    desktop_id varchar(15)  primary key ,
    desktop_make varchar(15)  not null,
    desktop_model varchar(10)  not null,
    desktop_status varchar(15)  not null
);

create table trainee(
    trainee_id int  primary key auto_increment,
    trainee_name varchar(15)  not null,
    desktop_id varchar(15)   unique  references desktop(desktop_id)
);

insert into desktop values('MYSGEC111111D','Acer','Aspire','ALLOCATED');
insert into desktop values('MYSGEC222222D','Dell','Vostro','AVAILABLE');

insert into trainee values(800001,'Scott','MYSGEC111111D');
insert into trainee values(800002,'Jack',null);

commit;

select * from desktop;
select * from trainee;