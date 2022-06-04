---------------------------------------------
-- category
---------------------------------------------
INSERT INTO shop.user (id, status, email, password, first_name, last_name, role) VALUES
    (nextval('shop.user_id_seq'), 'ACTIVE', 'user1@wp.pl', '$2a$10$9rdlBPfe5uhGITH23dgRx.3FvzR78SCqnuW2e0yt78THlxoMHYWb.', 'user1', 'user1', 'USER'),
    (nextval('shop.user_id_seq'), 'ACTIVE', 'user2@wp.pl', '$2a$10$caVyTrHN9JjoPn7470WNfepAGRPpbK2zJwzWk4A/GY2j6HdYrsE4i', 'user2', 'user2', 'USER'),
    (nextval('shop.user_id_seq'), 'ACTIVE', 'admin@wp.pl', '$2a$10$IshBIAokxAppEXa0CRf6E.lwsMVJ.L2omm1nTaPtZrBOJsHosAh72', 'admin', 'admin', 'ADMIN');
COMMIT;
