package aufgaben.shape;

import static java.lang.Math.*;

public final class Line extends Shape {
    private final Point endPosition;

    public Line(final Point position, final Point endPosition) {
        super(position);
        this.endPosition = endPosition;
    }

    public Point getEndPosition() {
        return endPosition;
    }

    @Override
    public double getPerimeter() {
        return sqrt(pow(getPosition().getX() - endPosition.getX(), 2) + pow(getPosition().getY() - endPosition.getY(), 2));
    }

    @Override
    public double getArea() {
        return 0;
    }
}
