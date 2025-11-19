public abstract class GameState {
    abstract String handleCommand(String input, GameContext context);
}
