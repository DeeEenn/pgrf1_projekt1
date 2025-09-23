package projekt1.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import projekt1.rasterizer.Raster;
import projekt1.rasterizer.RasterBufferedImage;

public class Panel extends JPanel {
    private final BufferedImage image;
    private final RasterBufferedImage raster;

    public Panel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        raster = new RasterBufferedImage(image);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public Raster getRaster(){ return raster; }
    public BufferedImage getImage(){ return image; }
}

    
