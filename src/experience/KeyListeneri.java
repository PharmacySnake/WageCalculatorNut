package experience;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class KeyListeneri implements KeyListener {

    private final JTextField searchField;
    private final JTextField pathField;
    private final Controller controller;

    protected KeyListeneri(JTextField pathField, JTextField searchField, Controller controller) {
        this.searchField = searchField;
        this.pathField = pathField;
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.searchField.getText() != "") {
                controller.setEntry(this.searchField.getText());
                this.controller.searchResults();
                this.searchField.setText("");
                
            } else if (this.pathField.getText() != "") {
                this.controller.fileManager(this.pathField.getText());
                this.pathField.setText("");
            }
        }
    }
}
