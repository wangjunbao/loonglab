create table u_user(
id bigint NOT NULL AUTO_INCREMENT,
name varchar(255) not null,
passwd varchar(255) not null,
curLocation varchar(255),
primary key (id)
);


create table l_location_catalog(
code char(16),
name varchar(255),
primary key (code)
);

insert into l_location_catalog values('860010','≤‚ ‘Œª÷√');

