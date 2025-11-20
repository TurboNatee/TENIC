import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private JSONObject fakeStory;

    @BeforeEach
    void setup() {
        fakeStory = new JSONObject()
                .put("start", new JSONObject()
                        .put("description", "This is the start block.")
                        .put("commands", new JSONObject()
                                .put("look", new JSONObject()
                                        .put("response", "You look around.")
                                        .put("next", "start")
                                )
                        )
                );
    }

    private GameController createController() {
        return new GameController(fakeStory);
    }

    @Test
    void testOpeningDescription() {
        GameController controller = createController();
        assertEquals("This is the start block.", controller.getOpeningDescription());
    }

    @Test
    void testProcessKnownCommand() {
        GameController controller = createController();
        assertEquals("You look around.", controller.processCommand("look"));
    }

    @Test
    void testProcessUnknownCommand() {
        GameController controller = createController();
        assertEquals("Unknown command. Type 'help'.", controller.processCommand("invalid"));
    }

    @Test
    void testCurrentBlock() {
        GameController controller = createController();
        assertEquals("start", controller.getCurrentBlock());
    }
}
