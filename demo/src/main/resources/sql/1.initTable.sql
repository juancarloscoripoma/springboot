-- USE relation;
-- constraint_schema is the name of the database
-- select * from information_schema.referential_constraints where constraint_schema = 'relation'
-- ALTER TABLE phone DROP FOREIGN KEY fk_phone_employee_id;
-- DROP database demo^
-- CREATE database demo^

ALTER TABLE phone DROP FOREIGN KEY fk_phone_employee_id^
drop table if exists phone^
drop table if exists employee^

create table employee
(
   id             bigint not null AUTO_INCREMENT,
   firstname 		  varchar(100),
   lastname       varchar(110),
   salary         decimal(10,2),
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8^

create table phone
(
   id             bigint not null AUTO_INCREMENT,
   type			      varchar(200),/*home, work*/
   areacode       int,/*951*/
   number         int,
   employee_id    bigint not null,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8^

alter table phone add constraint fk_phone_employee_id foreign key (employee_id)
      references employee (id) on delete restrict on update restrict^

