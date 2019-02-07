
drop table if exists card;
create table card(
	id bigint not null AUTO_INCREMENT,
    number_card varchar(255),
    issue_date TIMESTAMP,
    primary key (id)
);

drop table if exists person;
create table person(
	id bigint not null AUTO_INCREMENT,
    firstName varchar(255),
    lastName varchar(255),
    card_id bigint,
    primary key (id)
);