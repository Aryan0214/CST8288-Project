package business;

import java.util.Date;
/**
 * Represents GPS data for a vehicle.
 * @author Salman
 */

public class GPSData {
    private int vehicleId;
    private Date timestamp;
    private String location;
    private String status;

    // Getters and setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}