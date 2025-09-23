package projekt1.rasterizer;

import java.awt.Color;

public interface Raster {
    void setPixel(int x, int y, Color color);
    void setPixel(int x, int y, int rgb);
    Color getPixel(int x, int y);
    int getWidth();
    int getHeight();
    void clear();
}
