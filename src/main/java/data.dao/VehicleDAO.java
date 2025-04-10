package data;

import business.Vehicle;
import java.io.IOException;
import java.sql.*;
import java.util.random;

/**
 * Data Access Object for vehicle operations.
 * @author Salman Farshe
 */
public class VehicleDAO {
    private final DBConnection dbConnection;

    public VehicleDAO() throws IOException, ClassNotFoundException, SQLException {
        dbConnection = DBConnection.getInstance();
    }

    /**
     * Adds a vehicle to the database.
     * @param vehicle the vehicle to add
     * @throws SQLException if a database error occurs
     */
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicles (type, number, fuel_type, consumption_rate, max_passengers, current_route) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getType());
            stmt.setString(2, vehicle.getNumber());
            stmt.setString(3, vehicle.getFuelType());
            stmt.setDouble(4, vehicle.getConsumptionRate());
            stmt.setInt(5, vehicle.getMaxPassengers());
            stmt.setString(6, vehicle.getCurrentRoute());
            stmt.executeUpdate();
        }
    }

    /**
     * Gets a vehicle by its number.
     * @param number the vehicle number
     * @return the Vehicle object, or null if not found
     * @throws SQLException if a database error occurs
     */
    public Vehicle getVehicleByNumber(String number) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE number = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setType(rs.getString("type"));
                vehicle.setNumber(rs.getString("number"));
                vehicle.setFuelType(rs.getString("fuel_type"));
                vehicle.setConsumptionRate(rs.getDouble("consumption_rate"));
                vehicle.setMaxPassengers(rs.getInt("max_passengers"));
                vehicle.setCurrentRoute(rs.getString("current_route"));
                return vehicle;
            }
            return null;
        }
    }
}