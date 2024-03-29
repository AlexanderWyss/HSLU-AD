package aufgaben.shape;

public class Circle extends Shape {
    private int diameter;

    public Circle(final int x, final int y, final int diameter) {
        this(new Point(x, y), diameter);
    }

    public Circle(Point position, int diameter) {
        super(position);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public double getPerimeter() {
        return diameter * Math.PI;
    }

    @Override
    public double getArea() {
        return Math.pow(diameter / 2.0, 2) * Math.PI;
    }
}
