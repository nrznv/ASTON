package Lesson_6;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {

    private final NumberComparator comp = new NumberComparator();

    @Test
    public void testCompare() {
        assertEquals(comp.compare(5, 3), 1);
        assertEquals(comp.compare(2, 7), -1);
        assertEquals(comp.compare(4, 4), 0);
    }
}