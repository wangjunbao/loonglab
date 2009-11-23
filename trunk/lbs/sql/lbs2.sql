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


create table i_comment(
id bigint NOT NULL AUTO_INCREMENT,
title varchar(255),
comment varchar(2000),
postId bigint,
publisher bigint,
pubdate datetime,
primary key (id)
);

create table i_message(
id bigint NOT NULL AUTO_INCREMENT,
title varchar(255),
content varchar(2000),
user bigint,
location varchar(255),
picFile varchar(255),
audioFile varchar(255),
pubdate datetime,
primary key (id)
);
