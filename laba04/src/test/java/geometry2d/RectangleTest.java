package geometry2d;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import exceptions.InvalidParameterExceptions;

public class RectangleTest {
    private static final double EPSILON = 1e-9;

    @Test 
    public void areaOFThreeByFourrectangleIsTwelve(){
        Rectangle rec = new Rectangle(3.0, 4.0);
        double actual = rec.area();
        assertEquals(12.0, actual, EPSILON);
    }

    @Test 
    public void perimeterOfThreeByFourRectangleIsFOurteen(){
        Rectangle rec = new Rectangle(3.0, 4.0);
        double actual = rec.perimeter();
        assertEquals(14.0, actual, EPSILON);
    }

    @Test 
    public void areaOfSquare(){
        Rectangle rec = new Rectangle(3.0, 3.0);
        double actual = rec.area();
        assertEquals(9.0, actual, EPSILON);
    }
    @Test 
    public void perimeterOfSquare(){
        Rectangle rec = new Rectangle(3.0, 3.0);
        double actual = rec.perimeter();
        assertEquals(12.0, actual, EPSILON);
    }

    @Test 
    public void incorrectParametersOfRectangle(){
        assertThrows(InvalidParameterExceptions.class, () -> {
            new Rectangle(-3.0, -4.0);
        });

        assertThrows(InvalidParameterExceptions.class, () -> {
            new Rectangle(Double.NaN, 4.0);
        });

        assertThrows(InvalidParameterExceptions.class, () -> {
            new Rectangle(Double.POSITIVE_INFINITY, 4.0);
        });

        assertThrows(InvalidParameterExceptions.class, () -> {
            new Rectangle(4.0, Double.NEGATIVE_INFINITY);
        });
    }

    @Test 
    public void testRectangleToString(){
        Rectangle rec = new Rectangle(3.0, 4.0);
        String res = rec.toString();

        assertTrue(res.contains("Rectangle"), "String must have type of figure");
        assertTrue(res.contains("3.0"), "String must have first side");
        assertTrue(res.contains("4.0"), "String must have second side");
    }
}
