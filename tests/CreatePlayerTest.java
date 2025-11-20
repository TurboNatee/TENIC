import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePlayerTest {

    @Test
    void testBuilderCreatesPlayerCorrectly() {
        CreatePlayer player = new CreatePlayer.Builder()
                .withName("Nathan")
                .withProfession("Developer")
                .withAge(22)
                .build();

        assertEquals("Nathan", player.getName());
        assertEquals("Developer", player.getProfession());
        assertEquals(22, player.getAge());
    }

    @Test
    void testBuilderWithNullValues() {
        CreatePlayer player = new CreatePlayer.Builder()
                .withName(null)
                .withProfession(null)
                .withAge(0)
                .build();

        assertNull(player.getName());
        assertNull(player.getProfession());
        assertEquals(0, player.getAge());
    }

    @Test
    void testBuilderWithEmptyStrings() {
        CreatePlayer player = new CreatePlayer.Builder()
                .withName("")
                .withProfession("")
                .withAge(10)
                .build();

        assertEquals("", player.getName());
        assertEquals("", player.getProfession());
        assertEquals(10, player.getAge());
    }

    @Test
    void testToStringFormat() {
        CreatePlayer player = new CreatePlayer.Builder()
                .withName("John")
                .withProfession("Mage")
                .withAge(30)
                .build();

        String output = player.toString();

        assertTrue(output.contains("name='John'"));
        assertTrue(output.contains("profession='Mage'"));
        assertTrue(output.contains("age=30"));
    }

    @Test
    void testBuilderChaining() {
        CreatePlayer.Builder builder = new CreatePlayer.Builder();

        // chain methods
        builder.withName("Alice")
                .withProfession("Warrior")
                .withAge(19);

        CreatePlayer player = builder.build();

        assertEquals("Alice", player.getName());
        assertEquals("Warrior", player.getProfession());
        assertEquals(19, player.getAge());
    }
}
