INSERT INTO users (username, password, enabled)
VALUES ('admin', '{noop}admin', true);
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO users (username, password, enabled)
VALUES ('user', '{noop}user', true);
INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER');

INSERT INTO pet_type(name)
VALUES ('DOG');

INSERT INTO owner(first_name, last_name, address, city)
VALUES ('Anton', 'Ivanov', 'Spring 10, 25','Samara');

INSERT INTO pet(identification_number, owner_id, type_id)
VALUES ('DOG_1', 1, 1);

INSERT INTO visit(pet_id, visit_start, visit_end, description)
values (1, now(), now(), 'Some description');

INSERT INTO test_entity(id, string, bool, double_test, int_test, long_test, big_decimal, date, time, time_stamp, date_util, uuid_test, byte_test, character, class_test, float_test, short_test, big_int, url, blob, clob, duration, instant, local_date, local_date_time, local_time, offset_date_time, offset_time, zoned_date_time, calendar, currency, locale, bool_primitive, byte_primitive, char_primitive, short_primitive, byte_array, char_array, byte_primitive_array, char_primitive_array)
VALUES (1, '1234', true, 2.432, 12, 1234567890, 111111111112.43, '2021-09-16', '12:01:15', '2021-09-02 11:32:13.000000', '2021-09-10', '123e4567-e89b-12d3-a456-426614174000', 34, 'V', 'java.lang.String', 2.344, 42, 321123, 'https://developer.mozilla.org/ru/docs/Learn/', null, '123321', 100500, '2021-09-02 12:37:02.000000', '2021-09-02', '2021-09-06 12:37:18.000000', '12:23:43', '2021-09-08 12:37:44.878000', '11:43:43.000000', '2021-09-08 12:38:15.450000', '2021-09-17', 'USD', 'en-US', false, 24, 's', 34, null, 'dagfadczdsaf', null, 'dassdf');

