BEGIN;
CREATE TABLE asteroid(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    size INTEGER,
    shape VARCHAR(50),
    density TEXT,
    speed INTEGER NOT NULL,
    compound VARCHAR(50),
    coordinate VARCHAR(50),
    radiation INT,
    threats TEXT
);

CREATE TABLE probe(
    id SERIAL PRIMARY KEY,
    type VARCHAR(20),
    timeoflaunch TIMESTAMP,
    power INTEGER,
    coordinate TEXT,
    result TEXT,
    asteroid_id INTEGER REFERENCES asteroid(id)
);

CREATE TABLE camera(
    id SERIAL PRIMARY KEY,
    type VARCHAR(30) NOT NULL,
    number_of_shots INTEGER NOT NULL,
    permission INT,
    timeofshot TIMESTAMP,
    frequency INTEGER,
    location VARCHAR(30),
    probe_id INTEGER REFERENCES probe(id)
);

CREATE TABLE signal(
    id SERIAL PRIMARY KEY,
    frequency INTEGER,
    duration VARCHAR(30),
    timeofrecording TIMESTAMP,
    data_volume INTEGER,
    asteroid_id INTEGER REFERENCES asteroid(id),
    camera_id INTEGER REFERENCES camera(id)
);

CREATE TABLE observation(
    id SERIAL PRIMARY KEY,
    method TEXT,
    result TEXT,
    timeofobservation TIMESTAMP,
    location VARCHAR(50),
    signal_id INTEGER REFERENCES signals(id)
);

CREATE TABLE shot_to_observation(
    observation_id INTEGER REFERENCES observations(id),
    camera_id INTEGER REFERENCES cameras(id)
);

CREATE TABLE signal_to_observation(
    signal_id INTEGER REFERENCES signal(id),
    probe_id INTEGER REFERENCES probe(id)
);

INSERT INTO asteroid (name, size, shape, density, speed, compound, coordinate, radiation, threats) 
VALUES 
('Apollo', 1200, 'Elliptical', 'High', 25000, 'Iron-Nickel', '100.5, 200.3', 35, 'None'),
('Chiron', 650, 'Spherical', 'Medium', 30000, 'Silicate', '150.2, 300.7', 15, 'Low'),
('Dione', 800, 'Irregular', 'Low', 20000, 'Carbonaceous', '200.6, 400.4', 45, 'Medium'),
('Europa', 950, 'Elliptical', 'High', 35000, 'Metallic', '250.7, 500.5', 40, 'High'),
('Galatea', 700, 'Spherical', 'Medium', 22000, 'Silicate', '300.9, 600.6', 20, 'None'),
('Hyperion', 1200, 'Irregular', 'Low', 27000, 'Carbonaceous', '350.1, 700.7', 25, 'Low'),
('Io', 850, 'Elliptical', 'High', 28000, 'Metallic', '400.5, 800.8', 30, 'Medium'),
('Juno', 900, 'Spherical', 'Medium', 31000, 'Silicate', '450.7, 900.9', 50, 'High'),
('Kallisto', 750, 'Irregular', 'Low', 26000, 'Iron-Nickel', '500.3, 1000.2', 10, 'None'),
('Leto', 1000, 'Elliptical', 'High', 33000, 'Carbonaceous', '550.7, 1100.4', 55, 'Medium');

INSERT INTO probe (type, timeoflaunch, power, coordinate, result, asteroid_id) 
VALUES 
('Orbiter', '2022-01-10 10:00:00', 500, '100.5, 200.3', 'Data collected', 1),
('Rover', '2023-03-15 12:30:00', 700, '150.2, 300.7', 'Samples analyzed', 2),
('Lander', '2024-05-20 14:45:00', 600, '200.6, 400.4', 'Landing successful', 3),
('Flyby', '2021-07-25 08:15:00', 800, '250.7, 500.5', 'Images captured', 4),
('Driller', '2022-09-10 11:00:00', 750, '300.9, 600.6', 'Drilling completed', 5),
('Orbiter', '2023-11-30 16:00:00', 650, '350.1, 700.7', 'Data collected', 6),
('Rover', '2024-02-14 09:45:00', 700, '400.5, 800.8', 'Samples analyzed', 7);


INSERT INTO camera (type, number_of_shots, permission, timeofshot, frequency, location, probe_id) 
VALUES 
('Wide-angle', 100, 1, '2022-02-10 10:30:00', 60, 'Mars', 1),
('High-resolution', 200, 2, '2023-04-15 12:00:00', 45, 'Moon', 2),
('Infrared', 150, 3, '2024-06-20 15:30:00', 30, 'Europa', 3),
('Thermal', 120, 4, '2021-08-25 09:45:00', 90, 'Venus', 4),
('3D', 180, 5, '2022-10-10 14:00:00', 75, 'Titan', 5),
('Panoramic', 220, 6, '2023-12-31 17:30:00', 50, 'Phobos', 6),
('Ultra-wide', 140, 7, '2024-03-14 08:15:00', 100, 'Europa', 7);

INSERT INTO signal (frequency, duration, timeofrecording, data_volume, asteroid_id, camera_id) 
VALUES 
(50, '5 minutes', '2022-04-15 11:30:00', 500, 1, 1),
(100, '10 minutes', '2022-04-15 11:30:00', 600, 2, 2),
(150, '15 minutes', '2024-06-20 14:45:00', 700, 3, 3),
(200, '20 minutes', '2024-06-20 14:45:00', 800, 4, 4),
(250, '25 minutes', '2023-12-10 13:15:00', 900, 5, 5),
(300, '30 minutes', '2024-11-30 15:30:00', 1000, 6, 6),
(350, '35 minutes', '2025-03-14 16:00:00', 1100, 7, 7);

INSERT INTO observation (method, result, timeofobservation, location, signal_id) 
VALUES 
('Spectroscopy', 'Chemical analysis', '2022-05-10 11:00:00', 'Mars', 1),
('Radio Waves', 'Signal analysis', '2023-07-15 12:30:00', 'Europa', 2),
('Thermal Imaging', 'Heat signatures detected', '2024-10-20 15:45:00', 'Titan', 3),
('Photography', 'Images taken', '2025-02-25 08:15:00', 'Venus', 4),
('Magnetic Field', 'Magnetic anomalies detected', '2023-11-10 09:00:00', 'Earth Orbit', 5),
('Gravity Measurement', 'Gravitational anomalies detected', '2024-01-30 14:00:00', 'Moon', 6),
('Gravity Measurement', 'Gravitational anomalies detected', '2024-01-30 14:00:00', 'Moon', 7);

INSERT INTO shot_to_observation (observation_id, camera_id) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(1, 7),
(1, 6),
(1, 2),
(2, 7);

INSERT INTO signal_to_observation (signal_id, probe_id) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7);