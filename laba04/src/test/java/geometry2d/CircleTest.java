package geometry2d;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import exceptions.InvalidParameterExceptions;

public class CircleTest {
    private static final double EPSILON = 1e-9;

    @Test
    public void areaOfFiveRadiusCircle(){
        Circle cir = new Circle(5.0);
        double actual = cir.area();
        assertEquals(78.53981633974483, actual, EPSILON);
    }

    @Test 
    public void perimeterOfTworagiusCircle(){
        Circle cir = new Circle(2.0);
        double actual = cir.perimeter();
        assertEquals(12.566370614359172, actual, EPSILON);
    }

    @Test 
    public void incorrectParametersOfCircle(){
        assertThrows(InvalidParameterExceptions.class, ()-> {
            new Circle(-7.0);
        });

        assertThrows(InvalidParameterExceptions.class, () -> {
            new Circle(Double.NaN);
        });

        assertThrows(InvalidParameterExceptions.class, () -> {
            new Circle(Double.POSITIVE_INFINITY);
        });

        assertThrows(InvalidParameterExceptions.class, () -> {
            new Circle(Double.NEGATIVE_INFINITY);
        });
        }

    @Test 
    public void testCircleToString(){
        Circle cir = new Circle(5.0);
        String res = cir.toString();

        assertTrue(res.contains("Circle"), "String must have type of figure");
        assertTrue(res.contains("5.0"), "String must have radius");
    }
}
