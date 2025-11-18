import org.json.JSONObject;

public class gameContext {

    private final JSONObject story;
    private String currentBlock;
    private gameState currentState;

    public gameContext(JSONObject story, String startBlock) {
        this.story = story;
        this.currentBlock = startBlock;
        this.currentState = createState(startBlock);
    }

    public void setStateFromBlock(String blockName) {
        if (story.has(blockName)) {
            this.currentBlock = blockName;
            this.currentState = createState(blockName);
        }
    }

    public String getDescriptionFor(String blockName) {
        return story.getJSONObject(blockName).getString("description");
    }


    private gameState createState(String blockName) {
        JSONObject blockJson = story.getJSONObject(blockName);
        return new jsonState(blockJson);
    }

    public String handleCommand(String input) {
        return currentState.handleCommand(input, this);
    }

    public String getCurrentBlock() {
        return currentBlock;
    }

    public String getCurrentDescription() {
        return currentState instanceof jsonState
                ? ((jsonState) currentState).getDescription()
                : "";
    }
}
