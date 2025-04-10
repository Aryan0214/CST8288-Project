package data;

import business.GPSData;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object for GPS data operations.
 * @author Aryan
 */
public class GPSDataDAO {
    private final DBConnection dbConnection;

    public GPSDataDAO() throws IOException, ClassNotFoundException, SQLException {
        dbConnection = DBConnection.getInstance();
    }

    /**
     * Adds GPS data to the database.
     * @param data the GPS data to add
     * @throws SQLException if a database error occurs
     */
    public void addGPSData(GPSData data) throws SQLException {
        String sql = "INSERT INTO GPSData (vehicle_id, timestamp, location, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, data.getVehicleId());
            stmt.setTimestamp(2, new Timestamp(data.getTimestamp().getTime()));
            stmt.setString(3, data.getLocation());
            stmt.setString(4, data.getStatus());
            stmt.executeUpdate();
        }
    }

    /**
     * Gets all GPS data from the database.
     * @return a list of GPSData objects
     * @throws SQLException if a database error occurs
     */
    public List<GPSData> getGPSDataByVehicle(int vehicleId) throws SQLException {
        List<GPSData> dataList = new ArrayList<>();
        String sql = "SELECT * FROM GPSData WHERE vehicle_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GPSData data = new GPSData();
                data.setVehicleId(rs.getInt("vehicle_id"));
                data.setTimestamp(rs.getTimestamp("timestamp"));
                data.setLocation(rs.getString("location"));
                data.setStatus(rs.getString("status"));
                dataList.add(data);
            }
        }
        return dataList;
    }
    
    public List<GPSData> getAllGPSData() throws SQLException {
        List<GPSData> dataList = new ArrayList<>();
        String sql = "SELECT g.*, v.number FROM GPSData g JOIN Vehicles v ON g.vehicle_id = v.id ORDER BY g.timestamp";
        try (Connection conn = dbConnection.getConnection();
           PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GPSData data = new GPSData();
                data.setVehicleId(rs.getInt("vehicle_id"));
                data.setTimestamp(rs.getTimestamp("timestamp"));
                data.setLocation(rs.getString("location"));
                data.setStatus(rs.getString("status"));
                dataList.add(data);
            }
        }
        return dataList;
     }
}