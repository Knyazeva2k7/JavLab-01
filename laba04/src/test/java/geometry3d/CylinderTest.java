package geometry3d;

import geometry2d.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import exceptions.InvalidParameterExceptions;

public class CylinderTest {
    private static final double EPSILON = 1e-9;
    
    @Test 
    public void volumeOfCircleCylinderWithHeightSix(){
        Cylinder cyl = new Cylinder(new Circle(5.0), 6.0);
        double actual = cyl.volume();
        assertEquals(471.238898038, actual, EPSILON);
    }

    @Test
        public void volumeOfRectangleCylinderWithHeightSix(){
        Cylinder cyl = new Cylinder(new Rectangle(3.0, 4.0), 6.0);
        double actual = cyl.volume();
        assertEquals(72.0, actual, EPSILON);
    }

    @Test
    public void nullBaseOfCylinder(){
        assertThrows(InvalidParameterExceptions.class, () -> {
            new Cylinder((Figure)null, 10.0);
        });
    }

    @Test 
    public void testCylinderGetters(){
        Circle cir = new Circle(3.0);
        Cylinder cyl= new Cylinder(cir, 7.5);

        assertEquals(7.5, cyl.getH(), EPSILON);
        assertSame(cir, cyl.getBase()); 
    }

    @Test 
    public void testCylinderToString(){
        Cylinder cyl = new Cylinder(new Circle(5.0), 4.0);
        String res = cyl.toString();

        assertTrue(res.contains("Cylinder"), "String must have type of figure");
        assertTrue(res.contains("4.0"), "String must have height");

    }

}
