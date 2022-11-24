import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
/**none of this code was coppied and pasted, I used Google and <a href="https://www.w3schools.com/java">...</a> to see examples on how to use things such as timers and how to move things in a jframe by setting bounds
 to objects*/

public class TENIC {
    /** create j frame*/
    static JFrame mJFrame = new JFrame("Tenic Survival");
    /** This creates a global variable that can take in values for the first name, age and job*/
    static String namef1 = "";
    static String job11 = "";
    /**block int holds which block the user enters and basically disables some if statements if youre not progressed as far*/
    static int age1 = 0, block = 0;

    /**this boolean controls if the if statement will be used when you start the game based on progress*/
    static boolean secret = false;
    /** this array list will hold the inventory objects that can be added*/
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
/**This startscreen takes in the details of the user, and does some validation to make sure the input is okay the name and surname can be IDs too like t00225777 that's why it's not limited to strings*/
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
                namef1 = p1.name;
                job11 = p1.profession;
                age1 = p1.age;
                mJFrame.getContentPane().removeAll(); //this resets the jframe
                mJFrame.getContentPane().repaint();
                mJFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                part1(); // this calls the part1 class
            }


        });


    }
/**part1 creates a "loading" screen for a cool visual effect */
    public static void part1() {
        int max = 40, min = 20;
        int range = max - min + 1;
        JTextArea txtarea = new JTextArea(); // creates new textarea for a loading screen
        txtarea.setBackground(Color.black);
        txtarea.setForeground(Color.green);
        mJFrame.setLayout(new FlowLayout());
        mJFrame.add(txtarea);
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
                txtarea.append("\n\n\n <<<<<<<<<<<<<<<<<<<<<<<<WELCOME>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n " + "LOGIN DETAILS\n______________\n\n" + namef1 + " age " + age1 + "\n_______________\n" +
                        " Everyone here in Tenica has been anxiously awaiting your arrival...\n " +
                        " we have been waiting for someone with the abilities of a " + job11);
                ActionListener listener1 = event -> {

                    ((Timer) event.getSource()).stop();
                    mJFrame.getContentPane().removeAll();
                    mJFrame.getContentPane().repaint();
                    mJFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    part2();


                };
                Timer timer1 = new Timer(9000, listener1);
                timer1.start();

            }
        };

        Timer timer = new Timer(70, listener); //this loops by 70miliseconds until the timer has stopped by calling the listener for 30 times this creates a cool loading screen
        timer.start();


    }
/**This is the actual game part where decision-making takes place using if statements  */
    public static void part2() {
//cmndout window to show previous commands and outputs
        //_____________________________________________________________________________{


        JTextArea cmndout = new JTextArea();
        cmndout.setEditable(true);
        JScrollPane scrollPane1 = new JScrollPane(cmndout);
        scrollPane1.setBounds(0, 150, 450, 440);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //adds vertical scroll bar when its needed so you can scroll through previous commands
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.getVerticalScrollBar().setBackground(Color.BLACK);//customising the scrollbar
        scrollPane1.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            protected void configureScrollBarColors() {
                this.thumbColor = Color.green;

            }
        });
        mJFrame.getContentPane().add(scrollPane1);
        for (int i = 0; i < 25; i++) {
            cmndout.append("\n");

        }
        cmndout.append("Type help for help\n"); //this appends to the cmd out window as a tip for the player
        cmndout.setBackground(Color.black);
        cmndout.setForeground(Color.green);
        /* ______________________________________________________________________________________________} */


