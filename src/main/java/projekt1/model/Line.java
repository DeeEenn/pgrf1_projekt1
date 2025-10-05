package projekt1.model;

import java.awt.Color;

public class Line {
    private Point start, end;
    private Color startColor, endColor;  // Změň na dvě barvy
    private int thickness;

    public Line(Point start, Point end) {
        this(start, end, Color.WHITE, 1);
    }

    public Line(Point start, Point end, Color color) {
        this(start, end, color, 1);
    }
    
    // Konstruktor pro tlusté úsečky
    public Line(Point start, Point end, Color color, int thickness) {
        this(start, end, color, color, thickness);  // Obě barvy stejné
    }

    // Konstruktor pro úsečky s interpolaci barev
    public Line(Point start, Point end, Color startColor, Color endColor, int thickness) {
        this.start = start;
        this.end = end;
        this.startColor = startColor;     // Uložit startColor
        this.endColor = endColor;         // Uložit endColor
        this.thickness = thickness;
    }

    public Point getStart() { return start; }
    public Point getEnd() { return end; }
    public Color getColor() { return startColor; }  // Vrať startColor pro kompatibilitu
    public Color getStartColor() { return startColor; }  // Vrať uloženou startColor
    public Color getEndColor() { return endColor; }      // Vrať uloženou endColor
    public int getThickness() { return thickness; }
    
    public void setStart(Point start) { this.start = start; }
    public void setEnd(Point end) { this.end = end; }
    public void setColor(Color color) {
        this.startColor = color;
        this.endColor = color;
    }
    public void setThickness(int thickness) { this.thickness = thickness; }
}