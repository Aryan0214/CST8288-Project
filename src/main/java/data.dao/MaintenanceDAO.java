package data;

import business.MaintenanceRecord;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object for maintenance record operations.
 * @author Anjalika
 */
public class MaintenanceDAO {
    private final DBConnection dbConnection;

    public MaintenanceDAO() throws IOException, ClassNotFoundException, SQLException {
        dbConnection = DBConnection.getInstance();
    }

    /**
     * Adds a maintenance record to the database.
     * @param record the maintenance record to add
     * @throws SQLException if a database error occurs
     */
    public void addMaintenanceRecord(MaintenanceRecord record) throws SQLException {
        String sql = "INSERT INTO MaintenanceRecords (vehicle_id, component, wear_level, last_maintenance) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getVehicleId());
            stmt.setString(2, record.getComponent());
            stmt.setDouble(3, record.getWearLevel());
            stmt.setDate(4, record.getLastMaintenance() != null ? new java.sql.Date(record.getLastMaintenance().getTime()) : null);
            stmt.executeUpdate();
        }
    }

    /**
     * Gets all maintenance records for a vehicle.
     * @param vehicleId the vehicle ID
     * @return a list of MaintenanceRecord objects
     * @throws SQLException if a database error occurs
     */
    public List<MaintenanceRecord> getMaintenanceRecordsByVehicle(int vehicleId) throws SQLException {
        List<MaintenanceRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM MaintenanceRecords WHERE vehicle_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MaintenanceRecord record = new MaintenanceRecord();
                record.setVehicleId(rs.getInt("vehicle_id"));
                record.setComponent(rs.getString("component"));
                record.setWearLevel(rs.getDouble("wear_level"));
                record.setLastMaintenance(rs.getDate("last_maintenance"));
                records.add(record);
            }
        }
        return records;
    }
}