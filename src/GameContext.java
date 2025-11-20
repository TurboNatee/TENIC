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

    private GameState createState(String blockName) {
        JSONObject blockJson = story.getJSONObject(blockName);
        return stateFactory.createState(blockJson);
    }

    public void setStateFromBlock(String blockName) {
        if (story.has(blockName)) {
            currentBlock = blockName;
            currentState = createState(blockName);
        }
    }

    public String handleCommand(String input) {
        return currentState.handleCommand(input, this);
    }

    public String getCurrentBlock() {
        return currentBlock;
    }

    public String getCurrentDescription() {
        return story.getJSONObject(currentBlock).getString("description");
    }

    public boolean isCurrentStateEnd() {
        return currentState instanceof JsonState && ((JsonState) currentState).isEndState();
    }
}
