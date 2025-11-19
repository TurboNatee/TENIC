import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TENIC {

    static final JFrame mJFrame = new JFrame("Tenic Survival");

    static String playerName = "";
    static String playerJob = "";
    static int playerAge = 0;

    static final ArrayList<String> inventory = new ArrayList<>();

    public static void main(String[] args) {
        inventory.add("secretslist");
        inventory.add("baseball bat");

        setupMainFrame();
        showStartScreen();
    }

    private static void setupMainFrame() {
        mJFrame.setSize(460, 650);
        mJFrame.setLocationRelativeTo(null);
        mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mJFrame.setResizable(false);
        mJFrame.getContentPane().setBackground(Color.black);
    }

    public static void showStartScreen() {
        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.red);
        startButton.setForeground(Color.green);

        JLabel lblName = new JLabel("Name");
        JLabel lblAge = new JLabel("Age");
        JLabel lblJob = new JLabel("Job");

        lblName.setForeground(Color.green);
        lblAge.setForeground(Color.green);
        lblJob.setForeground(Color.green);

        JTextField txtName = new JTextField("", 35);
        JTextField txtAge = new JTextField("", 35);
        JTextField txtJob = new JTextField("", 35);

        mJFrame.setLayout(new FlowLayout());
        mJFrame.add(lblName);
        mJFrame.add(txtName);
        mJFrame.add(lblAge);
        mJFrame.add(txtAge);
        mJFrame.add(lblJob);
        mJFrame.add(txtJob);
        mJFrame.add(startButton);
        mJFrame.setVisible(true);

        startButton.addActionListener(e -> {


            String nameInput = txtName.getText();
            String ageInput = txtAge.getText();
            String jobInput = txtJob.getText();




            boolean valid = validatePlayer(nameInput, jobInput, ageInput);

            if (valid) {
                CreatePlayer player = new CreatePlayer(nameInput, jobInput, playerAge);
                playerName = player.getName();
                playerJob = player.getProfession();
                playerAge = player.getAge();

                mJFrame.getContentPane().removeAll();
                mJFrame.getContentPane().repaint();
                mJFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

                WelcomeScreen.initializeGameUI();
            }
        });
    }

    public static boolean validatePlayer(String nameInput, String jobInput, String ageInput){
        boolean valid = true;
        if (nameInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid name.", "NAME ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        try {
            playerAge = Integer.parseInt(ageInput);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Age must be a number.", "AGE ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        if (jobInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid job.", "JOB ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
        return valid;
    }
}
