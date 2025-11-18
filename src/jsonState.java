import org.json.JSONObject;

public class jsonState extends gameState {

    private final String description;
    private final JSONObject commands;

    public jsonState(JSONObject json) {
        this.description = json.getString("description");
        this.commands = json.getJSONObject("commands");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String handleCommand(String input, gameContext context) {
        input = input.toLowerCase();

        if (!commands.has(input)) {
            return "Unknown command. Type 'help'.";
        }

        JSONObject cmd = commands.getJSONObject(input);
        String response = cmd.getString("response");

        String next = cmd.getString("next");

        boolean stateChanged = !next.equals(context.getCurrentBlock());
        context.setStateFromBlock(next);

        if (stateChanged) {
            return response + "\n\n" + context.getDescriptionFor(next);
        }

        return response;
    }

}
