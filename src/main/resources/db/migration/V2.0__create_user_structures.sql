DROP TABLE IF EXISTS shop.attribute;
CREATE TABLE shop.attribute (
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

DROP SEQUENCE IF EXISTS shop.attribute_id_seq;
CREATE SEQUENCE shop.attribute_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS shop.product;
CREATE TABLE shop.product (
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

DROP SEQUENCE IF EXISTS shop.product_id_seq;
CREATE SEQUENCE shop.product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS shop.category;
CREATE TABLE shop.category (
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

DROP SEQUENCE IF EXISTS shop.category_id_seq;
CREATE SEQUENCE shop.category_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
