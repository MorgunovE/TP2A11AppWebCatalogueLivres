INSERT INTO User (name, familyName, tel, email) VALUES
('John', 'Doe', '123-456-7890', 'john.doe@example.com'),
('Jane', 'Smith', '098-765-4321', 'jane.smith@example.com'),
('Alice', 'Johnson', '555-123-4567', 'alice.johnson@example.com');

INSERT INTO Livre (title, description, author, genre, image) VALUES
('The Great Gatsby', 'A novel set in the Roaring Twenties', 'F. Scott Fitzgerald', 'Fiction', 'great_gatsby.jpg'),
('To Kill a Mockingbird', 'A novel about racial injustice', 'Harper Lee', 'Fiction', 'to_kill_a_mockingbird.jpg'),
('1984', 'A dystopian novel', 'George Orwell', 'Science Fiction', '1984.jpg');

INSERT INTO Basket (user_id) VALUES
(1),
(2),
(3);

INSERT INTO Basket_Livre (basket_id, livre_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 1),
(3, 3);