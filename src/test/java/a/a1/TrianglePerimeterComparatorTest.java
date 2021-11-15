package a.a1;

import aufgaben.shape.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrianglePerimeterComparatorTest {
    // https://www.triangle-calculator.com/de/?what=vc&a=10&a1=15&3dd=3D&a2=&b=5&b1=7&b2=&c=20&c1=25&c2=&submit=Berechnen&3d=0
    @Test
    void compare_aPerimeterLowerThanB_returnMinus1() {
        Triangle triangleA = new Triangle(new Point(10, 15), new Point(5, 7), new Point(20, 25)); // 47.007
        Triangle triangleB = new Triangle(new Point(10, 15), new Point(5, 7), new Point(25, 25)); // 54.369
        assertEquals(-1, getComparator().compare(triangleA, triangleB));
    }

    @Test
    void compare_aPerimeterGreaterThanB_return1() {
        Triangle triangleA = new Triangle(new Point(10, 15), new Point(5, 7), new Point(20, 25)); // 47.007
        Triangle triangleB = new Triangle(new Point(10, 15), new Point(5, 7), new Point(10, 10)); // 20.265
        assertEquals(1, getComparator().compare(triangleA, triangleB));
    }

    @Test
    void compare_aPerimeterEqualB_return0() {
        Triangle triangleA = new Triangle(new Point(10, 15), new Point(5, 7), new Point(20, 25)); // 47.007
        Triangle triangleB = new Triangle(new Point(10, 15), new Point(5, 7), new Point(20, 25)); // 47.007
        assertEquals(0, getComparator().compare(triangleA, triangleB));
    }

    private TrianglePerimeterComparator getComparator() {
        return new TrianglePerimeterComparator();
    }
}