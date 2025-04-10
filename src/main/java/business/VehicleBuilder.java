package business;

/**
 * Builder for constructing Vehicle objects.
 * @author Salman
 */
public class VehicleBuilder {
    private String type;
    private String number;
    private String fuelType;
    private double consumptionRate;
    private int maxPassengers;
    private String currentRoute;

    /**
     * Sets the vehicle type,Number,Fuel Type,Consumption Rate,Max Passengers,Current Route
     * @return this builder
     */
    public VehicleBuilder setType(String type) { this.type = type; return this; }
    public VehicleBuilder setNumber(String number) { this.number = number; return this; }
    public VehicleBuilder setFuelType(String fuelType) { this.fuelType = fuelType; return this; }
    public VehicleBuilder setConsumptionRate(double rate) { this.consumptionRate = rate; return this; }
    public VehicleBuilder setMaxPassengers (int maxPassengers){this.maxPassengers = maxPassengers; return this; };
    public VehicleBuilder setCurrentRoute (String currentRoute){this.currentRoute = currentRoute; return this; };
    
    
    /**
     * Builds the Vehicle instance.
     * @return a new Vehicle object
     */
    public Vehicle build() {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setNumber(number);
        vehicle.setFuelType(fuelType);
        vehicle.setConsumptionRate(consumptionRate);
        vehicle.setMaxPassengers(maxPassengers);
        vehicle.setCurrentRoute(currentRoute);
        return vehicle;
    }
}