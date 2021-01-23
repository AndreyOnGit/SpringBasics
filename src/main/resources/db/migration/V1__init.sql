create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  score                 int not null,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

create TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, score)
values
('admin', '$2y$12$zPDfRN8fKzeZBnbr/cXOjehuyG7fhqFyCLzkoF.EQ6Yi5xlPXtQD6', 55),
('user', '$2y$12$zPDfRN8fKzeZBnbr/cXOjehuyG7fhqFyCLzkoF.EQ6Yi5xlPXtQD6', 22);

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(2, 1);
