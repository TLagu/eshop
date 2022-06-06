---------------------------------------------
-- category
---------------------------------------------
INSERT INTO "user" (id, status, uuid, email, password, first_name, last_name, role) VALUES
    (nextval('shop.user_id_seq'), 'ACTIVE', '53c069de-e435-11ec-8fea-0242ac120002', 'user1@wp.pl', '$2a$10$9rdlBPfe5uhGITH23dgRx.3FvzR78SCqnuW2e0yt78THlxoMHYWb.', 'user1', 'user1', 'USER'),
    (nextval('shop.user_id_seq'), 'ACTIVE', '53c06b8c-e435-11ec-8fea-0242ac120002', 'user2@wp.pl', '$2a$10$caVyTrHN9JjoPn7470WNfepAGRPpbK2zJwzWk4A/GY2j6HdYrsE4i', 'user2', 'user2', 'USER'),
    (nextval('shop.user_id_seq'), 'ACTIVE', '53c06d62-e435-11ec-8fea-0242ac120002', 'admin@wp.pl', '$2a$10$IshBIAokxAppEXa0CRf6E.lwsMVJ.L2omm1nTaPtZrBOJsHosAh72', 'admin', 'admin', 'ADMIN');
COMMIT;
