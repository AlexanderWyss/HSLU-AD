package aufgaben.shape;

public abstract class Shape {
    private final Point position;

    protected Shape(final Point position) {
        this.position = position;
    }

    protected Shape(final int x, final int y) {
        this(new Point(x, y));
    }

    public Point getPosition() {
        return position;
    }

    public abstract double getPerimeter();

    public abstract double getArea();
}