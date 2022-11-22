import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;



public class TENIC {
    static JFrame  mJFrame = new JFrame("Tenic Survival");
    static int pnum = 0;
    public static void main(String[] args) {

    startscreen();


    }

    public static void startscreen(){
        JButton Startb = new JButton("Start");
        Startb.setBackground(Color.red);
        Startb.setForeground(Color.green);
        Startb.setBounds(70, 80, 100, 30);

        JLabel n1 = new JLabel("Name");
        JLabel n2 = new JLabel("Age ");
        JLabel n3 = new JLabel("Job ");
        n1.setBackground(Color.black);
        n1.setForeground(Color.green);
        n2.setBackground(Color.black);
        n2.setForeground(Color.green);
        n3.setBackground(Color.black);
        n3.setForeground(Color.green);



        mJFrame.setSize(450, 650);
        FlowLayout layout = new FlowLayout();
        mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mJFrame.setLocationRelativeTo(null);
        mJFrame.setLocation(400, 200);
        mJFrame.setResizable(false);

        JTextField playern = new JTextField("", 35);
        JTextField playerage = new JTextField("", 35);
        JTextField playerjob = new JTextField("", 35);


        playern.setCaretPosition(0);
        mJFrame.getContentPane().setBackground(Color.black);
        mJFrame.add(n1);
        mJFrame.add(playern);
        mJFrame.add(n2);
        mJFrame.add(playerage);
        mJFrame.add(n3);
        mJFrame.add(playerjob);
        mJFrame.add(Startb);
        mJFrame.setLayout(layout);
        mJFrame.setVisible(true);

        //start

        Startb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean profile1 = false;
                int age1 = 0;

                String namef = playern.getText(); // set text in textarea to a string
                String agestr = playerage.getText();
                String job1 = playerjob.getText();

                if(!profile1) {   //boolean profile1 is false so it'll proceed to check if the values entered are okay
                    profile1 = true; //this sets the bool to true so if it passes all these if statements it'll continue with the code, but if it doesnt pass it pops and error message and you'd have to click the button again.
                    if (playern.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "That is not a name...", "NAME INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                        profile1 = false;
                    }
                    try {
                        age1 = Integer.parseInt(agestr);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "That is not a valid age...", "AGE INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                        profile1 = false;
                    }
                    if (playern.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "That is not a job...", "JOB INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                        profile1 = false;
                    }
                }

                if (profile1) { //if the value is true after it passed all validation itll store these values into the playername file.
                    playername p1 = new playername(namef, job1, age1);
                   // String A1 = p1.name + " " + p1.profession + " " + p1.age;
                   // JOptionPane.showMessageDialog(Startb, A1, "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    mJFrame.getContentPane().removeAll();
                    mJFrame.getContentPane().repaint();
                    mJFrame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    pnum = 1;
                    part1();
                }


            }
        });



    }
    public static void part1(){
        int max = 40, min = 20;
        int range = max - min + 1;
        JTextArea prt1 = new JTextArea();
        prt1.setBackground(Color.black);
        prt1.setForeground(Color.green);
        prt1.setAlignmentX(Component.LEFT_ALIGNMENT);
        mJFrame.add(prt1);
        final String[] randstring = {""};
        final int[] counter = {0};

        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ){
                int randlength = (int) (Math.random() * range) + min;
                for (int i2 = 0; i2 < randlength; i2++) {
                    Random r = new Random();
                    char c = (char) (r.nextInt(26) + 'a');
                    randstring[0] = randstring[0] + c;

                }
                randstring[0] += "\n";
                prt1.append("<<<<<LOADING>>>>>>   ");
                prt1.append(randstring[0]);
                randstring[0] = "";



                counter[0]++;
                if ( counter[0] == 30 ){
                    ((Timer)e.getSource()).stop();
                    prt1.append("\n\n\n<<<<<<<<<<<<<<<<<<<<<<<<<DONE>>>>>>>>>>>>>>>>>>>>>>>>>>   ");
                }
            }
        };

        Timer timer = new Timer( 50, listener );
        timer.start();




    }
}
