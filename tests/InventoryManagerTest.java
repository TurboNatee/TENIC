import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {

    private InventoryManager manager;
    private TestObserver observer;

    // simple manual observer to capture updates
    private static class TestObserver implements InventoryObserver {
        List<List<String>> notifications = new ArrayList<>();

        @Override
        public void onInventoryUpdated(List<String> updatedInventory) {
            notifications.add(new ArrayList<>(updatedInventory));
        }
    }

    @BeforeEach
    void setup() {
        manager = new InventoryManager();
        observer = new TestObserver();
    }

    @Test
    void testAddItemValid() {
        manager.addObserver(observer);

        manager.addItem("Sword");
        manager.addItem("Shield");

        List<String> inventory = manager.getInventory();

        assertEquals(2, inventory.size());
        assertEquals("Sword", inventory.get(0));
        assertEquals("Shield", inventory.get(1));

        // observer should have been notified twice
        assertEquals(3, observer.notifications.size());
        // first notification is empty initial state
    }

    @Test
    void testAddItemInvalid() {
        manager.addObserver(observer);

        manager.addItem(null);
        manager.addItem("");
        manager.addItem("   ");

        assertTrue(manager.getInventory().isEmpty());
        assertEquals(1, observer.notifications.size());
        // only initial call
    }

    @Test
    void testRemoveItem() {
        manager.addObserver(observer);

        manager.addItem("Axe");
        manager.addItem("Potion");

        manager.removeItem("Axe");

        List<String> inventory = manager.getInventory();
        assertEquals(1, inventory.size());
        assertEquals("Potion", inventory.get(0));

        // notifications:
        // 1 = initial
        // 2 = add Axe
        // 3 = add Potion
        // 4 = remove Axe
        assertEquals(4, observer.notifications.size());
    }

    @Test
    void testRemoveNonExistentItem() {
        manager.addObserver(observer);

        manager.addItem("Bow");
        manager.removeItem("NotInInventory");

        // should NOT notify again when removal fails
        assertEquals(2, observer.notifications.size());
        assertEquals(1, manager.getInventory().size());
    }

    @Test
    void testClearInventory() {
        manager.addObserver(observer);

        manager.addItem("Food");
        manager.addItem("Water");

        manager.clear();

        assertTrue(manager.getInventory().isEmpty());

        // initial + food + water + clear
        assertEquals(4, observer.notifications.size());
    }

    @Test
    void testAddObserverPushesCurrentState() {
        manager.addItem("Map");

        manager.addObserver(observer);

        // immediately receives current inventory
        assertEquals(1, observer.notifications.size());
        assertEquals("Map", observer.notifications.get(0).get(0));
    }

    @Test
    void testRemoveObserver() {
        manager.addObserver(observer);

        manager.removeObserver(observer);

        manager.addItem("Torch");

        // only initial update before removal
        assertEquals(1, observer.notifications.size());
    }

    @Test
    void testGetInventoryIsImmutable() {
        manager.addItem("Key");

        List<String> inventory = manager.getInventory();

        assertThrows(UnsupportedOperationException.class, () -> {
            inventory.add("HACK");
        });
    }
}
