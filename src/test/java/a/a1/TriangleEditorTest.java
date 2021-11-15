package a.a1;

import aufgaben.shape.Point;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TriangleEditorTest {
    @Test
    void naturalSort() {
        Triangle[] triangles = new Triangle[]{ //
                new Triangle(new Point(10, 15), new Point(5, 7), new Point(20, 25)), //
                new Triangle(new Point(12, 15), new Point(7, 7), new Point(25, 25)), //
                new Triangle(new Point(2, 4), new Point(30, 20), new Point(0, 5)), //
                new Triangle(new Point(7, 8), new Point(77, 77), new Point(58, 98)) //
        };

        Arrays.sort(triangles); // stabil/mergesort -> siehe java doc

        double previousArea = 0;
        for (Triangle triangle : triangles) {
            double area = triangle.getArea();
            assertTrue(area > previousArea);
            previousArea = area;
        }
    }

    @Test
    void sortSpecific() {
        Triangle[] triangles = new Triangle[]{ //
                new Triangle(new Point(10, 15), new Point(5, 7), new Point(20, 25)), //
                new Triangle(new Point(12, 15), new Point(7, 7), new Point(25, 25)), //
                new Triangle(new Point(2, 4), new Point(30, 20), new Point(0, 5)), //
                new Triangle(new Point(7, 8), new Point(77, 77), new Point(58, 98)) //
        };

        Arrays.sort(triangles, new TrianglePerimeterComparator()); // stabil/mergesort -> siehe java doc

        double previousPerimeter = 0;
        for (Triangle triangle : triangles) {
            double perimeter = triangle.getPerimeter();
            assertTrue(perimeter > previousPerimeter);
            previousPerimeter = perimeter;
        }
    }
}