package projekt1.rasterizer;

import java.awt.Color;

import projekt1.model.Line;

public class LineRasterizerBresenham extends LineRasterizer {
    public LineRasterizerBresenham(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(Line line) {
        int thickness = line.getThickness();
        if (thickness <= 1){
            rasterizeNormal(line);
        } else {
            rasterizeThick(line, thickness);
        }
    }

    // BRESENHAMUV ALGORITMUS VYKRESLOVANI USECKY
    private void rasterizeNormal(Line line) {
        // POCATECNI BOD
        int x1 = line.getStart().getX();
        int y1 = line.getStart().getY();
        // KONCOVY BOD
        int x2 = line.getEnd().getX();
        int y2 = line.getEnd().getY();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        
        int stepX = (x1 < x2) ? 1 : -1;  // Směr v X ose
        int stepY = (y1 < y2) ? 1 : -1;  // Směr v Y ose
        
        int err = dx - dy;
        int x = x1;
        int y = y1;
        
        int totalSteps = Math.max(dx, dy);
        int currentStep = 0;
        
        while (true) {
            // Barevná interpolace podle vzdálenosti
            float t = (totalSteps > 0) ? (float) currentStep / totalSteps : 0;
            Color color = interpolateColor(line.getStartColor(), line.getEndColor(), t);
            raster.setPixel(x, y, color);
            
            // Pokud jsme dosáhli cíle
            if (x == x2 && y == y2) break;
            
            int e2 = 2 * err;
            
            if (e2 > -dy) {  // Krok v X
                err -= dy;
                x += stepX;
            }
            
            if (e2 < dx) {  // Krok v Y
                err += dx;
                y += stepY;
            }
            
            currentStep++;
        }
    }
    private void drawThickPixel(int centerX, int centerY, int thickness, Color color){
        int radius = thickness / 2;

        for(int dx = -radius; dx <= radius; dx++){
            for(int dy = -radius; dy <= radius; dy++){
                raster.setPixel(centerX + dx, centerY + dy, color);
            }
        }
    }

    private void rasterizeThick(Line line, int thickness){
        int x1 = line.getStart().getX();
        int y1 = line.getStart().getY();
        int x2 = line.getEnd().getX();
        int y2 = line.getEnd().getY();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        
        int stepX = (x1 < x2) ? 1 : -1;
        int stepY = (y1 < y2) ? 1 : -1;
        
        int err = dx - dy;
        int x = x1;
        int y = y1;
        
        int totalSteps = Math.max(dx, dy);
        int currentStep = 0;
        
        while (true) {
            // Barevná interpolace
            float t = (totalSteps > 0) ? (float) currentStep / totalSteps : 0;
            Color color = interpolateColor(line.getStartColor(), line.getEndColor(), t);
            
            drawThickPixel(x, y, thickness, color);
            
            if (x == x2 && y == y2) break;
            
            int e2 = 2 * err;
            
            if (e2 > -dy) {
                err -= dy;
                x += stepX;
            }
            
            if (e2 < dx) {
                err += dx;
                y += stepY;
            }
            
            currentStep++;
        }
    }

    private Color interpolateColor(Color startColor, Color endColor, float t){
        int red = (int) ((1 - t) * startColor.getRed() + t * endColor.getRed());
        int green = (int) ((1 - t) * startColor.getGreen() + t * endColor.getGreen());
        int blue = (int) ((1 - t) * startColor.getBlue() + t * endColor.getBlue());

        return new Color(red, green, blue);
    }
}


