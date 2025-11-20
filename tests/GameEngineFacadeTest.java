import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameEngineFacadeTest {

    private GameEngineFacade facade;

    @BeforeEach
    void setup() {
        facade = new GameEngineFacade();
    }

    @Test
    void testOpeningDescriptionNotNull() {
        String desc = facade.getOpeningDescription();
        assertNotNull(desc);
        assertFalse(desc.trim().isEmpty());
    }

    @Test
    void testCurrentBlockStartsAtStart() {
        assertEquals("start", facade.getCurrentBlock());
    }

    @Test
    void testHandleCommandUnknown() {
        String response = facade.handleCommand("asdjkahsd");
        assertTrue(response.toLowerCase().contains("unknown"));
    }

    @Test
    void testHandleCommandValid() {
        // "look" exists in your JSON story
        String response = facade.handleCommand("look");

        assertNotNull(response);
        assertFalse(response.trim().isEmpty());
    }
}
