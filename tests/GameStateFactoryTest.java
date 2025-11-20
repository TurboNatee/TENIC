import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateFactoryTest {

    @Test
    void testCreateStateReturnsJsonState() {
        // Arrange: minimal valid block JSON
        JSONObject json = new JSONObject()
                .put("description", "Test state")
                .put("commands", new JSONObject());

        GameStateFactory factory = new GameStateFactory();

        // Act
        GameState state = factory.createState(json);

        // Assert
        assertNotNull(state, "Factory must not return null");
        assertTrue(state instanceof JsonState, "Factory must return a JsonState");
    }

    @Test
    void testCreateStateUsesProvidedJson() {
        JSONObject json = new JSONObject()
                .put("description", "Desc X")
                .put("commands", new JSONObject());

        GameStateFactory factory = new GameStateFactory();
        JsonState state = (JsonState) factory.createState(json);

        // This indirectly checks that the JSON was passed correctly
        String result = state.handleCommand("invalid", null);

        assertEquals("Unknown command. Type 'help'.", result);
    }
}
