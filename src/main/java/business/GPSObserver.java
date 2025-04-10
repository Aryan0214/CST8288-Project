package business;
/**
 * Observer interface for GPS updates.
 * @author Aryan
 */
public interface GPSObserver {
    /**
     * Updates the observer with new location data.
     * @param location the new location
     */
    void update(String location);
}