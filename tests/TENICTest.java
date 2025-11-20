import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TENICTest {

    @BeforeEach
    void setup() {
        // Enable test mode to disable JOptionPane dialogs
        TENIC.testMode = true;

        // Reset static values so each test is fresh
        TENIC.playerName = "";
        TENIC.playerJob = "";
        TENIC.playerAge = 0;
    }

    @Test
    void validatePlayer_shouldFailWhenNameEmpty() {
        boolean valid = TENIC.validatePlayer("", "Job", "25");
        assertFalse(valid);
    }

    @Test
    void validatePlayer_shouldFailWhenNameNull() {
        boolean valid = TENIC.validatePlayer(null, "Job", "25");
        assertFalse(valid);
    }

    @Test
    void validatePlayer_shouldFailWhenJobEmpty() {
        boolean valid = TENIC.validatePlayer("Nathan", "", "25");
        assertFalse(valid);
    }

    @Test
    void validatePlayer_shouldFailWhenJobNull() {
        boolean valid = TENIC.validatePlayer("Nathan", null, "25");
        assertFalse(valid);
    }

    @Test
    void validatePlayer_shouldFailWhenAgeNotANumber() {
        boolean valid = TENIC.validatePlayer("Nathan", "Developer", "abc");
        assertFalse(valid);
    }

    @Test
    void validatePlayer_shouldSetAgeWhenValidNumber() {
        TENIC.validatePlayer("Nathan", "Dev", "30");
        assertEquals(30, TENIC.playerAge);
    }

    @Test
    void validatePlayer_shouldFailWhenMultipleFieldsInvalid() {
        boolean valid = TENIC.validatePlayer("", "", "notANumber");
        assertFalse(valid);
    }

    @Test
    void validatePlayer_shouldReturnTrueForValidInputs() {
        boolean valid = TENIC.validatePlayer("Nathan", "Developer", "25");
        assertTrue(valid);
        assertEquals(25, TENIC.playerAge);
    }

    @Test
    void validatePlayer_shouldTrimWhitespaceInputs() {
        boolean valid = TENIC.validatePlayer("  Nathan  ", "  Dev  ", "  22 ");
        assertTrue(valid);
    }
}
