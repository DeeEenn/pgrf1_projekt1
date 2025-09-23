package projekt1.model;

import java.awt.Color;

public class Point {
    private int x, y;
    private Color color;

    public Point(int x, int y) {
        this(x, y, Color.WHITE);
    }

    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Color getColor() { return color; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setColor(Color color) { this.color = color; }
}
