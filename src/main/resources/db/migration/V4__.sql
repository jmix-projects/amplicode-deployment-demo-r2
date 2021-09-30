CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE file_ref
(
    id         BIGINT NOT NULL,
    content_id VARCHAR(255),
    CONSTRAINT pk_file_ref PRIMARY KEY (id)
);