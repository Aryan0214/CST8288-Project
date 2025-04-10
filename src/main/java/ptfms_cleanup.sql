-- Check if the database exists and switch to it
DROP DATABASE IF EXISTS ptfms;

-- If you only want to clean tables without dropping the database, use this section instead:
-- Drop tables with foreign keys first (child tables)
DROP TABLE IF EXISTS GPSData;
DROP TABLE IF EXISTS FuelConsumption;
DROP TABLE IF EXISTS MaintenanceRecords;

-- Then drop the parent table
DROP TABLE IF EXISTS Vehicles;

-- Finally drop the Users table (no dependencies)
DROP TABLE IF EXISTS Users;