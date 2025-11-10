import Lesson_6.Triangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testArea() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals(6.0, t.getArea(), 0.001);
    }
}