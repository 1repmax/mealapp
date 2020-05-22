
INSERT INTO products (name) VALUES ("milti");
INSERT INTO products (name) VALUES ("aboli");
INSERT INTO products (name) VALUES ("olas");
INSERT INTO products (name) VALUES ("cukurs");

INSERT INTO units (name, denotation) VALUES ("grami", "g");
INSERT INTO units (name, denotation) VALUES ("gabali", "gab");

INSERT INTO recipes (name, short_description, cooking_time, usage_frequency, servings_per_recipe) VALUES ("Pīrāgs", "Labākais pīrāgs pasaulē" ,3600, 1, 4);
INSERT INTO recipes (name, short_description, cooking_time, usage_frequency, servings_per_recipe) VALUES ("Omlete", "Viegla recepte iesācējiem", 600, 1, 1);

-- Recipe for apple pie
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (1, 300, 1, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (2, 2, 2, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (3, 3, 2, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (4, 100, 1, 1);

INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (1, "No olām un miltiem sajauc mīklu", 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (2, "Mīklu liek veidnē un pārklāj ar āboliem un cukuru", 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (3, "Liek cepties cepeškrāsnī 200 grādos", 1);

-- Recipe for omlet
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (3, 2, 2, 2);

INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (1, "Pārsit olas un sakuļ", 2);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (2, "Cepj sviestā uz pannas", 2);