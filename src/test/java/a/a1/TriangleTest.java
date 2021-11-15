package a.a1;

import aufgaben.shape.Point;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    // https://www.triangle-calculator.com/de/?what=vc&a=10&a1=15&3dd=3D&a2=&b=5&b1=7&b2=&c=20&c1=25&c2=&submit=Berechnen&3d=0
    @Test
    void getLineA_correct() {
        assertEquals(23.431, testTriangle().getLineA().getPerimeter(), 0.001);
    }

    @Test
    void getLineB_correct() {
        assertEquals(14.142, testTriangle().getLineB().getPerimeter(), 0.001);
    }

    @Test
    void getLineC_correct() {
        assertEquals(9.434, testTriangle().getLineC().getPerimeter(), 0.001);
    }

    @Test
    void getPerimeter_correct() {
        assertEquals(47.007, testTriangle().getPerimeter(), 0.001);
    }

    @Test
    void getArea_correct() {
        assertEquals(15, testTriangle().getArea(), 0.001);
    }

    @Test
    void compareTo_thisAreaLowerThanOther_returnMinus1() {
        Triangle triangle = new Triangle(new Point(10, 15), new Point(5, 7), new Point(25, 25));
        assertEquals(-1, testTriangle().compareTo(triangle));
    }

    @Test
    void compareTo_thisAreaGreaterThanOther_return1() {
        Triangle triangle = new Triangle(new Point(10, 15), new Point(5, 7), new Point(10, 10));
        assertEquals(1, testTriangle().compareTo(triangle));
    }

    @Test
    void compareTo_thisAreaEqualOther_return0() {
        Triangle triangle = new Triangle(new Point(20, 25), new Point(10, 15), new Point(5, 7));
        assertEquals(0, testTriangle().compareTo(triangle));
    }

    @NotNull
    private Triangle testTriangle() {
        return new Triangle(new Point(10, 15), new Point(5, 7), new Point(20, 25));
    }
}