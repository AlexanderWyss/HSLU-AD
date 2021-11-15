package a.a1;

import java.util.Comparator;

public class TrianglePerimeterComparator implements Comparator<Triangle> {
    @Override
    public int compare(Triangle a, Triangle b) {
        return Double.compare(a.getPerimeter(), b.getPerimeter());
    }
}
