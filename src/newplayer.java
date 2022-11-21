import javax.swing.*;

public class newplayer {
    public static void main(String[] args) {

        playername p1 = new playername("nathan", "computing", 19);
        String out = "";
        out += p1;
        JOptionPane.showConfirmDialog(null, "" + out, "hewwow", JOptionPane.DEFAULT_OPTION );
        out = "";
        out += p1.name;
        JOptionPane.showConfirmDialog(null, "" + out, "hewwow", JOptionPane.DEFAULT_OPTION );
    }
}
