import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class part2 {
    /**
     * This is the actual game part where decision-making takes place using if statements
     */
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
//cmndout window to show previous commands and outputs
        //_____________________________________________________________________________{

        JTextArea cmndout = new JTextArea();
        cmndout.setEditable(false);
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
        TENIC.mJFrame.getContentPane().add(scrollPane1);
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
        TENIC.mJFrame.setLayout(null);
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
        TENIC.mJFrame.getContentPane().add(scrollPane);
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
        TENIC.mJFrame.add(commands);
        commands.setBounds(25, 588, 420, 25);
        //create a cmd cursor
        JLabel cmndcur = new JLabel(">>>");
        cmndcur.setBounds(1, 585, 30, 30);
        cmndcur.setBackground(Color.black);
        cmndcur.setForeground(Color.green);
        TENIC.mJFrame.add(cmndcur);
        //______________________________________________________________________________________________________________}
        //output onto the output screen from commands line_________________________________________________________________________{
        commands.addActionListener(e -> {
            String output = commands.getText();
            commands.setText("");
            cmndout.append(TENIC.namef1 + " >>>");
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
                ivntry.append(TENIC.objects.get(0));
                TENIC.secret = true;
            }
            if (output.equals("open secretslist") && TENIC.secret) {
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
                TENIC.block = 1;
            }
            if (output.equalsIgnoreCase("run") && TENIC.block == 1) {
                cmndout.append("\ngood choice.. you run and see a baseball bat");
            }
            if (output.equalsIgnoreCase("hide") && TENIC.block == 1) {
                cmndout.append("\nwrong choice... you died...RIP");
                diereset();
            }
            if (output.equalsIgnoreCase("take baseball bat") && TENIC.block == 1) {
                ivntry.append("" + TENIC.objects.get(1));
                cmndout.append("\nsomeone attacks you and you defend yourself with the bat and find the exit\n better luck in the next block?");
            }
            if (Objects.equals(output.toLowerCase(), "go to block4")) {
                cmndout.append("\nYou enter block 4, all seems quiet.. too quiet\nyou explore and see a tall figure\ntalk or ignore?");
                TENIC.block = 2;
            }
            if (output.equalsIgnoreCase("talk") && TENIC.block == 2) {
                cmndout.append("\nIt's him, the hacker.. you talk to him and he pulls out a knife..\n disarm or run?");
            }
            if (output.equalsIgnoreCase("run") && TENIC.block == 2) {
                cmndout.append("\ngood choice.. but he comes after you.. what can you use to defend yourself?");
            }
            if (output.equalsIgnoreCase("disarm") && TENIC.block == 2) {
                cmndout.append("\nbad choice.. you missed and he stabbed you to death");
                diereset();
            }
            if (output.equalsIgnoreCase("use baseball bat") && TENIC.block == 2) {
                cmndout.append("\nhe comes after you and you defend yourself with the bat\nhe dies\n you are forever seen as a hero");

                String savegame = "savegame.txt", savetext;
                try {
                    FileOutputStream save = new FileOutputStream(savegame);
                    ObjectOutputStream saveout = new ObjectOutputStream(save);
                    savetext = ("\n\nName: " + TENIC.namef1 + "\nAge: " + TENIC.age1 + "\nJob: " + TENIC.job11);
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
            if (output.equalsIgnoreCase("ignore") && TENIC.block == 2) {
                cmndout.append("\nit's too late...");
                cmndout.append("\nIt's him, the hacker.. he pulls out a knife..\n disarm or run?");
            }
            cmndout.append("\n");
        });
        //___________________________________________________________________________________________________________________________}
        TENIC.mJFrame.setVisible(true);


    }
}
