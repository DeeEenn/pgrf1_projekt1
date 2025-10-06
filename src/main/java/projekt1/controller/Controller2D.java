package projekt1.controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import projekt1.model.Line;
import projekt1.model.Point;
import projekt1.model.Polygon;
import projekt1.rasterizer.FilledLineRasterizer;
import projekt1.rasterizer.Raster;
import projekt1.view.Panel;

public class Controller2D {
    private final Panel panel;
    private final Raster raster;
    private final FilledLineRasterizer lineRasterizer;

    private Point startPoint;
    private boolean isDrawing = false;
    private int lineThickness = 1;
    private boolean interpolationMode = false;
    private boolean isVerticalHorizontalMode = false;
    private boolean polygonMode = false;
    private boolean isEditingPoint = false;
    private Point editingPoint = null;
    private int editingPointIndex = -1;

    private Polygon polygon = new Polygon();
    private final List<Line> lines = new ArrayList<>();

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();
        this.lineRasterizer = new FilledLineRasterizer(raster);

        raster.clear();
        panel.repaint();
        
        initListeners();
    }

    private void initListeners() {
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Prvni bod usecky je na: (" + e.getX() + ", " + e.getY() + ")");
                    startPoint = new Point(e.getX(), e.getY(), Color.WHITE);
                    isDrawing = true;
                } else if (e.getButton() == MouseEvent.BUTTON3 && polygonMode) {
                    Point closest = polygon.findClosestPoint(e.getX(), e.getY());
                    if (closest != null) {
                        double distance = polygon.getDistance(e.getX(), e.getY(), closest);
                        if (distance < 15) { // Pokud je kliknuti ve vzdalenosti maximalne 15 pixelu, tak se zvoli bod.
                            isEditingPoint = true;
                            editingPoint = closest;
                            editingPointIndex = polygon.getPointIndex(closest);
                        }
                    }
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDrawing && e.getButton() == MouseEvent.BUTTON1) {
                    Point endPoint = getAlignedPoint(startPoint, e.getX(), e.getY());

                    if(polygonMode){
                        polygon.addPoint(endPoint);
                    } else {
                        Line finalLine;
                        if(interpolationMode){
                            finalLine = new Line(startPoint, endPoint, Color.BLUE, Color.RED, lineThickness);
                        } else {
                            finalLine = new Line(startPoint, endPoint, Color.WHITE, lineThickness);
                        }
                        lines.add(finalLine);
                    }

                    redrawAll();
                    isDrawing = false;
                } else if (isEditingPoint && e.getButton() == MouseEvent.BUTTON3) {
                    isEditingPoint = false;
                    editingPoint = null;
                    editingPointIndex = -1;
                }
            }
            
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    redrawAll();

                    Point currentPoint = getAlignedPoint(startPoint, e.getX(), e.getY());
                    Line previewLine;

                    if(interpolationMode){
                        previewLine = new Line(startPoint, currentPoint, Color.BLUE, Color.RED, lineThickness);
                    } else {
                        previewLine = new Line(startPoint, currentPoint, Color.WHITE, lineThickness);
                    }
                    lineRasterizer.rasterize(previewLine);
                    panel.repaint();
                } else if (isEditingPoint && editingPoint != null) {
                    Point newPosition = getAlignedPoint(editingPoint, e.getX(), e.getY());
                    polygon.movePoint(editingPointIndex, newPosition.getX(), newPosition.getY());
                    redrawAll();
                }
            }
            
            @Override public void mouseMoved(MouseEvent e) {}
        });
        
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    clearAll();
                } else if (e.getKeyCode() == KeyEvent.VK_T) {
                    lineThickness = (lineThickness == 1) ? 3 : 1;
                } else if (e.getKeyCode() == KeyEvent.VK_I) {
                    interpolationMode = !interpolationMode;
                } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    isVerticalHorizontalMode = !isVerticalHorizontalMode;
                } else if (e.getKeyCode() == KeyEvent.VK_P) {
                    polygonMode = !polygonMode;
                }
            }
 
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyTyped(KeyEvent e) {}
        });
        
        panel.setFocusable(true);
        panel.requestFocus();
    }

    public Point getAlignedPoint(Point startPoint, int mouseX, int mouseY) {
        if (!isVerticalHorizontalMode) {
            return new Point(mouseX, mouseY, Color.WHITE);
        }

        int dx = Math.abs(mouseX - startPoint.getX());
        int dy = Math.abs(mouseY - startPoint.getY());

        if(dx > dy){
            return new Point(mouseX, startPoint.getY(), Color.WHITE); // Horizontalni
        } else if (dx < dy) {
            return new Point(startPoint.getX(), mouseY, Color.WHITE); // Vertikalni
        } else if (dx == dy){
            return new Point(mouseX, mouseY, Color.WHITE); // Diagonalni
        }
        return null;
    }

    private void redrawAll() {
        raster.clear();
        
        for (Line line : lines) {
            lineRasterizer.rasterize(line);
        }

        for (Line line : polygon.getLines()) {
            lineRasterizer.rasterize(line);
        }
        
        panel.repaint();
    }
    
    private void clearAll() {
        lines.clear();  
        polygon.clear();
        raster.clear(); 
        System.out.println("Platno bylo vymazano.");
        panel.repaint();
    }
}