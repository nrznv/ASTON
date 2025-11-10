import Lesson_6.NumberComparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    private final NumberComparator comp = new NumberComparator();

    @Test
    void testCompare() {
        assertEquals(1, comp.compare(5, 3));
        assertEquals(-1, comp.compare(2, 7));
        assertEquals(0, comp.compare(4, 4));
    }
}