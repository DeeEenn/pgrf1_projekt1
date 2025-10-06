package projekt1.rasterizer;

import java.awt.Color;

import projekt1.model.Line;

public class FilledLineRasterizer extends LineRasterizer {
    public FilledLineRasterizer(Raster raster) {
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

    // DDA ALGORITMUS VYKRESLOVANI USECKY
    private void rasterizeNormal(Line line) {
        // POCATECNI BOD
        int x1 = line.getStart().getX();
        int y1 = line.getStart().getY();
        // KONCOVY BOD
        int x2 = line.getEnd().getX();
        int y2 = line.getEnd().getY();

        // Vypocitani rozdilu
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        // Vypocitani inkrementu pro kazdy krok
        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for(int i = 0; i <= steps; i++){
            float t = (float) i / steps;
            Color color = interpolateColor(line.getStartColor(), line.getEndColor(), t);
            raster.setPixel(Math.round(x), Math.round(y), color);
            x += xIncrement;
            y += yIncrement;
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

        // Vypocitani rozdilu
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        // Vypocitani inkrementu pro kazdy krok
        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            float t = (float) i / steps;
            Color currentColor = interpolateColor(line.getStartColor(), line.getEndColor(), t);
    
            drawThickPixel(Math.round(x), Math.round(y), thickness, currentColor);
            x += xIncrement;
            y += yIncrement;
        }
    }

    private Color interpolateColor(Color startColor, Color endColor, float t){
        int red = (int) ((1 - t) * startColor.getRed() + t * endColor.getRed());
        int green = (int) ((1 - t) * startColor.getGreen() + t * endColor.getGreen());
        int blue = (int) ((1 - t) * startColor.getBlue() + t * endColor.getBlue());

        return new Color(red, green, blue);
    }
}


