-- Initialize TPCafe Database
-- This script runs automatically when MySQL container starts for the first time

CREATE DATABASE IF NOT EXISTS tpcafe CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE tpcafe;

-- Grant privileges to the application user
GRANT ALL PRIVILEGES ON tpcafe.* TO 'tpcafe_user'@'%';
FLUSH PRIVILEGES;

-- Optional: Insert initial data if needed
-- INSERT INTO client (nom, prenom, email, telephone, date_naissance) VALUES ('John', 'Doe', 'john.doe@example.com', '1234567890', '1990-01-01');
