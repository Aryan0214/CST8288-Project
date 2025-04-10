package business;
/**
 * Strategy interface for fuel consumption calculation.
 * @author Aryan
 */
public interface FuelStrategy {
    double calculateConsumption(double distance);
}