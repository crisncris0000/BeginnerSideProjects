CREATE TABLE categories(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(50) NOT NULL
);

INSERT INTO categories (category) VALUES
 ("clothing"),
 ("cosmetics"),
 ("celebration"),
 ("other");

CREATE TABLE posts(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	img LONGBLOB NOT NULL,
	post TEXT,
    category_id INT,
    price DOUBLE,
    quantity INT,
    FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE authorities(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    authority VARCHAR(50)
);

INSERT INTO authorities(authority) VALUES
	("ROLE_USER"),
    ("ROLE_ADMIN");

CREATE TABLE users(
	username VARCHAR(100) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY(role_id) REFERENCES authorities(id)
);

CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    item_image LONGBLOB NOT NULL,
    item_price DOUBLE NOT NULL,
    item_quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(username)
);

INSERT INTO users(username, password, role_id) VALUES
("Admin", "$2a$10$Fu7F7oMMVfMjSTXbcFmuDu7u2x4G5dtpsEZFDZuBIP5sXRUjk4n4W", 2);

CREATE TABLE contact(
	id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(255),
	body TEXT
);

