insert into roles(rolename) values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_RENTER');

INSERT INTO users (username, password, phonenumber, email)
VALUES
    ('gebruiker', '$2y$10$rDxhAEdbaYBuIrjBd7ykruOKLa.vwcdLIgHUmBhEWicHk1mn.jehu', '123-456-7890', 'gebruiker@example.com'),
    ('huurder', '$2y$10$oRxIh4DkcX0BVs5XwFuZyOk.EIXNjy2qM7XmKYGkdFh6t/F/R76z2', '987-654-3210', 'huurder@example.com'),
    ('admin', '$2a$10$A5rTLtrPQyqah54yfDy0u..XXi5QXo2dUv95NH6daqizaNGqSjiQS', '555-555-5555', 'admin@example.com');

INSERT INTO users_roles (users_username, roles_rolename)
VALUES
    ('gebruiker', 'ROLE_USER'),
    ('huurder', 'ROLE_RENTER'),
    ('admin', 'ROLE_ADMIN');

INSERT INTO products (id, name, price, featured, image)
VALUES
    (1, 'Product 1', 100.0, true, NULL),
    (2, 'Product 2', 75.0, false, NULL),
    (3, 'Product 3', 120.0, true, NULL);