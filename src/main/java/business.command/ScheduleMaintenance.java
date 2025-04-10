package business;
/**
 * Command to schedule maintenance.
 * @author Aryan
 */
public class ScheduleMaintenance implements MaintenanceCommand {
    private String vehicleNumber;

    public ScheduleMaintenance(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    @Override
    public void execute() {
        System.out.println("Scheduled maintenance for vehicle: " + vehicleNumber);
    }
}