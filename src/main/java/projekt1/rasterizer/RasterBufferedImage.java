package projekt1.rasterizer;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {
    private final BufferedImage image;

    public RasterBufferedImage(BufferedImage image) {
        this.image = image;
        clear();
    }

    @Override
    public void setPixel(int x, int y, Color color){
        if (isInBounds(x, y)) {
            image.setRGB(x, y, color.getRGB());
        }
    }

    @Override
    public void setPixel(int x, int y, int rgb) {
        if (isInBounds(x, y)) {
            image.setRGB(x, y, rgb);
        }
    }

    @Override
    public Color getPixel(int x, int y) {
        if (isInBounds(x, y)) {
            return new Color(image.getRGB(x, y), true);
        }
        return Color.BLACK;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void clear() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                setPixel(x, y, Color.BLACK);
            }
        }
    }

    private boolean isInBounds(int x, int y){
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

}
