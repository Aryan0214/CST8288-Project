package business;

import java.util.Date;

public class FuelConsumption {
    private int vehicleId;
    private Date date;
    private double consumption;

    // Getters and setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public double getConsumption() { return consumption; }
    public void setConsumption(double consumption) { this.consumption = consumption; }
}