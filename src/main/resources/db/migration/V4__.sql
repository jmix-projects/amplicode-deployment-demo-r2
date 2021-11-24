CREATE TABLE pmsg_message
(
    code    VARCHAR(255) NOT NULL,
    values_ TEXT         NOT NULL,
    CONSTRAINT pk_pmsg_message PRIMARY KEY (code)
);