//inventory textarea to show current items in inventory{
        //_____________________________________________________________________________________________________
        JTextArea ivntry = new JTextArea();
        ivntry.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ivntry);
        mJFrame.setLayout(null);
        scrollPane.setBounds(0, 0, 150, 150);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.green));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            protected void configureScrollBarColors() {
                this.thumbColor = Color.green; //sets the colour of the scrollbar thumb
            }
        });

        ivntry.append(" INVENTORY\n");
        mJFrame.getContentPane().add(scrollPane);
        ivntry.setBackground(Color.black);
        ivntry.setForeground(Color.green);
        //____________________________________________________________________________________________________}
        //command textarea to take in commands{
        //_____________________________________________________________________________________________________
        JTextField commands = new JTextField();
        commands.setBorder(BorderFactory.createLineBorder(Color.black));
        commands.setBackground(Color.black);
        commands.setForeground(Color.green);
        commands.setCaretColor(Color.WHITE);
        mJFrame.add(commands);
        commands.setBounds(25, 593, 420, 15);

        JLabel cmndcur = new JLabel(">>>");
        cmndcur.setBounds(1, 585, 30, 30);
        cmndcur.setBackground(Color.black);
        cmndcur.setForeground(Color.green);
        mJFrame.add(cmndcur);

        //output onto the output screen from commands line_________________________________________________________________________{

        commands.addActionListener(e -> {

            String output = commands.getText();
            commands.setText("");
            cmndout.setEditable(false);
            cmndout.append(namef1 + " >>>");
            cmndout.append(output);
            cmndout.append("\n");
            //______________________________________________________________________________________________________________}
            //________________________________________if and else statements for the gameplay with desision making__________{
            if (Objects.equals(output.toLowerCase(), "help")) {
                cmndout.append("""

                        ___________________________________
                        list of commands are: \s
                         start\s
                         take (item)\s
                         open (object from inventory)\s
                         go to (location)\s
                         use (item from inventory)
                        __________________________________
                         
                         """);

            }
            if (Objects.equals(output.toLowerCase(), "take secretslist")) {
                ivntry.append("\n");
                ivntry.append(objects.get(0));
                secret = true;
            }

            if (output.equals("open secretslist") && secret) {
                cmndout.append("\nTHIS FILE IS PASSWORD PROTECTED\n type hintpswd for a hint and then 'open secretslist (password here)'");


            }
            if (Objects.equals(output.toLowerCase(), "hintpswd")) {
                cmndout.append("\n'the password is the first and last letter of our group' - TENIC TEAM");
            }
            if (Objects.equals(output.toLowerCase(), "open secretslist tc")) {
                cmndout.append("\n At the time of someone reading this, I will either be in Block1,\n or block4, come find me.");

            }
            if (Objects.equals(output.toLowerCase(), "clear")) {
                for (int i = 0; i < 25; i++) {
                    cmndout.append("\n");

                }
                cmndout.append("type 'help' to help");
            }
            if (Objects.equals(output.toLowerCase(), "start")) {
                cmndout.append("\nTime for you to save us from the hacker who destroyed us all...\n here's something that might be helpful to you that they left\n *shows secretslist*");

            }
            if (Objects.equals(output.toLowerCase(), "go to block1")) {
                cmndout.append("\nWrong choice... you entered block1 and discovered you are trapped\n you have two choices\nrun or hide");
                block = 1;
            }
            if (output.equalsIgnoreCase("run") && block == 1) {
                cmndout.append("\ngood choice.. you run and see a baseball bat");

            }
            if (output.equalsIgnoreCase("hide") && block == 1) {
                cmndout.append("\nwrong choice... you died...RIP");
                ActionListener listener3 = event -> {
                    ((Timer) event.getSource()).stop();
                    mJFrame.getContentPane().removeAll();
                    mJFrame.getContentPane().repaint();
                    startscreen();
                };

                Timer timer1 = new Timer(9000, listener3);
                timer1.start();
            }

            if (output.equalsIgnoreCase("take baseball bat") && block == 1) {
                ivntry.append("" + objects.get(1));
                cmndout.append("\nsomeone attacks you and you defend yourself with the bat and find the exit\n better luck in the next block?");

            }


            if (Objects.equals(output.toLowerCase(), "go to block4")) {
                cmndout.append("\nYou enter block 4, all seems quiet.. too quiet\nyou explore and see a tall figure\ntalk or ignore?");
                block = 2;
            }
            if (output.equalsIgnoreCase("talk") && block == 2) {
                cmndout.append("\nIt's him, the hacker.. you talk to him and he pulls out a knife..\n disarm or run?");

            }
            if (output.equalsIgnoreCase("run") && block == 2) {
                cmndout.append("\ngood choice.. but he comes after you.. what can you use to defend yourself?");

            }
            if (output.equalsIgnoreCase("disarm") && block == 2) {
                cmndout.append("\nbad choice.. you missed and he stabbed you to death");
                ActionListener listener3 = event -> {
                    ((Timer) event.getSource()).stop();
                    mJFrame.getContentPane().removeAll();
                    mJFrame.getContentPane().repaint();
                    startscreen();
                };
                Timer timer1 = new Timer(9000, listener3);
                timer1.start();

            }

            if (output.equalsIgnoreCase("use baseball bat") && block == 2) {
                cmndout.append("\nhe comes after you and you defend yourself with the bat\nhe dies\n you are forever seen as a hero");
                String savegame = "savegame.txt", savetext;
                try {
                    FileOutputStream save = new FileOutputStream(savegame);
                    ObjectOutputStream saveout = new ObjectOutputStream(save);
                    savetext = ("\n\nName: " + namef1 + "\nAge: " + age1 + "\nJob: " + job11);
                    saveout.writeChars(savetext);
                    saveout.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                ActionListener listener3 = event -> {
                    ((Timer) event.getSource()).stop();
                    System.exit(0);
                };
                Timer timer1 = new Timer(9000, listener3);
                timer1.start();


            }
            if (output.equalsIgnoreCase("ignore") && block == 2) {
                cmndout.append("\nit's too late...");
                cmndout.append("\nIt's him, the hacker.. he pulls out a knife..\n disarm or run?");

            }


            cmndout.append("\n");


        });
        //___________________________________________________________________________________________________________________________}
        mJFrame.setVisible(true);


    }

}
