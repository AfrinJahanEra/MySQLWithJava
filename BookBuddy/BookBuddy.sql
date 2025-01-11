DROP DATABASE BookBuddy;
CREATE DATABASE BookBuddy;

USE BookBuddy;

-- Table for Users
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

-- Table for Books
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    category VARCHAR(50),
    published_year INT
);

-- Table for Ratings (Each user can rate a book, creating many-to-many relationships)
CREATE TABLE ratings (
    user_id INT,
    book_id INT,
    rating INT CHECK(rating BETWEEN 1 AND 5),
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Table for Tracking User Book Interactions (For big data management)
CREATE TABLE user_interactions (
    user_id INT,
    book_id INT,
    interaction_type VARCHAR(50), -- "read", "wishlisted", "rated"
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, book_id, interaction_type),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);
