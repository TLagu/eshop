---------------------------------------------
-- category
---------------------------------------------
INSERT INTO category (id, created_by, updated_by, status, name, description) VALUES
    (nextval('category_id_seq'), 1, 1, 'ACTIVE', 'RAM', 'RAM'),
    (nextval('category_id_seq'), 1, 1, 'ACTIVE', 'CPU', 'CPU'),
    (nextval('category_id_seq'), 1, 1, 'ACTIVE', 'GPU', 'GPU');
COMMIT;
INSERT INTO category (id, created_by, updated_by, status, name, description, parent_id)
    (
        SELECT nextval('category_id_seq'), 1, 1, 'ACTIVE', 'AMD', 'AMD', id FROM category WHERE name = 'CPU' UNION
        SELECT nextval('category_id_seq'), 1, 1, 'ACTIVE', 'Intel', 'Intel', id FROM category WHERE name = 'CPU' UNION
        SELECT nextval('category_id_seq'), 1, 1, 'ACTIVE', 'ATI', 'ATI', id FROM category WHERE name = 'GPU' UNION
        SELECT nextval('category_id_seq'), 1, 1, 'ACTIVE', 'nVidia', 'nVidia', id FROM category WHERE name = 'GPU'
    );
COMMIT;
---------------------------------------------
-- product
---------------------------------------------
INSERT INTO product (id, created_by, updated_by, status, uuid, model, description, category_id) VALUES
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '4da99500-5bf8-4710-b005-bca9aaede908', 'Model 1', 'Description 1', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '2f68aaa0-39bf-46f9-bd67-62ea34128e00', 'Model 2', 'Description 2', 4),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '4ac53f58-b2c9-46ab-8b4e-c3235459c8c2', 'Model 3', 'Description 3', 6),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'cd324667-e1f7-4d06-91c2-4b31f8732ec6', 'Model 4', 'Description 4', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'c75b0937-9055-40c3-9ff9-22070d76d9a6', 'Model 5', 'Description 5', 4),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '411a5019-6d7f-4dad-90c6-356c77792312', 'Model 6', 'Description 6', 6),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'c39b14f5-ca1b-4b35-baa5-5c1690a6be50', 'Model 7', 'Description 7', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '987917ad-e36c-452e-9a36-526ba59421cd', 'Model 8', 'Description 8', 5),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '6f5f42f5-5ca1-4bd7-80d0-56a05c8fdc35', 'Model 9', 'Description 9', 6),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '3a20fda0-1f71-40e2-8043-91a0e5b4a037', 'Model 10', 'Description 10', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'cdad6f96-48f6-4c0a-887d-1de91c8aae1d', 'Model 11', 'Description 11', 5),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '88eef6ca-cb9c-41c5-8a44-416ab4bd88bc', 'Model 12', 'Description 12', 7),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '51d2fb72-a0f1-4d66-a936-f34daab50ce9', 'Model 13', 'Description 13', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '6fc3bbdc-9543-46e1-b733-6e58429343c2', 'Model 14', 'Description 14', 2),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '119e71da-8b67-4b21-baf8-9b86495cd985', 'Model 15', 'Description 15', 3),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '285bc12d-2885-45df-afe2-7d30bc951386', 'Model 16', 'Description 16', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'e259bd28-dffe-444b-83dd-00a328891a70', 'Model 17', 'Description 17', 2),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '7f9e83b3-339c-4646-b4cb-b3efe0cf42be', 'Model 18', 'Description 18', 3),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '5a0f2eab-b6f6-4d81-9ef4-5233dacdce3e', 'Model 19', 'Description 19', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'd627125c-9fd0-446a-897a-8e19b881c5b3', 'Model 20', 'Description 20', 2),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'fbd26bc3-701b-43bf-bea3-11d3f9998402', 'Model 21', 'Description 21', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '9af9bcf4-2fea-4d5f-925f-7eddc1c0f948', 'Model 22', 'Description 22', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'a91be5a0-b771-4002-804c-78e366ee5ec6', 'Model 23', 'Description 23', 2),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '51c449b8-2037-44b9-a420-c57bf36b35e9', 'Model 24', 'Description 24', 3),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'c2a36959-fd80-4b4f-9bb0-2798f65fde14', 'Model 25', 'Description 25', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '1ceacfd5-5c25-4094-8e8a-75b9b262f4a7', 'Model 26', 'Description 26', 2),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'a6635111-cf58-4dfc-a1b2-8d9ab789c374', 'Model 27', 'Description 27', 3),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '94c0cb71-54c5-483d-995e-a292c193d60e', 'Model 28', 'Description 28', 1),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', 'e6fbd22f-361e-461e-b646-df71db896598', 'Model 29', 'Description 29', 2),
    (nextval('product_id_seq'), 1, 1, 'ACTIVE', '75c01bbb-91c3-4e33-82fa-d7fe4b5bafbe', 'Model 30', 'Description 30', 3);
COMMIT;
---------------------------------------------
-- attribute
---------------------------------------------
INSERT INTO attribute (id, created_by, updated_by, status, product_id, name, description) VALUES
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 1, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 1, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 1, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 4, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 4, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 4, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 7, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 7, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 7, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 10, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 10, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 10, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 13, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 13, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 13, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 16, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 16, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 16, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 19, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 19, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 19, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 22, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 22, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 22, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 25, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 25, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 25, 'RAM 3', 'RAM 3'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 28, 'RAM 1', 'RAM 1'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 28, 'RAM 2', 'RAM 2'),
    (nextval('attribute_id_seq'), 1, 1, 'ACTIVE', 28, 'RAM 3', 'RAM 3');
COMMIT;
