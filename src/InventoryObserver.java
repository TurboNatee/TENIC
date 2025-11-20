import java.util.List;

public interface InventoryObserver {
    void onInventoryUpdated(List<String> updatedInventory);
}
