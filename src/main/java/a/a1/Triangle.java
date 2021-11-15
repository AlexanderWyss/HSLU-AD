package a.a1;

import aufgaben.shape.Point;
import aufgaben.shape.Shape;
import aufgaben.shape.Line;

public class Triangle extends Shape implements Comparable<Triangle> {
    private final Point cornerB;
    private final Point cornerC;

    public Triangle(Point cornerA, Point cornerB, Point cornerC) {
        super(cornerA);
        this.cornerB = cornerB;
        this.cornerC = cornerC;
    }

    public Point getCornerA() {
        return getPosition();
    }

    public Line getLineA() {
        return new Line(getCornerB(), getCornerC());
    }

    public Point getCornerB() {
        return cornerB;
    }

    public Line getLineB() {
        return new Line(getCornerA(), getCornerC());
    }

    public Point getCornerC() {
        return cornerC;
    }

    public Line getLineC() {
        return new Line(getCornerA(), getCornerB());
    }

    @Override
    public double getPerimeter() {
        return getLineA().getPerimeter() + getLineB().getPerimeter() + getLineC().getPerimeter();
    }

    @Override
    public double getArea() {
        return heronFormula();
    }

    private double heronFormula() {
        double s = getPerimeter() / 2;
        double a = getLineA().getPerimeter();
        double b = getLineB().getPerimeter();
        double c = getLineC().getPerimeter();
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public int compareTo(Triangle triangle) {
        return Double.compare(getArea(), triangle.getArea());
    }
}
