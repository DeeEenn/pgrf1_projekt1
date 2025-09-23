package projekt1.controller;

import projekt1.view.Panel;

public class Controller2D {
    private final Panel panel;

    public Controller2D(Panel panel) {
        this.panel = panel;

        panel.getRaster().setRGB(100, 100, 0xFFFF0000);
        panel.repaint();
    }
}
