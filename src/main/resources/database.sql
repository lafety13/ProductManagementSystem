CREATE TABLE products (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  salary REAL NOT NULL,
  producer VARCHAR(50) NOT NULL,
  description VARCHAR(255) NOT NULL
);

CREATE TABLE users (
  id       SERIAL          NOT NULL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
  id   SERIAL          NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
);


INSERT INTO products (name, salary, producer, description) VALUES ('book', 1000, 'BBC', 'description');

--login: admin, pass: admin
--login: user, pass: user
INSERT INTO users (username, password)
VALUES ('admin', '$2a$11$U33AX0vbNZPIbMRdnPTo8upHHvst5IBNtZfavhoXiFvwty17p3cHm'),
  ('user', '$2a$11$BoMnIGGIfFzLVtR.XT9W5ukn303aR/04LfMWvgJl./YRwmoLEfgrq');
INSERT INTO roles (name)  VALUES ('ROLE_USER'),('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id)  VALUES (1, 2), (2, 1);