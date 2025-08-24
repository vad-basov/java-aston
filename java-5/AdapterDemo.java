class LegacyRectangle {
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("LegacyRectangle: draw(" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ")");
    }
}

interface Shape {
    void draw(int x, int y, int width, int height);
}

class RectangleAdapter implements Shape {
    private final LegacyRectangle legacyRectangle;

    public RectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public void draw(int x, int y, int width, int height) {
        int x2 = x + width;
        int y2 = y + height;
        legacyRectangle.draw(x, y, x2, y2);
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        LegacyRectangle legacyRectangle = new LegacyRectangle();
        Shape shape = new RectangleAdapter(legacyRectangle);

        shape.draw(10, 20, 30, 40);
    }
}