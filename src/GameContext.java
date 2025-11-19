import org.json.JSONObject;

public class GameContext {

    private final JSONObject story;
    private final GameStateFactory stateFactory;

    private String currentBlock;
    private GameState currentState;

    public GameContext(JSONObject story, String startBlock, GameStateFactory stateFactory) {
        this.story = story;
        this.stateFactory = stateFactory;
        this.currentBlock = startBlock;
        this.currentState = createState(startBlock);
    }

    public void setStateFromBlock(String blockName) {
        if (story.has(blockName)) {
            this.currentBlock = blockName;
            this.currentState = createState(blockName);
        }
    }

    private GameState createState(String blockName) {
        JSONObject blockJson = story.getJSONObject(blockName);
        return stateFactory.createState(blockJson);
    }

    public String handleCommand(String input) {
        return currentState.handleCommand(input, this);
    }

    public String getCurrentBlock() {
        return currentBlock;
    }

    public String getCurrentDescription() {
        JSONObject blockJson = story.getJSONObject(currentBlock);
        return blockJson.getString("description");
    }
}
