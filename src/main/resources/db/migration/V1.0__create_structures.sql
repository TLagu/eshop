---------------------------------------------
-- attribute
---------------------------------------------
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
---------------------------------------------
-- product
---------------------------------------------
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
    price numeric(10, 2),
    path character varying(200),
    code character varying(50),
    CONSTRAINT product_pkey PRIMARY KEY (id)
);
DROP SEQUENCE IF EXISTS product_id_seq;
CREATE SEQUENCE product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------
-- category
---------------------------------------------
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
    parent_id bigint,
    CONSTRAINT category_pkey PRIMARY KEY (id)
);
DROP SEQUENCE IF EXISTS category_id_seq;
CREATE SEQUENCE category_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------
-- card
---------------------------------------------
DROP TABLE IF EXISTS cart CASCADE;
CREATE TABLE cart (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id bigint,
    product_id bigint,
    amount integer default 1,
    total numeric(10, 2),
    CONSTRAINT cart_pkey PRIMARY KEY (id)
);
DROP SEQUENCE IF EXISTS cart_id_seq;
CREATE SEQUENCE cart_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------
-- wishlist
---------------------------------------------
DROP TABLE IF EXISTS wishlist CASCADE;
CREATE TABLE wishlist (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id bigint,
    product_id bigint,
    CONSTRAINT wishlist_pkey PRIMARY KEY (id)
);
DROP SEQUENCE IF EXISTS wishlist_id_seq;
CREATE SEQUENCE wishlist_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------
-- compare
---------------------------------------------
DROP TABLE IF EXISTS compare CASCADE;
CREATE TABLE compare (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id bigint,
    product_id bigint,
    CONSTRAINT compare_pkey PRIMARY KEY (id)
);
DROP SEQUENCE IF EXISTS compare_id_seq;
CREATE SEQUENCE compare_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------
-- order_main
---------------------------------------------
DROP TABLE IF EXISTS order_main CASCADE;
CREATE TABLE order_main (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    uuid character varying(36),
    user_id bigint,
    street character varying(100),
    post_code character varying(6),
    post character varying(50),
    total numeric(10, 2),
    CONSTRAINT order_main_pkey PRIMARY KEY (id)
);
DROP SEQUENCE IF EXISTS order_main_id_seq;
CREATE SEQUENCE order_main_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------
-- order_details
---------------------------------------------
DROP TABLE IF EXISTS order_details CASCADE;
CREATE TABLE order_details (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    order_id bigint,
    product_id bigint,
    price numeric(10, 2),
    amount integer,
    total numeric(10, 2),
    CONSTRAINT order_details_pkey PRIMARY KEY (id)
);
DROP SEQUENCE IF EXISTS order_details_id_seq;
CREATE SEQUENCE order_details_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
