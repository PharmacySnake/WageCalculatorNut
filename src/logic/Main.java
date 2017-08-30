package logic;

import experience.UserInterface;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Staff staff = new Staff();
        Roster roster = new Roster(staff);

        SwingUtilities.invokeLater(new UserInterface(roster));
    }
}
