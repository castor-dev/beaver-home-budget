CREATE TABLE budget_user
(
    id   uuid primary key,
    name varchar(255) not null
);

create table account
(
    id       uuid primary key,
    name     varchar(255) not null,
    balance  numeric      not null,
    owner_id uuid         not null,
    foreign key (owner_id) references budget_user (id)
);

CREATE TABLE transaction
(
    id          UUID primary key,
    description VARCHAR(255),
    value       NUMERIC not null,
    date        DATE    not null,
    is_credit   BOOLEAN not null,
    is_planned  BOOLEAN not null,
    account_id  uuid    not null,
    foreign key (account_id) references account (id)
);
