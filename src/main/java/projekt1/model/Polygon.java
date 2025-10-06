package projekt1.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Point> points;

    public Polygon() {
        points = new ArrayList<>();
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y, Color.WHITE));
    }

    public void addPoint(Point point){
        points.add(point);
    }

    public void clear() {
        points.clear();
    }

    public List<Point> getPoints() { return points; }
    public int size() { return points.size(); }
    public boolean isEmpty() { return points.isEmpty(); }

    public List<Line> getLines() {
        List<Line> lines = new ArrayList<>();

        if (points.size() < 2) {
            return lines; // Nemame dost bodu na polygon
        }

        for(int i = 0; i < points.size(); i++) {
            Point start = points.get(i);
            Point end = points.get((i + 1) % points.size()); // Spojeni posledniho s prvnim
            lines.add(new Line(start, end, Color.WHITE, 1));
        }

        return lines;
    }

    public Point findClosestPoint(int mouseX, int mouseY){
        if (points.isEmpty()) return null;

        Point closest = points.get(0);
        double shortest = getDistance(mouseX, mouseY, closest);

        for (Point point : points) {
            double distance = getDistance(mouseX, mouseY, point);
            if (distance < shortest) {
                shortest = distance;
                closest = point;
            }
        }

        return closest;
    }

    public void movePoint(int index, int newX, int newY) {
        if (index >= 0 && index < points.size()){
            Point point = points.get(index);
            point.setX(newX);
            point.setY(newY);
        }
    }

    public int getPointIndex(Point point) {
        return points.indexOf(point);
    }

    public double getDistance(int x, int y, Point point) {
        int dx = point.getX() - x;
        int dy = point.getY() - y;
        return Math.sqrt(dx * dx + dy * dy);
    }


}
