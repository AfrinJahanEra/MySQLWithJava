CREATE DATABASE hobby_organizer;

USE hobby_organizer;

CREATE TABLE hobbies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hobby_name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    skill_level VARCHAR(50),
    hours_spent INT DEFAULT 0,
    milestones TEXT
);
