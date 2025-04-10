package business;
/**
 * Represents a vehicle in the transit system.
 * @author Anjalika
 */
public class Vehicle {
    private int id;
    private String type;
    private String number;
    private String fuelType;
    private double consumptionRate;
    private int maxPassengers;
    private String currentRoute;

    /**
     * Gets the vehicle ID.
     * @return the vehicle ID
     */
    public int getId() { return id; }
    /**
     * Sets the vehicle ID.
     * @param id the vehicle ID
     */
    public void setId(int id) { this.id = id; }
    /**
     * Gets the type.
     * @return the type
     */
    public String getType() { return type; }
    /**
     * Sets the type.
     * @param type the type
     */
    public void setType(String type) { this.type = type; }
    /**
     * Gets the number
     * @return the number
     */
    public String getNumber() { return number; }
    /**
     * Sets the number.
     * @param number the number
     */
    public void setNumber(String number) { this.number = number; }
    /**
     * Gets the fuelType.
     * @return the fuelType
     */
    public String getFuelType() { return fuelType; }
    /**
     * Sets the fuelType
     * @param fuelType the fuelType
     */
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    
    /**
     * Gets the consumptionRate
     * @return the consumptionRate
     */
    public double getConsumptionRate() { return consumptionRate; }
    
    /**
     * Sets the consumptionRate
     * @param consumptionRate the consumption Rate
     */
    public void setConsumptionRate(double consumptionRate) { this.consumptionRate = consumptionRate; }
    
    /**
     * Gets the maxPassengers.
     * @return the maxPassengers
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }
    
    /**
     * Sets the maxPassengers
     * @param maxPassengers the maxPassengers
     */
    public void setMaxPassengers(int maxPassengers){
        this.maxPassengers = maxPassengers;
    }
    
    /**
     * Gets the Current Route.
     * @return the Current Route
     */
    public String getCurrentRoute() {
        return currentRoute;
    }
    
    /**
     * Sets the Current Route.
     * @param currentRoute the Current Route
     */
    public void setCurrentRoute(String currentRoute){
        this.currentRoute = currentRoute;
    }
}