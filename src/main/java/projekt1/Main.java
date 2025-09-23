package projekt1;
import projekt1.controller.Controller2D;
import projekt1.view.Frame;


public class Main {
    public static void main(String[] args) {
        Frame window = new Frame(1200, 800);
        new Controller2D(window.getPanel());
    }
}