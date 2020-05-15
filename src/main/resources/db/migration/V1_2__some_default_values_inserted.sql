
INSERT INTO products (name) VALUES ("milti");
INSERT INTO products (name) VALUES ("aboli");
INSERT INTO products (name) VALUES ("olas");
INSERT INTO products (name) VALUES ("cukurs");

INSERT INTO units (name, denotation) VALUES ("grami", "g");
INSERT INTO units (name, denotation) VALUES ("gabali", "gab");

INSERT INTO recipes (name, cooking_time, usage_frequency, servings_per_recipe) VALUES ("Pīrāgs", 3600, 1, 4);

INSERT INTO ingredients (product_id, quantity, units_id, recipe_id) VALUES (1, 300, 1, 1);
INSERT INTO ingredients (product_id, quantity, units_id, recipe_id) VALUES (2, 2, 2, 1);
INSERT INTO ingredients (product_id, quantity, units_id, recipe_id) VALUES (3, 3, 2, 1);
INSERT INTO ingredients (product_id, quantity, units_id, recipe_id) VALUES (4, 100, 1, 1);