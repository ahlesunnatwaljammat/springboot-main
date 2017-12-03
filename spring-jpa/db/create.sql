create table date_time_demo (id bigint not null auto_increment, date tinyblob, date_time tinyblob, duration tinyblob, primary key (id))
create table user (id bigint not null auto_increment, password varchar(255), username varchar(255), primary key (id))
alter table user add constraint UKsb8bbouer5wak8vyiiy4pf2bx unique (username)
