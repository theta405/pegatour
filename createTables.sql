CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    passwd VARCHAR(20) NOT NULL,
    id2 CHAR(12) NOT NULL,
    phone CHAR(11) NOT NULL,
    gender CHAR(1) NOT NULL,
    birthday CHAR(10) NOT NULL,
    money INT NOT NULL,
    book VARCHAR(200)
);

CREATE TABLE routes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destination VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    date CHAR(10) NOT NULL
);

CREATE TABLE fmdate (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date CHAR(10) NOT NULL
);

INSERT INTO fmdate (date) VALUES ('1000-01-01');