import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Nathan Swanepoel T00225777 SD. none of this code was coppied and pasted, I used Google and <a href="https://www.w3schools.com/java">...</a>
 * to see examples on how to use things such as timers and how to move things in a jframe by setting bounds to objects
 */

public class
TENIC {
    /**
     * create j frame
     */
    static JFrame mJFrame = new JFrame("Tenic Survival");
    /**
     * This creates a global variable that can take in values for the first name, age and job
     */
    static String namef1 = "", job11 = "";
    /**
     * block int holds which block the user enters and basically disables some if statements if youre not progressed as far
     */
    static int age1 = 0, block = 0;

    /**
     * this boolean controls if the if statement will be used when you start the game based on progress
     */
    static boolean secret = false;
    /**
     * this array list will hold the inventory objects that can be added
     */
    static ArrayList<String> objects = new ArrayList<>();

    public static void main(String[] args) {

        /*array list add*/
        objects.add("secretslist");
        objects.add("\nbaseball bat");//}
        /*{set j frame globally*/
        mJFrame.setSize(470, 650);
        mJFrame.setLocationRelativeTo(null);
        mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mJFrame.setResizable(false);
        mJFrame.getContentPane().setBackground(Color.black);

        /*start of the program*/
        startscreen();


    }

    /**
     * This startscreen takes in the details of the user, and does some validation to make sure the input is okay the name and surname can be IDs too like t00225777 that's why it's not limited to strings
     */
    public static void startscreen() {
        //create start button
        JButton Startb = new JButton("Start");
        Startb.setBackground(Color.red);
        Startb.setForeground(Color.green);
        Startb.setBounds(70, 80, 100, 30);
        //create jlables next to intake texts
        JLabel n1 = new JLabel("Name");
        JLabel n2 = new JLabel("    Age");
        JLabel n3 = new JLabel("    Job");
        //set jlable fonts and colours
        n1.setBackground(Color.black);
        n1.setForeground(Color.green);
        n2.setBackground(Color.black);
        n2.setForeground(Color.green);
        n3.setBackground(Color.black);
        n3.setForeground(Color.green);

        //create intake jtextfields
        JTextField playern = new JTextField("", 35);
        JTextField playerage = new JTextField("", 35);
        JTextField playerjob = new JTextField("", 35);

        //set caret position and add objexts to main jframe
        playern.setCaretPosition(0);
        mJFrame.setLayout(new FlowLayout());
        mJFrame.add(n1);
        mJFrame.add(playern);
        mJFrame.add(n2);
        mJFrame.add(playerage);
        mJFrame.add(n3);
        mJFrame.add(playerjob);
        mJFrame.add(Startb);
        mJFrame.setVisible(true);

        //start

        Startb.addActionListener(e -> { // once the button is clicked it will start a simple validation process

            boolean profile1 = true; //this sets the bool to true so if it passes all these if statements it'll continue with the code, but if it doesn't pass
            // it pops and error message, and you'd have to click the button again.


            String namef = playern.getText(); // assign text in textarea to a string
            String agestr = playerage.getText();
            String job1 = playerjob.getText();


            if (playern.getText().isEmpty()) { //check if text is empty it can be an id like: t00225777 or a name
                JOptionPane.showMessageDialog(null, "That is not a name...", "NAME INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                profile1 = false;
            }
            try {
                age1 = Integer.parseInt(agestr); //this tries ti convert age to an int
            } catch (NumberFormatException nfe) {
                //if it cant it'll handle that by prompting that it's not valid, and you'd have to enter it again
                JOptionPane.showMessageDialog(null, "That is not a valid age...", "AGE INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                profile1 = false;
            }
            if (playern.getText().isEmpty()) { //checks if text is empty
                JOptionPane.showMessageDialog(null, "That is not a job...", "JOB INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                profile1 = false;
            }

            if (profile1) { //if the value is true after it passed all validation it'll pass these values into the playername and store it here in global strings.

                age1 = Integer.parseInt(agestr);
                playername p1 = new playername(namef, job1, age1);// creates a new player p1 using the playername file
                namef1 = p1.getName();
                job11 = p1.getProfession();
                age1 = p1.getAge();
                mJFrame.getContentPane().removeAll(); //this resets the jframe
                mJFrame.getContentPane().repaint();
                mJFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                part1.part1method(); // this calls the part1 class
            }


        });


    }


}
