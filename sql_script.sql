CREATE DATABASE IF NOT EXISTS ticketing_system;
USE ticketing_system;

DROP TABLE IF EXISTS tickets;
CREATE TABLE IF NOT EXISTS tickets(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    issue VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    resolved TINYINT DEFAULT 0,
    closed TINYINT DEFAULT 0
) AUTO_INCREMENT = 1;