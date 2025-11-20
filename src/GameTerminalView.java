import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameTerminalView {

    public static void resetGameAfterDeath() {
        ActionListener listener = event -> {
            ((Timer) event.getSource()).stop();

            // reset player + inventory state
            TENIC.inventoryManager.clear();
            TENIC.playerName = "";
            TENIC.playerJob = "";
            TENIC.playerAge = 0;

            TENIC.mJFrame.getContentPane().removeAll();
            TENIC.mJFrame.getContentPane().repaint();
            TENIC.showStartScreen();
        };
        new Timer(6000, listener).start();
    }

    public static void setupGameTerminalUI() {

        TENIC.mJFrame.getContentPane().removeAll();
        TENIC.mJFrame.getContentPane().repaint();
        TENIC.mJFrame.setLayout(null);

        GameEngineFacade engine = new GameEngineFacade();

        // --- OUTPUT AREA ---
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.black);
        outputArea.setForeground(Color.green);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBounds(0, 150, 450, 440);
        outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        outputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScroll.setBorder(BorderFactory.createLineBorder(Color.black));
        outputScroll.getVerticalScrollBar().setBackground(Color.BLACK);
        outputScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.green;
            }
        });

        TENIC.mJFrame.add(outputScroll);

        for (int i = 0; i < 20; i++) outputArea.append("\n");
        outputArea.append("Type help for help\n\n");
        outputArea.append(engine.getOpeningDescription() + "\n\n");

        // --- INVENTORY AREA ---
        JTextArea inventoryArea = new JTextArea();
        inventoryArea.setEditable(false);
        inventoryArea.setBackground(Color.black);
        inventoryArea.setForeground(Color.green);
        inventoryArea.setLineWrap(true);
        inventoryArea.setWrapStyleWord(true);

        JScrollPane inventoryScroll = new JScrollPane(inventoryArea);
        inventoryScroll.setBounds(0, 0, 150, 150);
        inventoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inventoryScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inventoryScroll.setBorder(BorderFactory.createLineBorder(Color.green));
        inventoryScroll.getVerticalScrollBar().setBackground(Color.BLACK);
        inventoryScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.green;
            }
        });

        TENIC.mJFrame.add(inventoryScroll);
        inventoryArea.setText(" INVENTORY\n");

        TENIC.inventoryManager.addObserver(updatedList -> {
            inventoryArea.setText(" INVENTORY\n");
            for (String item : updatedList) {
                inventoryArea.append("- " + item + "\n");
            }
        });

        // --- INPUT FIELD ---
        JTextField commandInput = new JTextField();
        commandInput.setBounds(25, 588, 420, 25);
        commandInput.setBackground(Color.black);
        commandInput.setForeground(Color.green);
        commandInput.setCaretColor(Color.white);
        commandInput.setBorder(BorderFactory.createLineBorder(Color.black));
        TENIC.mJFrame.add(commandInput);

        JLabel cursorLabel = new JLabel(">>>");
        cursorLabel.setBounds(1, 585, 30, 30);
        cursorLabel.setForeground(Color.green);
        cursorLabel.setBackground(Color.black);
        TENIC.mJFrame.add(cursorLabel);

        // --- COMMAND HANDLING ---
        commandInput.addActionListener(e -> {
            String input = commandInput.getText().trim();
            commandInput.setText("");

            if (input.isEmpty()) return;

            outputArea.append(TENIC.playerName + " >>> " + input + "\n");

            if (input.equalsIgnoreCase("clear")) {
                outputArea.setText("");
                for (int i = 0; i < 20; i++) outputArea.append("\n");
                outputArea.append("Type help for help\n\n");
                return;
            }

            String response = engine.handleCommand(input);

            if (response != null && !response.isEmpty()) {
                outputArea.append(response + "\n\n");
            }

            // 🔥 NEW — auto restart when reaching end=true
            if (engine.isEndState()) {
                outputArea.append("GAME OVER. Restarting...\n");
                GameTerminalView.resetGameAfterDeath();
            }

            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        });


        TENIC.mJFrame.setVisible(true);
    }
}
