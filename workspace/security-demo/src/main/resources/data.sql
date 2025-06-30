insert into  users (username, password, enabled) values ('jack','$2a$12$E2vIwjFp7pBnsb2sj0ezRenm6Ru5yiZmdyBwKI.4S46ZVucg3K0ee', 1);
insert into  users (username, password, enabled) values ('priya','$2a$12$fsLSi0h2YmQV2Oc4LwNSy.5808ysAo7nZ.wYvaJOgl9Q3dUto03dC', 1);

insert into authorities(username, authority) values( 'jack', 'ROLE_CUSTOMER');
insert into authorities(username, authority) values( 'priya', 'ROLE_CUSTOMER');
insert into authorities(username, authority) values( 'priya', 'ROLE_ADMIN');
