// src/test/java/data/VehicleDAOTest.java
package data;

import business.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit tests for VehicleDAO.
 * @author Salman
 */
public class VehicleDAOTest {

    @Mock
    private DBConnection dbConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    private VehicleDAO vehicleDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        vehicleDAO = new VehicleDAO();
        java.lang.reflect.Field field = VehicleDAO.class.getDeclaredField("dbConnection");
        field.setAccessible(true);
        field.set(vehicleDAO, dbConnection);
        when(dbConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);
    }

    /**
     * Tests adding a vehicle.
     * @throws SQLException if a database error occurs
     */
    @Test
    public void testAddVehicle() throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setType("Bus");
        vehicle.setNumber("BUS004");
        vehicle.setFuelType("Diesel");
        vehicle.setConsumptionRate(0.5);
        vehicle.setMaxPassengers(50);
        vehicle.setCurrentRoute("Route 4");

        when(stmt.executeUpdate()).thenReturn(1);

        vehicleDAO.addVehicle(vehicle);

        verify(connection).prepareStatement("INSERT INTO Vehicles (type, number, fuel_type, consumption_rate, max_passengers, current_route) VALUES (?, ?, ?, ?, ?, ?)");
        verify(stmt).setString(1, "Bus");
        verify(stmt).setString(2, "BUS004");
        verify(stmt).setString(3, "Diesel");
        verify(stmt).setDouble(4, 0.5);
        verify(stmt).setInt(5, 50);
        verify(stmt).setString(6, "Route 4");
        verify(stmt).executeUpdate();
    }

    @Test
    public void testGetVehicleByNumber() throws SQLException {
        when(connection.prepareStatement("SELECT * FROM Vehicles WHERE number = ?")).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("type")).thenReturn("Bus");
        when(rs.getString("number")).thenReturn("BUS004");
        when(rs.getString("fuel_type")).thenReturn("Diesel");
        when(rs.getDouble("consumption_rate")).thenReturn(0.5);
        when(rs.getInt("max_passengers")).thenReturn(50);
        when(rs.getString("current_route")).thenReturn("Route 4");

        Vehicle vehicle = vehicleDAO.getVehicleByNumber("BUS004");

        assertNotNull(vehicle);
        assertEquals("Bus", vehicle.getType());
        assertEquals("BUS004", vehicle.getNumber());
    }
}