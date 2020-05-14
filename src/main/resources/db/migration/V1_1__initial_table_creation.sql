
CREATE TABLE IF NOT EXISTS products (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS units (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(10) DEFAULT NULL,
  denotation varchar(5) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS recipes (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  cooking_time int DEFAULT NULL,
  usage_frequency int DEFAULT NULL,
  servings_per_recipe int DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS users (
  id int PRIMARY KEY AUTO_INCREMENT,
  firstname varchar(20) DEFAULT NULL,
  lastname varchar(20) DEFAULT NULL,
  username varchar(20) DEFAULT NULL,
  password varchar(20) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS ingredients (
  id int PRIMARY KEY AUTO_INCREMENT,
  product_id int NOT NULL,
  quantity float DEFAULT NULL,
  units_id int NOT NULL,
  recipe_id int NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (units_id) REFERENCES units (id),
  FOREIGN KEY (recipe_id) REFERENCES recipes (id)
);


CREATE TABLE IF NOT EXISTS cooking_steps (
  id int PRIMARY KEY AUTO_INCREMENT,
  step_number int DEFAULT NULL,
  description longtext,
  recipe_id int NOT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipes (id)
);


CREATE TABLE IF NOT EXISTS daily_plans (
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int NOT NULL,
  date date DEFAULT NULL,
  recipe_id int NOT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipes (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);



CREATE TABLE IF NOT EXISTS basket (
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id int DEFAULT NULL,
  quantity float DEFAULT NULL,
  unit_id int DEFAULT NULL,
  status tinyint DEFAULT NULL,
  user_id int DEFAULT NULL,
  start_date date DEFAULT NULL,
  end_date date DEFAULT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (unit_id) REFERENCES units (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);
