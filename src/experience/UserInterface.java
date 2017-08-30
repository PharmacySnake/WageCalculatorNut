package experience;

import logic.Roster;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

    private final Roster roster;
    private JFrame frame;
    private final Controller controller;
    private final JTextArea searchResult;
    

    public UserInterface(Roster roster) {
        this.roster = roster;
        this.searchResult = new JTextArea(40, 30);
        this.searchResult.setEditable(false);
        this.controller = new Controller(this.searchResult, this.roster);
    }

    @Override
    public void run() {
        frame = new JFrame("Wage Calculator");
        frame.setPreferredSize(new Dimension(370, 810));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        generateComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void generateComponents(Container container) {
        JLabel searchLabel = new JLabel("Search: ");
        JLabel resultsLabel = new JLabel("Results:");
        JLabel fileLabel = new JLabel("File path: ");
        JButton searchButton = new JButton("Ok");
        JButton loadButton = new JButton("Ok");
        JTextField searchField = new JTextField("", 20);
        JTextField pathField = new JTextField("", 20);
        SpringLayout layout = new SpringLayout();

        layout.putConstraint(SpringLayout.WEST, fileLabel, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, fileLabel, 15, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, pathField, 55, SpringLayout.WEST, searchLabel);
        layout.putConstraint(SpringLayout.NORTH, pathField, 15, SpringLayout.NORTH, container);
        
        layout.putConstraint(SpringLayout.WEST, loadButton, 230, SpringLayout.WEST, searchField);
        layout.putConstraint(SpringLayout.NORTH, loadButton, 12, SpringLayout.NORTH, container);
        
        layout.putConstraint(SpringLayout.WEST, searchLabel, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 55, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, searchField, 55, SpringLayout.WEST, searchLabel);
        layout.putConstraint(SpringLayout.NORTH, searchField, 55, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, searchButton, 230, SpringLayout.WEST, searchField);
        layout.putConstraint(SpringLayout.NORTH, searchButton, 52, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, this.searchResult, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, this.searchResult, 120, SpringLayout.NORTH, container);
        
        layout.putConstraint(SpringLayout.WEST, resultsLabel, 10, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, resultsLabel, 100, SpringLayout.NORTH, container);

        container.setLayout(layout);

        KeyListeneri keyListener = new KeyListeneri(pathField, searchField, this.controller);
        ActionListeneri actionListener = new ActionListeneri(loadButton, pathField, searchButton, searchField, this.controller);

        pathField.addKeyListener(keyListener);
        searchField.addKeyListener(keyListener);
        loadButton.addActionListener(actionListener);
        searchButton.addActionListener(actionListener);
        
        container.add(searchLabel);
        container.add(resultsLabel);
        container.add(searchField);
        container.add(searchButton);
        container.add(this.searchResult);
        container.add(fileLabel);
        container.add(pathField);
        container.add(loadButton);
    }
}
