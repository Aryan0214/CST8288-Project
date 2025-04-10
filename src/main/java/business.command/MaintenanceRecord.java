package business;

import java.util.Date;
/**
 * Represents a maintenance record for a vehicle in the transit system.
 * @author anjad
 */
public class MaintenanceRecord {
    private int vehicleId;
    private String component;
    private double wearLevel;
    private Date lastMaintenance;

    /**
     * Gets the vehicle ID associated with this maintenance record.
     * @return the vehicle ID
     */
    public int getVehicleId() { return vehicleId; }
    /**
     * Sets the vehicle ID for this maintenance record.
     * @param vehicleId the vehicle ID
     */
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    /**
     * Gets the component ID associated with this component
     * @return the vehicle ID
     */
    public String getComponent() { return component; }
    /**
     * Sets the component ID for this component
     * @param component
     */
    public void setComponent(String component) { this.component = component; }
    /**
     * Gets the wear level.
     * @return the vehicle ID
     */
    public double getWearLevel() { return wearLevel; }
    /**
     * Sets the wear level.
     * @param wearLevel
     */
    public void setWearLevel(double wearLevel) { this.wearLevel = wearLevel; }
    /**
     * Gets the last maintenance record.
     * @return the vehicle ID
     */
    public Date getLastMaintenance() { return lastMaintenance; }
    /**
     * Sets the last maintenance record.
     * @param lastMaintenance 
     */
    public void setLastMaintenance(Date lastMaintenance) { this.lastMaintenance = lastMaintenance; }
}