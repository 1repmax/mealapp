INSERT INTO authorities(user_role) VALUES (
    'USER'
);

INSERT INTO users (name, password, enabled, authority_id) VALUES (
    'username', 'pw', 1, 1
);