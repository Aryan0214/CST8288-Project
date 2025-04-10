package business;

import data.VehicleDAO;
import data.GPSDataDAO;
import data.FuelConsumptionDAO;
import data.MaintenanceDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
/**
 * Service class for vehicle management and GPS tracking.
 * @author Anjalika
 */
public class VehicleService {
    private final VehicleDAO vehicleDAO;
    private final GPSDataDAO gpsDataDAO;
    private final FuelConsumptionDAO fuelDAO;
    private final MaintenanceDAO maintenanceDAO;
    private final GPSSubject gpsSubject;

    public VehicleService() throws IOException, ClassNotFoundException, SQLException {
        this.vehicleDAO = new VehicleDAO();
        this.gpsDataDAO = new GPSDataDAO();
        this.fuelDAO = new FuelConsumptionDAO();
        this.maintenanceDAO = new MaintenanceDAO();
        this.gpsSubject = new GPSSubject();
        startGPSSimulation();
    }

    /**
     * Adds a vehicle to the system.
     * @param type vehicle type
     * @param number vehicle number
     * @param fuelType fuel type
     * @param rate consumption rate
     * @param maxPassengers max passengers
     * @param currentRoute current route
     * @throws SQLException if a database error occurs
     */
    public void addVehicle(String type, String number, String fuelType, double rate,int maxPassengers, String currentRoute) throws SQLException {
        Vehicle vehicle = VehicleFactory.createVehicle(type, number, fuelType, rate, maxPassengers, currentRoute);
        vehicleDAO.addVehicle(vehicle);
    }

    public double calculateFuelConsumption(Vehicle vehicle, double distance) {
        FuelStrategy strategy;
        if ("Diesel".equals(vehicle.getFuelType())) {
            strategy = new DieselFuelStrategy(vehicle.getConsumptionRate());
        } else {
            strategy = new ElectricFuelStrategy(vehicle.getConsumptionRate());
        }
        return strategy.calculateConsumption(distance);
    }

    /**
     * Logs GPS data for a vehicle.
     * @param vehicleId the vehicle ID
     * @param location the location
     * @param status the status
     * @throws SQLException if a database error occurs
     */
    public void logGPSData(int vehicleId, String location, String status) throws SQLException {
        GPSData data = new GPSData();
        data.setVehicleId(vehicleId);
        data.setTimestamp(new Date());
        data.setLocation(location);
        data.setStatus(status);
        gpsDataDAO.addGPSData(data);
        gpsSubject.setLocation(location); // Notify observers
    }

    public List<GPSData> getGPSReport(int vehicleId) throws SQLException {
        return gpsDataDAO.getGPSDataByVehicle(vehicleId);
    }

    public void logFuelConsumption(int vehicleId, double consumption) throws SQLException {
        FuelConsumption data = new FuelConsumption();
        data.setVehicleId(vehicleId);
        data.setDate(new Date());
        data.setConsumption(consumption);
        fuelDAO.addFuelConsumption(data);
    }

    public List<FuelConsumption> getFuelReport(int vehicleId) throws SQLException {
        return fuelDAO.getFuelConsumptionByVehicle(vehicleId);
    }

    public void scheduleMaintenance(String vehicleNumber) {
        MaintenanceCommand command = new ScheduleMaintenance(vehicleNumber);
        command.execute();
    }

    public void logMaintenance(int vehicleId, String component, double wearLevel) throws SQLException {
        MaintenanceRecord record = new MaintenanceRecord();
        record.setVehicleId(vehicleId);
        record.setComponent(component);
        record.setWearLevel(wearLevel);
        record.setLastMaintenance(new Date());
        maintenanceDAO.addMaintenanceRecord(record);
        if (wearLevel > 0.75) {
            scheduleMaintenance(getVehicleByNumber(vehicleId).getNumber());
        }
    }

    public List<MaintenanceRecord> getMaintenanceReport(int vehicleId) throws SQLException {
        return maintenanceDAO.getMaintenanceRecordsByVehicle(vehicleId);
    }

    public void addGPSObserver(GPSObserver observer) {
        gpsSubject.addObserver(observer);
    }

    private Vehicle getVehicleByNumber(int vehicleId) throws SQLException {
        // Simplified logic; in practice, fetch by ID if needed
        return vehicleDAO.getVehicleByNumber("BUS00" + vehicleId);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<GPSData> getAllGPSData() throws SQLException {
        return gpsDataDAO.getAllGPSData();
    }
    
    private void startGPSSimulation() {
        new Thread(() -> {
            try{
                while(true){
                    logGPSData(1,"Station A", "Active");
                    Thread.sleep(5000);
                }
            } catch (SQLException | InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}