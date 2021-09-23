INSERT INTO users (username, password, enabled)
VALUES ('admin', '{noop}admin', true);
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO users (username, password, enabled)
VALUES ('anton', '{noop}anton', true);
INSERT INTO authorities (username, authority)
VALUES ('anton', 'ROLE_OWNER');

INSERT INTO users (username, password, enabled)
VALUES ('alex', '{noop}alex', true);
INSERT INTO authorities (username, authority)
VALUES ('alex', 'ROLE_OWNER');

INSERT INTO users (username, password, enabled)
VALUES ('doc', '{noop}doc', true);
INSERT INTO authorities (username, authority)
VALUES ('doc', 'ROLE_VETERINARIAN');


INSERT INTO pet_type(name)
VALUES ('DOG');

INSERT INTO owner(first_name, last_name, address, city, username)
VALUES ('Anton', 'Ivanov', 'Spring 10, 25','Samara', 'anton');

INSERT INTO owner(first_name, last_name, address, city, username)
VALUES ('Alex', 'Sidorov', 'Foo 10, 25','Samara', 'alex');

INSERT INTO veterinarian(first_name, last_name, username)
VALUES ('John', 'Doe', 'doc');

INSERT INTO pet(identification_number, owner_id, type_id)
VALUES ('DOG_1', 1, 1);

INSERT INTO visit(pet_id, visit_start, description, state)
values (1, now(), 'Some description', 'PLANNED')

