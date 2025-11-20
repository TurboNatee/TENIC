import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryManager {

    private final List<String> inventory = new ArrayList<>();
    private final List<InventoryObserver> observers = new ArrayList<>();

    public void addItem(String name) {
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        inventory.add(name);
        notifyObservers();
    }

    public void removeItem(String name) {
        if (inventory.remove(name)) {
            notifyObservers();
        }
    }

    public List<String> getInventory() {
        // prevent external modification
        return Collections.unmodifiableList(inventory);
    }

    public void clear() {
        inventory.clear();
        notifyObservers();
    }

    public void addObserver(InventoryObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            // push current state immediately
            observer.onInventoryUpdated(getInventory());
        }
    }

    public void removeObserver(InventoryObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        List<String> snapshot = getInventory();
        for (InventoryObserver obs : observers) {
            obs.onInventoryUpdated(snapshot);
        }
    }

}
