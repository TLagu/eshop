DROP TABLE IF EXISTS card CASCADE;
CREATE TABLE card (
    id bigint NOT NULL,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id bigint,
    product_id bigint,
    CONSTRAINT card_pkey PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS card_id_seq;
CREATE SEQUENCE card_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

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
