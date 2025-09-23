package projekt1.rasterizer;

import projekt1.model.Line;

public class FilledLineRasterizer extends LineRasterizer {
    // Implementace rasterizace cary DDA algoritmem
    public FilledLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(Line line) {
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
            raster.setPixel(Math.round(x), Math.round(y), line.getColor());
            x += xIncrement;
            y += yIncrement;
        }
    }
}


