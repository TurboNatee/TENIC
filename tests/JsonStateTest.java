import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonStateTest {

    private GameContext context;

    @BeforeEach
    void setup() {
        // Minimal JSON story with two blocks for transitions
        JSONObject story = new JSONObject()
                .put("start", new JSONObject()
                        .put("description", "Start block")
                        .put("commands", new JSONObject()))
                .put("nextBlock", new JSONObject()
                        .put("description", "Next block")
                        .put("commands", new JSONObject()));

        GameStateFactory factory = new GameStateFactory();
        context = new GameContext(story, "start", factory);

        // Clear inventory before each test
        TENIC.inventoryManager.clear();
    }

    @Test
    void handleCommand_returnsUnknownForMissingCommand() {
        JSONObject json = new JSONObject()
                .put("description", "desc")
                .put("commands", new JSONObject()); // empty commands

        JsonState state = new JsonState(json);

        String result = state.handleCommand("invalid", context);
        assertEquals("Unknown command. Type 'help'.", result);
    }

    @Test
    void handleCommand_addsSingleItem() {
        JSONObject cmd = new JSONObject()
                .put("response", "You pick up the key.")
                .put("addItem", "key")
                .put("next", "start");

        JSONObject json = new JSONObject()
                .put("description", "desc")
                .put("commands", new JSONObject().put("take", cmd));

        JsonState state = new JsonState(json);

        String result = state.handleCommand("take", context);

        assertTrue(TENIC.inventoryManager.getInventory().contains("key"));
        assertEquals("You pick up the key.", result);
    }

    @Test
    void handleCommand_addsMultipleItems() {
        JSONArray items = new JSONArray().put("knife").put("rope");

        JSONObject cmd = new JSONObject()
                .put("response", "You gather supplies.")
                .put("addItem", items)
                .put("next", "start");

        JSONObject json = new JSONObject()
                .put("description", "desc")
                .put("commands", new JSONObject().put("gather", cmd));

        JsonState state = new JsonState(json);

        state.handleCommand("gather", context);

        assertTrue(TENIC.inventoryManager.getInventory().contains("knife"));
        assertTrue(TENIC.inventoryManager.getInventory().contains("rope"));
    }

    @Test
    void handleCommand_removesSingleItem() {
        TENIC.inventoryManager.addItem("key");

        JSONObject cmd = new JSONObject()
                .put("response", "You drop the key.")
                .put("removeItem", "key")
                .put("next", "start");

        JSONObject json = new JSONObject()
                .put("description", "desc")
                .put("commands", new JSONObject().put("drop", cmd));

        JsonState state = new JsonState(json);

        state.handleCommand("drop", context);

        assertFalse(TENIC.inventoryManager.getInventory().contains("key"));
    }

    @Test
    void handleCommand_removesMultipleItems() {
        TENIC.inventoryManager.addItem("knife");
        TENIC.inventoryManager.addItem("rope");

        JSONArray items = new JSONArray().put("knife").put("rope");

        JSONObject cmd = new JSONObject()
                .put("response", "You discard items.")
                .put("removeItem", items)
                .put("next", "start");

        JSONObject json = new JSONObject()
                .put("description", "desc")
                .put("commands", new JSONObject().put("discard", cmd));

        JsonState state = new JsonState(json);

        state.handleCommand("discard", context);

        assertEquals(0, TENIC.inventoryManager.getInventory().size());
    }

    @Test
    void handleCommand_changesStateAndReturnsUpdatedDescription() {
        JSONObject cmd = new JSONObject()
                .put("response", "Moving on...")
                .put("next", "nextBlock"); // different block

        JSONObject json = new JSONObject()
                .put("description", "desc")
                .put("commands", new JSONObject().put("move", cmd));

        JsonState state = new JsonState(json);

        String output = state.handleCommand("move", context);

        assertTrue(output.contains("Moving on..."));
        assertTrue(output.contains("Next block"));
        assertEquals("nextBlock", context.getCurrentBlock());
    }

    @Test
    void handleCommand_sameStateDoesNotAppendDescription() {
        JSONObject cmd = new JSONObject()
                .put("response", "You stay here.")
                .put("next", "start"); // same block

        JSONObject json = new JSONObject()
                .put("description", "desc")
                .put("commands", new JSONObject().put("stay", cmd));

        JsonState state = new JsonState(json);

        String output = state.handleCommand("stay", context);
    }
}
