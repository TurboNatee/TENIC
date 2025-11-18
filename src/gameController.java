import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class gameController {

    private final gameContext context;

    public gameController() {
        JSONObject story = loadStory("src/main-story.json");
        this.context = new gameContext(story, "start");

    }

    public String getOpeningDescription() {
        return context.getCurrentDescription();
    }


    private JSONObject loadStory(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            String text = new String(bytes, StandardCharsets.UTF_8);
            return new JSONObject(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load story JSON from " + path, e);
        }
    }

    public String processCommand(String input) {
        return context.handleCommand(input);
    }

    public String getCurrentBlock() {
        return context.getCurrentBlock();
    }
}

