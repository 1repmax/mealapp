
INSERT INTO products (name) VALUES ("flour");
INSERT INTO products (name) VALUES ("apples");
INSERT INTO products (name) VALUES ("eggs");
INSERT INTO products (name) VALUES ("sugar");

INSERT INTO units (name, denotation) VALUES ("grams", "g");
INSERT INTO units (name, denotation) VALUES ("pieces", "pcs");

INSERT INTO recipes (name, short_description, cooking_time, usage_frequency, servings_per_recipe) VALUES ("Pie", "Worlds best pie" ,3600, 1, 4);
INSERT INTO recipes (name, short_description, cooking_time, usage_frequency, servings_per_recipe) VALUES ("Omlet", "Easy to cook for beginners", 600, 1, 1);

-- Recipe for apple pie
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (1, 300, 1, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (2, 2, 2, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (3, 3, 2, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (4, 100, 1, 1);

INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (1, "Make butter from eggs and flour", 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (2, "Spread butter in baking pan and add apples and sugar", 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (3, "Put in the oven bake at 200 degrees", 1);

-- Recipe for omlet
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (3, 2, 2, 2);

INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (1, "Brake eggs and whisk eggs", 2);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (2, "Bake in butter on the pan", 2);
