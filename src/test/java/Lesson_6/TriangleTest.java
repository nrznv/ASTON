package Lesson_6;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleTest {

    @Test
    public void testValidTriangle() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals(t.getArea(), 6.0, 0.001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidTriangle() {
        new Triangle(1, 2, 5);
    }
}