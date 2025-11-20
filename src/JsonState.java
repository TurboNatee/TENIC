import org.json.JSONArray;
import org.json.JSONObject;

public class JsonState extends GameState {

    private final String description;
    private final JSONObject commands;
    private final boolean isEnd;

    public JsonState(JSONObject json) {
        this.description = json.getString("description");
        this.commands = json.getJSONObject("commands");
        this.isEnd = json.optString("end", "false").equalsIgnoreCase("true");
    }

    public boolean isEndState() {
        return isEnd;
    }

    @Override
    public String handleCommand(String input, GameContext context) {
        input = input.toLowerCase();

        if (!commands.has(input)) {
            return "Unknown command. Type 'help'.";
        }

        JSONObject cmd = commands.getJSONObject(input);
        String response = cmd.getString("response");

        // Handle item pickup(s)
        if (cmd.has("addItem")) {
            Object addItemValue = cmd.get("addItem");

            if (addItemValue instanceof JSONArray) {
                JSONArray array = cmd.getJSONArray("addItem");
                for (int i = 0; i < array.length(); i++) {
                    TENIC.inventoryManager.addItem(array.getString(i));
                }
            } else {
                TENIC.inventoryManager.addItem(cmd.getString("addItem"));
            }
        }

        // Handle item removal(s)
        if (cmd.has("removeItem")) {
            Object removeItemValue = cmd.get("removeItem");

            if (removeItemValue instanceof JSONArray) {
                JSONArray array = cmd.getJSONArray("removeItem");
                for (int i = 0; i < array.length(); i++) {
                    TENIC.inventoryManager.removeItem(array.getString(i));
                }
            } else {
                TENIC.inventoryManager.removeItem(cmd.getString("removeItem"));
            }
        }

        // State transition
        String next = cmd.getString("next");
        boolean changed = !next.equals(context.getCurrentBlock());
        context.setStateFromBlock(next);

        if (changed) {
            return response + "\n\n" + context.getCurrentDescription();
        }

        return response;
    }
}
