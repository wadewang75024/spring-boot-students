create table students(
	id int unique not null default 0,
	firstName varchar(30) not null,
	lastName varchar(50) not null,
	emailAddress varchar(30) not null
);
alter table students add primary key(id);
alter table students modify column id INT auto_increment;