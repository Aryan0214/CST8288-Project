package business;
/**
 * Factory for creating Vehicle instances.
 * @author Aryan
 */
public class VehicleFactory {
    /**
     * Creates a new Vehicle with the given details.
     * @param type vehicle type
     * @param number vehicle number
     * @return a new Vehicle object
     */
    public static Vehicle createVehicle(String type, String number, String fuelType, double rate,int maxPassengers, String currentRoute) {
        return new VehicleBuilder()
                .setType(type)
                .setNumber(number)
                .setFuelType(fuelType)
                .setConsumptionRate(rate)
                .setMaxPassengers(maxPassengers)
                .setCurrentRoute(currentRoute)
                .build();
    }
}