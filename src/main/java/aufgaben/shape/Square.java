package aufgaben.shape;

public final class Square extends Shape {
    private int length;

    public Square(final int x, final int y, final int length) {
        super(x, y);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    @Override
    public double getPerimeter() {
        return length * 4;
    }

    @Override
    public double getArea() {
        return length * length;
    }
}
