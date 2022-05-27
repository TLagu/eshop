DROP TABLE IF EXISTS attribute CASCADE;
CREATE TABLE attribute (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status character varying(25),
    product_id bigint,
    name character varying(200),
    description text,
    CONSTRAINT attribute_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS attribute_id_seq;
CREATE SEQUENCE attribute_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status character varying(25),
    uuid character varying(36),
    model character varying(200),
    description text,
    category_id bigint,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS product_id_seq;
CREATE SEQUENCE product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status character varying(25),
    name character varying(200),
    description text,
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS category_id_seq;
CREATE SEQUENCE category_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
