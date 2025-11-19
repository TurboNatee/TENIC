public class GameEngineFacade {

    private final GameController controller;

    public GameEngineFacade() {
        this.controller = new GameController();
    }

    public String getOpeningDescription() {
        return controller.getOpeningDescription();
    }

    public String handleCommand(String input) {
        return controller.processCommand(input);
    }

    public String getCurrentBlock() {
        return controller.getCurrentBlock();
    }
}
