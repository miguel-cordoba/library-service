DELETE FROM loan;
DELETE FROM member;
DELETE FROM book;
DELETE FROM author;

INSERT INTO authors (id, name, date_of_birth) VALUES (1, 'J.K. Rowling', '1965-07-31');
INSERT INTO authors (id, name, date_of_birth) VALUES (2, 'George Orwell', '1903-06-25');
INSERT INTO authors (id, name, date_of_birth) VALUES (3, 'J.R.R. Tolkien', '1892-01-03');

INSERT INTO book (id, title, genre, price, author_id) VALUES (1, 'Harry Potter and the Philosopher Stone', 'Fantasy', 19.99, 1);
INSERT INTO book (id, title, genre, price, author_id) VALUES (2, '1984', 'Dystopian', 14.99, 2);
INSERT INTO book (id, title, genre, price, author_id) VALUES (3, 'The Hobbit', 'Fantasy', 12.99, 3);

INSERT INTO book (id, title, genre, price, author_id) VALUES (4, 'Harry Potter and the Chamber of Secrets', 'Fantasy', 21.99, 1);
INSERT INTO book (id, title, genre, price, author_id) VALUES (5, 'Animal Farm', 'Dystopian', 10.99, 2);

INSERT INTO member (id, name, email, membership_date) VALUES (1, 'Alice Johnson', 'alice.johnson@example.com', '2020-05-10');
INSERT INTO member (id, name, email, membership_date) VALUES (2, 'Bob Smith', 'bob.smith@example.com', '2021-08-15');
INSERT INTO member (id, name, email, membership_date) VALUES (3, 'Charlie Brown', 'charlie.brown@example.com', '2022-03-20');

INSERT INTO loan (id, loan_date, return_date, book_id, member_id) VALUES (1, '2023-01-01', '2023-02-01', 1, 1);
INSERT INTO loan (id, loan_date, return_date, book_id, member_id) VALUES (2, '2023-03-01', '2023-03-15', 4, 1);

INSERT INTO loan (id, loan_date, return_date, book_id, member_id) VALUES (3, '2023-02-10', '2023-03-10', 2, 2);

INSERT INTO loan (id, loan_date, return_date, book_id, member_id) VALUES (4, '2023-04-01', '2023-04-15', 3, 3);

INSERT INTO loan (id, loan_date, return_date, book_id, member_id) VALUES (5, '2023-05-01', '2023-05-15', 5, 1);
