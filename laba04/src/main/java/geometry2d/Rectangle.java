package geometry2d;
import java.io.Serializable;

import exceptions.InvalidParameterExceptions;

import java.io.Serial;

public class Rectangle implements Figure, Serializable{
    @Serial
    private static final long serialVersionUID = 52;

    private final double a;
    private final double b;

    public Rectangle(double a, double b){
        if (a <= 0 || b <= 0 ||  
            Double.isInfinite(a) ||  Double.isInfinite(b) || 
            Double.isNaN(a) || Double.isNaN(b)){
            throw new InvalidParameterExceptions ("The sides of the rectangle must be greater than 0");
        }
        this.a = a;
        this.b = b;
    }
    @Override 
    public double area(){
        return a * b;
    }
    @Override 
    public double perimeter(){
        return (a + b) * 2;
    }
    
    public double getA(){
        return a;
    }
    
    public double getB(){
        return b;
    }

    @Override 
    public String toString(){
        return "Rectangle with sides " + a + ", " + b +
         "\nArea: " + area() +
          "\nPerimeter: " + perimeter() + 
          "\n--------------\n";
    }

    @Override 
    public String toStringNew(){
        return "Rectangle " + a + " " + b;
    }
}
