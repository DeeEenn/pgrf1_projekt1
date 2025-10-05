package projekt1.model;

import java.awt.Color;

public class Line {
    private Point start, end;
    private Color startColor, endColor; 
    private int thickness;

    public Line(Point start, Point end) {
        this(start, end, Color.WHITE, 1);
    }

    public Line(Point start, Point end, Color color) {
        this(start, end, color, 1);
    }
    
    // Konstruktor pro tlusté úsečky
    public Line(Point start, Point end, Color color, int thickness) {
        this(start, end, color, color, thickness); 
    }

    // Konstruktor pro úsečky s interpolaci barev
    public Line(Point start, Point end, Color startColor, Color endColor, int thickness) {
        this.start = start;
        this.end = end;
        this.startColor = startColor;    
        this.endColor = endColor;       
        this.thickness = thickness;
    }

    public Point getStart() { return start; }
    public Point getEnd() { return end; }
    public Color getColor() { return startColor; }  
    public Color getStartColor() { return startColor; }  
    public Color getEndColor() { return endColor; }    
    public int getThickness() { return thickness; }
    
    public void setStart(Point start) { this.start = start; }
    public void setEnd(Point end) { this.end = end; }
    public void setColor(Color color) {
        this.startColor = color;
        this.endColor = color;
    }
    public void setThickness(int thickness) { this.thickness = thickness; }
}