package projekt1.rasterizer;

import projekt1.model.Line;

public abstract class LineRasterizer {
    protected Raster raster;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public abstract void rasterize(Line line);
}
