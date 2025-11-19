import org.json.JSONObject;

public class GameStateFactory {

    public GameState createState(JSONObject blockJson) {
        return new JsonState(blockJson);
    }
}
