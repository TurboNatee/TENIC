import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class WelcomeScreen {

    private static final int MIN_RANDOM = 20;
    private static final int MAX_RANDOM = 40;
    private static final int LOAD_CYCLES = 30;
    private static final int LOAD_SPEED_MS = 70;

    public static void initializeGameUI() {

        JTextArea loadingArea = new JTextArea();
        loadingArea.setBackground(Color.black);
        loadingArea.setForeground(Color.green);

        TENIC.mJFrame.setLayout(new FlowLayout());
        TENIC.mJFrame.add(loadingArea);

        final StringBuilder randomLine = new StringBuilder();
        final int[] counter = {0};

        ActionListener loadStep = e -> {

            int length = new Random().nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;

            for (int i = 0; i < length; i++) {
                char c = (char) ('a' + new Random().nextInt(26));
                randomLine.append(c);
            }

            randomLine.append("\n");
            loadingArea.append("<<<<< LOADING >>>>>   " + randomLine);
            randomLine.setLength(0);

            counter[0]++;

            if (counter[0] >= LOAD_CYCLES) {
                ((Timer) e.getSource()).stop();

                loadingArea.setText("");

                loadingArea.append("\n\n\n LOADING COMPLETE");
                loadingArea.append("\n\n\n WELCOME TO TENIC\n");
                loadingArea.append("\nLOGIN DETAILS\n______________\n\n");
                loadingArea.append(TENIC.playerName + "  age " + TENIC.playerAge);
                loadingArea.append("\n_______________\n");
                loadingArea.append(" Everyone here in Tenic has been anxiously awaiting your arrival...\n");
                loadingArea.append(" We need someone with the abilities of a " + TENIC.playerJob + ".\n");

                ActionListener proceedToTerminal = event -> {
                    ((Timer) event.getSource()).stop();
                    TENIC.mJFrame.getContentPane().removeAll();
                    TENIC.mJFrame.getContentPane().repaint();
                    TENIC.mJFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    GameTerminalView.setupGameTerminalUI();
                };

                new Timer(9000, proceedToTerminal).start();
            }
        };

        new Timer(LOAD_SPEED_MS, loadStep).start();
    }
}
