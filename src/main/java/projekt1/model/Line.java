package projekt1.model;

import java.awt.Color;

public class Line {
    private Point start, end;
    private Color color;
    private int thickness;

    public Line(Point start, Point end) {
        this(start, end, Color.WHITE, 1);
    }

    public Line(Point start, Point end, Color color) {
        this(start, end, color, 1);
    }
    
    public Line(Point start, Point end, Color color, int thickness) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.thickness = thickness;
    }

    public Point getStart() { return start; }
    public Point getEnd() { return end; }
    public Color getColor() { return color; }
    public int getThickness() { return thickness; }
    
    public void setStart(Point start) { this.start = start; }
    public void setEnd(Point end) { this.end = end; }
    public void setColor(Color color) { this.color = color; }
    public void setThickness(int thickness) { this.thickness = thickness; }
}
