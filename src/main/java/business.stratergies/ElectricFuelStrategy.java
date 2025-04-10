package business;
/**
 * Electric fuel consumption strategy.
 * @author Salman
 */
public class ElectricFuelStrategy implements FuelStrategy {
    private double rate;

    public ElectricFuelStrategy(double rate) { this.rate = rate; }

    @Override
    public double calculateConsumption(double distance) {
        return distance * rate * 0.8; // Adjusted for efficiency
    }
}