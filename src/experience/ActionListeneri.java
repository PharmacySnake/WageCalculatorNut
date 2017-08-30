package experience;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ActionListeneri implements ActionListener {

    private final JButton searchButton;
    private final JButton loadButton;
    private final JTextField searchField;
    private final JTextField pathField;
    private final Controller controller;

    protected ActionListeneri(JButton loadButton, JTextField pathField, JButton searchButton, JTextField searchField, Controller controller) {
        this.loadButton = loadButton;
        this.pathField = pathField;
        this.searchButton = searchButton;
        this.searchField = searchField;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.searchButton) {
            this.controller.setEntry(this.searchField.getText());
            this.controller.searchResults();
            this.searchField.setText("");

        } else if (ae.getSource() == this.loadButton) {
            this.controller.fileManager(this.pathField.getText());
            this.pathField.setText("");
        }
    }
}
