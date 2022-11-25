import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class part1 {
    public static void main(String[] args) {
    }

    /**
     * part1 creates a "loading" screen for a cool visual effect
     */
    public static void part1method() {
        int max = 40, min = 20;
        int range = max - min + 1;
        JTextArea txtarea = new JTextArea(); // creates new textarea for a loading screen
        txtarea.setBackground(Color.black);
        txtarea.setForeground(Color.green);
        TENIC.mJFrame.setLayout(new FlowLayout());
        TENIC.mJFrame.add(txtarea);
        final String[] randstring = {""};
        final int[] counter = {0};

        ActionListener listener = e -> {
            int randlength = (int) (Math.random() * range) + min;
            for (int i2 = 0; i2 < randlength; i2++) {
                Random r = new Random();
                char c = (char) (r.nextInt(26) + 'a');
                randstring[0] = randstring[0] + c; //this creates a random string with random letters

            }
            randstring[0] += "\n";
            txtarea.append("<<<<<LOADING>>>>>>   ");
            txtarea.append(randstring[0]);
            randstring[0] = "";


            counter[0]++;
            if (counter[0] == 30) {
                ((Timer) e.getSource()).stop();
                txtarea.selectAll();

                txtarea.replaceSelection("");
                txtarea.append("\n\n\n <<<<<<<<<<<<<<<<<<<<<<<<<<DONE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   ");
                txtarea.append("\n\n\n <<<<<<<<<<<<<<<<<<<<<<<<WELCOME>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n " + "LOGIN DETAILS\n______________\n\n" + TENIC.namef1 + " age " + TENIC.age1 + "\n_______________\n" +
                        " Everyone here in Tenica has been anxiously awaiting your arrival...\n " +
                        " we have been waiting for someone with the abilities of a " + TENIC.job11);
                ActionListener listener1 = event -> {

                    ((Timer) event.getSource()).stop();
                    TENIC.mJFrame.getContentPane().removeAll();
                    TENIC.mJFrame.getContentPane().repaint();
                    TENIC.mJFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    part2.part2methods();


                };
                Timer timer1 = new Timer(9000, listener1);
                timer1.start();

            }
        };

        Timer timer = new Timer(70, listener); //this loops by 70miliseconds until the timer has stopped by calling the listener for 30 times this creates a cool loading screen
        timer.start();


    }
}
