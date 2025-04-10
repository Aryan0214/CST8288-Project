package business;

import java.util.ArrayList;
import java.util.List;
/**
 * Subject for GPS data updates.
 * @author Anjalika
 */
public class GPSSubject {
    private List<GPSObserver> observers = new ArrayList<>();
    private String location;

    /**
     * Adds an observer.
     * @param observer the observer to add
     */
    public void addObserver(GPSObserver observer) { observers.add(observer); }
    public void setLocation(String location) {
        this.location = location;
        notifyObservers();
    }
    private void notifyObservers() {
        for (GPSObserver observer : observers) {
            observer.update(location);
        }
    }
}