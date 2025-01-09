CREATE DATABASE wildlife_tracker;

USE wildlife_tracker;

CREATE TABLE sightings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    animal_name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    sighting_date DATE NOT NULL,
    sighting_time TIME NOT NULL
);
