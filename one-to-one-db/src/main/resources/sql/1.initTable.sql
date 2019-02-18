-- USE relation;
-- constraint_schema is the name of the database
-- select * from information_schema.referential_constraints where constraint_schema = 'relation'
-- ALTER TABLE users DROP FOREIGN KEY fk_users_address_id;
-- DROP database relation^
-- CREATE database relation^

--ALTER TABLE users DROP FOREIGN KEY fk_users_address_id^
drop table if exists address^
drop table if exists users^

create table address
(
   id               bigint not null AUTO_INCREMENT,
   addressLine1 		varchar(100),
   primary key (id)
)/*ENGINE=InnoDB DEFAULT CHARSET=utf8*/^

create table users
(
   id             bigint not null AUTO_INCREMENT,
   firstName			varchar(200),
   /*address_id     bigint not null,*/
   primary key (id)
)/*ENGINE=InnoDB DEFAULT CHARSET=utf8*/^
/*
alter table users add constraint fk_users_address_id foreign key (address_id)
      references address (id) on delete restrict on update restrict^
*/
