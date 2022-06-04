DROP TABLE IF EXISTS shop.user CASCADE;
CREATE TABLE shop.user (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status character varying(25),
    email character varying(45) NOT NULL,
    password character varying(64),
    first_name character varying(20) NOT NULL,
    last_name character varying(20) NOT NULL,
    role character varying(20) NOT NULL DEFAULT 'USER',
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS shop.user_id_seq;
CREATE SEQUENCE shop.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
