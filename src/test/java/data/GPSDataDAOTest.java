// src/test/java/data/GPSDataDAOTest.java
package data;

import business.GPSData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit tests for GPSDataDAO SQL operations.
 * @author Aryan
 */
public class GPSDataDAOTest {

    @Mock
    private DBConnection dbConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    private GPSDataDAO gpsDataDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        gpsDataDAO = new GPSDataDAO() {
            {
                try {
                    java.lang.reflect.Field field = GPSDataDAO.class.getDeclaredField("dbConnection");
                    field.setAccessible(true);
                    field.set(this, dbConnection);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to inject mock DBConnection", e);
                }
            }
        };
        when(dbConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);
    }

    /**
     * Tests adding GPS data to the database.
     * @throws SQLException if a database error occurs
     */
    @Test
    public void testAddGPSData() throws SQLException {
        GPSData data = new GPSData();
        data.setVehicleId(1);
        data.setTimestamp(new Date());
        data.setLocation("Station C");
        data.setStatus("Break");

        when(stmt.executeUpdate()).thenReturn(1);

        gpsDataDAO.addGPSData(data);

        verify(connection).prepareStatement(eq("INSERT INTO GPSData (vehicle_id, timestamp, location, status) VALUES (?, ?, ?, ?)"));
        verify(stmt).setInt(eq(1), eq(1));
        verify(stmt).setTimestamp(eq(2), any(java.sql.Timestamp.class));
        verify(stmt).setString(eq(3), eq("Station C"));
        verify(stmt).setString(eq(4), eq("Break"));
        verify(stmt).executeUpdate();
    }

    /**
     * Tests retrieving all GPS data from the database.
     * @throws SQLException if a database error occurs
     */
    @Test
    public void testGetAllGPSData() throws SQLException {
        when(connection.prepareStatement("SELECT * FROM GPSData ORDER BY timestamp")).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("vehicle_id")).thenReturn(1);
        when(rs.getTimestamp("timestamp")).thenReturn(new java.sql.Timestamp(System.currentTimeMillis()));
        when(rs.getString("location")).thenReturn("Station C");
        when(rs.getString("status")).thenReturn("Break");

        List<GPSData> dataList = gpsDataDAO.getAllGPSData();

        assertNotNull(dataList);
        assertEquals(1, dataList.size());
        assertEquals("Station C", dataList.get(0).getLocation());
    }

    /**
     * Tests retrieving an empty list when no GPS data exists.
     * @throws SQLException if a database error occurs
     */
    @Test
    public void testGetAllGPSDataEmpty() throws SQLException {
        when(connection.prepareStatement("SELECT * FROM GPSData ORDER BY timestamp")).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        List<GPSData> dataList = gpsDataDAO.getAllGPSData();

        assertNotNull(dataList);
        assertTrue(dataList.isEmpty());
    }
}