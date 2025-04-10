CREATE DATABASE ptfms;
USE ptfms;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('Manager', 'Operator') NOT NULL
);

CREATE TABLE Vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    number VARCHAR(50) UNIQUE NOT NULL,
    fuel_type VARCHAR(50) NOT NULL,
    consumption_rate DOUBLE NOT NULL
);

CREATE TABLE GPSData (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    timestamp DATETIME NOT NULL,
    location VARCHAR(255) NOT NULL,
    status ENUM('Active', 'Break', 'Out-of-Service') NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicles(id)
);

CREATE TABLE FuelConsumption (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    date DATE NOT NULL,
    consumption DOUBLE NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicles(id)
);

CREATE TABLE MaintenanceRecords (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    component VARCHAR(100) NOT NULL,
    wear_level DOUBLE NOT NULL,
    last_maintenance DATE,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicles(id)
);

-- Sample Data
INSERT INTO Users (name, email, password, role) VALUES
('Alice Manager', 'alice@example.com', 'pass123', 'Manager'),
('Bob Operator', 'bob@example.com', 'pass456', 'Operator');

INSERT INTO Vehicles (type, number, fuel_type, consumption_rate) VALUES
('Diesel Bus', 'BUS001', 'Diesel', 0.5),
('Electric Light Rail', 'RAIL001', 'Electric', 0.3);

INSERT INTO GPSData (vehicle_id, timestamp, location, status) VALUES
(1, NOW(), 'Lat:45.4215,Lng:-75.6972', 'Active'),
(2, NOW(), 'Lat:45.4315,Lng:-75.7072', 'Break');

INSERT INTO FuelConsumption (vehicle_id, date, consumption) VALUES
(1, CURDATE(), 50.0),
(2, CURDATE(), 30.0);

INSERT INTO MaintenanceRecords (vehicle_id, component, wear_level, last_maintenance) VALUES
(1, 'Brakes', 0.8, '2023-09-01'),
(2, 'Pantograph', 0.4, '2023-07-20');

