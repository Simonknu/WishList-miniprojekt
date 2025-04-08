DROP TABLE IF EXISTS wishes;
DROP TABLE IF EXISTS wishlists;
DROP TABLE IF EXISTS users;


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
    wish_name   VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    link        VARCHAR(255),
    FOREIGN KEY (wishList_id) REFERENCES wishLists (id) ON DELETE CASCADE
);

INSERT INTO users (username, password)
VALUES
    ('alice', 'hashed_password1'),
    ('bob', 'hashed_password2');

-- Insert WishLists
INSERT INTO wishLists (user_id, wishList_name)
VALUES
    (1, 'Alice’s Birthday Wishes'),  -- Alice's wishlist
    (2, 'Bob’s Travel Dreams');       -- Bob's wishlist

-- Insert Wishes
INSERT INTO wishes (wishList_id, wish_name, description, link)
VALUES
    (1, 'New Laptop', 'desc', 'link'),               -- Wish for Alice's Birthday Wishlist
    (1, 'Trip to Japan', 'desc', 'link'),            -- Wish for Alice's Birthday Wishlist
    (2, 'Hiking Gear', 'desc', 'link'),              -- Wish for Bob's Travel Wishlist
    (2, 'Camera', 'desc', 'link');                   -- Wish for Bob's Travel Wishlist
