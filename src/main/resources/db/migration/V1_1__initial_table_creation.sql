
CREATE TABLE IF NOT EXISTS products (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) DEFAULT NULL,
  UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS units (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(10) DEFAULT NULL,
  denotation VARCHAR(5) DEFAULT NULL,
  CONSTRAINT UC_unit UNIQUE (name, denotation)
);

CREATE TABLE IF NOT EXISTS recipes (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) DEFAULT NULL,
  short_description VARCHAR(100) DEFAULT NULL,
  cooking_time INT DEFAULT NULL,
  usage_frequency INT DEFAULT NULL,
  servings_per_recipe INT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS users (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(20) DEFAULT NULL,
  last_name VARCHAR(20) DEFAULT NULL,
  username VARCHAR(20) DEFAULT NULL,
  password VARCHAR(20) DEFAULT NULL,
  email_address VARCHAR(120) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS ingredients (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT(8) NOT NULL,
  quantity FLOAT DEFAULT NULL,
  unit_id BIGINT(8) NOT NULL,
  recipe_id BIGINT(8) NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (unit_id) REFERENCES units (id),
  FOREIGN KEY (recipe_id) REFERENCES recipes (id)
);

CREATE TABLE IF NOT EXISTS cooking_steps (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  step_number INT DEFAULT NULL,
  description longtext,
  recipe_id BIGINT(8) DEFAULT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipes (id)
);

CREATE TABLE IF NOT EXISTS daily_plans (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT(8) NOT NULL,
  target_date DATE DEFAULT NULL,
  recipe_id BIGINT(8) NOT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipes (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS baskets (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT(8) DEFAULT NULL,
  start_date DATE DEFAULT NULL,
  end_date DATE DEFAULT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS product_basket (
  id BIGINT(8) PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT(8) DEFAULT NULL,
  quantity FLOAT DEFAULT NULL,
  unit_id BIGINT(8) DEFAULT NULL,
  status TINYINT DEFAULT NULL,
  basket_id BIGINT(8) DEFAULT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (unit_id) REFERENCES units (id),
  FOREIGN KEY (basket_id) REFERENCES baskets (id)
);
