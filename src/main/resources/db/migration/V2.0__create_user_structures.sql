DROP TABLE IF EXISTS "user" CASCADE;
CREATE TABLE "user" (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status character varying(25),
    uuid character varying(36),
    email character varying(45) NOT NULL,
    password character varying(64),
    first_name character varying(20) NOT NULL,
    last_name character varying(20) NOT NULL,
    role character varying(20) NOT NULL DEFAULT 'USER',
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS user_id_seq;
CREATE SEQUENCE user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
