drop table  if exists authorities;
drop table  if exists users;
create table users(
                      username varchar(50) not null primary key,
                      password varchar(255) not null,
                      enabled TINYINT not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);