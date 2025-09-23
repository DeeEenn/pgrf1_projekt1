package projekt1.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panel extends JPanel {
    private final BufferedImage raster;

    public Panel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        raster = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(raster, 0, 0, null);
    }

    public BufferedImage getRaster(){
        return raster;
    }
}
    
