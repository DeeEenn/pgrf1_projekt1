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
import projekt1.rasterizer.FilledLineRasterizer;
import projekt1.rasterizer.Raster;
import projekt1.view.Panel;

public class Controller2D {
    private final Panel panel;
    private final Raster raster;
    private final FilledLineRasterizer lineRasterizer;

    private Point startPoint;
    private boolean isDrawing = false;
    
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
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDrawing && e.getButton() == MouseEvent.BUTTON1) {
                    Point endPoint = new Point(e.getX(), e.getY(), Color.WHITE);
                    Line finalLine = new Line(startPoint, endPoint, Color.WHITE);
                    System.out.println("Druhy bod usecky je na: (" + e.getX() + ", " + e.getY() + ")");
                    System.out.println("Usecka byla nakreslena na pozici: " + startPoint.getX() + ", " + startPoint.getY() + " -> " + endPoint.getX() + ", " + endPoint.getY());
                    lines.add(finalLine);

                    
                    redrawAll();
                    
                    isDrawing = false;
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
                    
                    Point currentPoint = new Point(e.getX(), e.getY(), Color.WHITE);
                    Line previewLine = new Line(startPoint, currentPoint, Color.WHITE);
                    lineRasterizer.rasterize(previewLine);
                    panel.repaint();
                }
            }
            
            @Override public void mouseMoved(MouseEvent e) {}
        });
        
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    clearAll();
                }
            }
            
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyTyped(KeyEvent e) {}
        });
        
        panel.setFocusable(true);
        panel.requestFocus();
    }
    
    private void redrawAll() {
        raster.clear();
        
        for (Line line : lines) {
            lineRasterizer.rasterize(line);
        }
        
        panel.repaint();
    }
    
    private void clearAll() {
        lines.clear();  
        raster.clear(); 
        System.out.println("Platno bylo vymazano.");
        panel.repaint();
    }
}