import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameContextTest {

    @Test
    void testInitialStateLoadsCorrectly() {
        JSONObject story = new JSONObject("""
        {
          "start": {
            "description": "Welcome!",
            "commands": {}
          }
        }
        """);

        GameStateFactory factory = new GameStateFactory();
        GameContext context = new GameContext(story, "start", factory);

        assertEquals("start", context.getCurrentBlock());
        assertEquals("Welcome!", context.getCurrentDescription());
    }

    @Test
    void testStateTransition() {
        JSONObject story = new JSONObject("""
        {
          "start": {
            "description": "Start block",
            "commands": {}
          },
          "next": {
            "description": "Next block",
            "commands": {}
          }
        }
        """);

        GameStateFactory factory = new GameStateFactory();
        GameContext context = new GameContext(story, "start", factory);

        context.setStateFromBlock("next");

        assertEquals("next", context.getCurrentBlock());
        assertEquals("Next block", context.getCurrentDescription());
    }

    @Test
    void testHandleCommandDelegatesToState() {
        JSONObject story = new JSONObject("""
        {
          "start": {
            "description": "desc",
            "commands": {
              "hello": {
                "response": "world",
                "next": "start"
              }
            }
          }
        }
        """);

        GameStateFactory factory = new GameStateFactory();
        GameContext context = new GameContext(story, "start", factory);

        String response = context.handleCommand("hello");
        assertTrue(response.contains("world"));
    }
}
