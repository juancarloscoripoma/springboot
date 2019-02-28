-- USE demo;
-- constraint_schema is the name of the database
-- select * from information_schema.referential_constraints where constraint_schema = 'relation'
-- ALTER TABLE phone DROP FOREIGN KEY fk_phone_employee_id;
-- DROP database demo^
-- CREATE database demo^

-- -------------- one to many -----------------------------------------------------------
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
-- -------------- end one to many --------------

-- -------------- one to one ------------------------------------------------------------
-- USE demo;
-- constraint_schema is the name of the database
-- select * from information_schema.referential_constraints where constraint_schema = 'relation'
-- ALTER TABLE users DROP FOREIGN KEY fk_users_address_id;

ALTER TABLE users DROP FOREIGN KEY fk_users_address_id^
drop table if exists address^
drop table if exists users^

create table address
(
   id               bigint not null AUTO_INCREMENT,
   addressline1 		varchar(100),
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8^

create table users
(
   id             bigint not null AUTO_INCREMENT,
   firstname			varchar(200),
   address_id     bigint not null,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8^

alter table users add constraint fk_users_address_id foreign key (address_id)
      references address (id) on delete restrict on update restrict^
-- -------------- end one to one ----------------

-- -------------- many to many ----------------------------------------------------------
ALTER TABLE course_student DROP FOREIGN KEY fk_course_student_course^
ALTER TABLE course_student DROP FOREIGN KEY fk_course_student_student^

drop table if exists student^
drop table if exists course^
drop table if exists course_student^

create table student
(
   id                  bigint not null AUTO_INCREMENT,
   firstname			     varchar(200),
   age                 INT,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8^

create table course
(
   id                   bigint not null AUTO_INCREMENT,
   code                 varchar(210),
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8^


create table course_student
(
   student_id                   bigint not null,
   course_id                    bigint not null,
   primary key (student_id, course_id)
)^

alter table course_student add constraint fk_course_student_course foreign key (course_id)
      references course (id) on delete restrict on update restrict^

alter table course_student add constraint fk_course_student_student foreign key (student_id)
      references student (id) on delete restrict on update restrict^

-- -------------- end many to many --------------