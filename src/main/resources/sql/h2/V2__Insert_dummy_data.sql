-- Inserting dummy data into books
INSERT INTO books (title, author, publication_year, isbn) VALUES
('To Kill a Mockingbird', 'Harper Lee', 1960, '978-0-435-00001-0'),
('1984', 'George Orwell', 1949, '978-0-452-00002-0'),
('The Great Gatsby', 'F. Scott Fitzgerald', 1925, '978-0-743-00003-0');

-- Inserting dummy data into patrons
INSERT INTO patrons (name, contact_information) VALUES
('John Doe', 'johndoe@email.com'),
('Jane Smith', 'janesmith@email.com'),
('Alice Johnson', 'alicejohnson@email.com');

-- Inserting dummy data into borrowing_records
INSERT INTO borrowing_records (book_id, patron_id, borrowed_date, return_date) VALUES
(1, 1, '2024-01-10', '2024-01-20'),
(2, 2, '2024-01-15', NULL),
(3, 1, '2024-01-18', NULL);
