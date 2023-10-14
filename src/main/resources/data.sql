insert into roles(rolename) values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_RENTER');

INSERT INTO users (username, password, phonenumber, email)
VALUES
    ('gebruiker', '$2a$10$NrJxx/Km4ilYUwyrb5k1uOhDYR4IFXntYIQuv/X3nR3b/AOU01FEy', '123-456-7890', 'gebruiker@example.com'),
    ('huurder', '$2a$10$IkXxYDaSJOQknArJWPvYvuUsx1947YDz2h/KnqDkgYvtGdO3ZDgCG', '987-654-3210', 'huurder@example.com'),
    ('admin', '$2a$10$vsDkDo1ZEH7xH23Ogf6vcO9LjVr8PmS.mLoJUPlbqfWcMNWZv/yPW', '555-555-5555', 'admin@example.com');

INSERT INTO users_roles (users_username, roles_rolename)
VALUES
    ('gebruiker', 'ROLE_USER'),
    ('huurder', 'ROLE_RENTER'),
    ('admin', 'ROLE_ADMIN');

-- Insert products into the 'products' table
INSERT INTO products (id ,name, price, featured, image)
VALUES
    (1, 'Product 1', 100.0, true, NULL),
    (2, 'Product 2', 75.0, false, NULL),
    (3, 'Product 3', 120.0, true, NULL);
