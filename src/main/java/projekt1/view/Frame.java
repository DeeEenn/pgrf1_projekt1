package projekt1.view;

import javax.swing.JFrame;

public class Frame extends JFrame {

    private final Panel panel;

    public Frame(int width, int height) {
        setTitle("Projekt 1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        panel = new Panel(width, height);
        add(panel);
        pack();

        panel.setFocusable(true);
    }

    public Panel getPanel() {
        return panel;
    }
}