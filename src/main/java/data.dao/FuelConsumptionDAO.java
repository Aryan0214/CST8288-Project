package data;

import business.FuelConsumption;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object for fuel consumption operations.
 * @author Aryan
 */
public class FuelConsumptionDAO {
    private final DBConnection dbConnection;

    public FuelConsumptionDAO() throws IOException, ClassNotFoundException, SQLException {
        dbConnection = DBConnection.getInstance();
    }

    /**
     * Adds fuel consumption data to the database.
     * @param data
     * @throws SQLException if a database error occurs
     */
    public void addFuelConsumption(FuelConsumption data) throws SQLException {
        String sql = "INSERT INTO FuelConsumption (vehicle_id, date, consumption) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, data.getVehicleId());
            stmt.setDate(2, new java.sql.Date(data.getDate().getTime()));
            stmt.setDouble(3, data.getConsumption());
            stmt.executeUpdate();
        }
    }

    public List<FuelConsumption> getFuelConsumptionByVehicle(int vehicleId) throws SQLException {
        List<FuelConsumption> dataList = new ArrayList<>();
        String sql = "SELECT * FROM FuelConsumption WHERE vehicle_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FuelConsumption data = new FuelConsumption();
                data.setVehicleId(rs.getInt("vehicle_id"));
                data.setDate(rs.getDate("date"));
                data.setConsumption(rs.getDouble("consumption"));
                dataList.add(data);
            }
        }
        return dataList;
    }
}