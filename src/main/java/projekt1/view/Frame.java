package projekt1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

    private Panel panel;
    private JLabel statusLabel;

    public Frame(int width, int height) {
        setupFrame();
        setupComponents(width, height);
        setupLayout();
        finalizeSetup();
    }
    
    private void setupFrame() {
        setTitle("PROJEKT1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
    }
    
    private void setupComponents(int width, int height) {
        // Hlavní kreslicí plátno
        panel = new Panel(width, height);
        
        // Status bar
// V setupComponents() změň řádek se statusLabel na:
statusLabel = new JLabel("Kresleni: Klikni a tahni mysi | Mazani: Klavesa C | Tloustka usecky: Klavesa T | ");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        statusLabel.setBackground(new Color(240, 240, 240));
        statusLabel.setOpaque(true);
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
    }
    
    private void setupLayout() {
        add(panel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    private void finalizeSetup() {
        pack();
        setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
    }

    public Panel getPanel() {
        return panel;
    }
    
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }
}