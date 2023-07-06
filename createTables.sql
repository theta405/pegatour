CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    passwd VARCHAR(20) NOT NULL,
    id2 CHAR(12) NOT NULL,
    phone CHAR(11) NOT NULL,
    gender CHAR(1) NOT NULL,
    birthday DATE NOT NULL,
    money INT NOT NULL,
    book VARCHAR(200)
);

CREATE TABLE routes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destination VARCHAR(10) NOT NULL,
    date DATE NOT NULL
);