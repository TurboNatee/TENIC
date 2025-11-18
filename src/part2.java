import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class part2 {

    public static void diereset() {
        ActionListener listener = event -> {
            ((Timer) event.getSource()).stop();
            TENIC.mJFrame.getContentPane().removeAll();
            TENIC.mJFrame.getContentPane().repaint();
            TENIC.startscreen();
        };
        Timer timer1 = new Timer(6000, listener);
        timer1.start();
    }

    public static void part2methods() {

        gameController controller = new gameController();

        JTextArea cmndout = new JTextArea();
        cmndout.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(cmndout);
        scrollPane1.setBounds(0, 150, 450, 440);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.getVerticalScrollBar().setBackground(Color.BLACK);
        scrollPane1.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            protected void configureScrollBarColors() {
                this.thumbColor = Color.green;
            }
        });
        TENIC.mJFrame.getContentPane().add(scrollPane1);
        for (int i = 0; i < 25; i++) {
            cmndout.append("\n");
        }
        cmndout.append("Type help for help\n");
        cmndout.setBackground(Color.black);
        cmndout.setForeground(Color.green);

        JTextArea ivntry = new JTextArea();
        ivntry.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ivntry);
        TENIC.mJFrame.setLayout(null);
        scrollPane.setBounds(0, 0, 150, 150);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.green));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            protected void configureScrollBarColors() {
                this.thumbColor = Color.green;
            }
        });

        ivntry.append(" INVENTORY\n");
        TENIC.mJFrame.getContentPane().add(scrollPane);
        ivntry.setBackground(Color.black);
        ivntry.setForeground(Color.green);

        JTextField commands = new JTextField();
        commands.setBorder(BorderFactory.createLineBorder(Color.black));
        commands.setBackground(Color.black);
        commands.setForeground(Color.green);
        commands.setCaretColor(Color.WHITE);
        TENIC.mJFrame.add(commands);
        commands.setBounds(25, 588, 420, 25);

        JLabel cmndcur = new JLabel(">>>");
        cmndcur.setBounds(1, 585, 30, 30);
        cmndcur.setBackground(Color.black);
        cmndcur.setForeground(Color.green);
        TENIC.mJFrame.add(cmndcur);

        commands.addActionListener(e -> {
            String output = commands.getText().trim();
            commands.setText("");

            cmndout.append(TENIC.namef1 + " >>> " + output + "\n");

            if (output.equalsIgnoreCase("clear")) {
                for (int i = 0; i < 25; i++) {
                    cmndout.append("\n");
                }
                cmndout.append("Type help for help\n");
                return;
            }

            String response = controller.processCommand(output);

            if (response != null && !response.isEmpty()) {
                cmndout.append(response + "\n");
            }

            cmndout.append("\n");
            cmndout.setCaretPosition(cmndout.getDocument().getLength());
        });

        TENIC.mJFrame.setVisible(true);
    }
}
