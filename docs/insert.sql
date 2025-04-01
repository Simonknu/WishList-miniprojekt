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
