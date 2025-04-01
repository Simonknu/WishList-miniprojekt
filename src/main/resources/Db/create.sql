CREATE
DATABASE wishList;

USE
wishList;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password TEXT         NOT NULL
);

CREATE TABLE wishLists
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT          NOT NULL,
    wishList_name VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE wishes
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    wishList_id INT          NOT NULL,
    wish_name   VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    link        VARCHAR(255),
    FOREIGN KEY (wishList_id) REFERENCES wishLists (id) ON DELETE CASCADE

);


