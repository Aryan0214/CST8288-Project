package business;
/**
 * Diesel fuel consumption strategy.
 * @author Anjalika
 */
public class DieselFuelStrategy implements FuelStrategy {
    private double rate;

    public DieselFuelStrategy(double rate) { this.rate = rate; }

    @Override
    public double calculateConsumption(double distance) {
        return distance * rate;
    }
}
