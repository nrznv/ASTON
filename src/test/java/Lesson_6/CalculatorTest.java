package Lesson_6;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    public void testFactorial() {
        assertEquals(calc.factorial(5), 120L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() {
        calc.factorial(-1);
    }

    @Test
    public void testAdd() {
        assertEquals(calc.add(3, 5), 8);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        calc.divide(10, 0);
    }
}