import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TENIC {
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


        JFrame mJFrame = new JFrame("Tenic Survival");
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

                String namef = playern.getText();
                String agestr = playerage.getText();
                String job1 = playerjob.getText();
                if(!profile1) {
                    profile1 = true;
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
                        JOptionPane.showMessageDialog(null, "That is not a job...", "NAME INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                        profile1 = false;
                    }
                }

                if (profile1) {
                    playername p1 = new playername(namef, job1, age1);
                    String A1 = p1.name + " " + p1.profession + " " + p1.age;
                    JOptionPane.showMessageDialog(Startb, A1, "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    mJFrame.getContentPane().removeAll();
                    mJFrame.getContentPane().repaint();
                }


            }
        });


    }
}
