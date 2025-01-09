CREATE DATABASE finance_manager;

USE finance_manager;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    income DOUBLE NOT NULL
);

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    category VARCHAR(50) NOT NULL,
    amount DOUBLE NOT NULL,
    expense_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE savings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    goal DOUBLE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


