package geometry2d;
import java.io.Serializable;

import exceptions.InvalidParameterExceptions;

import java.io.Serial;

public class Circle implements Figure, Serializable{

    @Serial
    private static final long serialVersionUID = 67;
    

    private final double r;

    public Circle(double r){
        if (r <= 0 || Double.isInfinite(r)|| Double.isNaN(r)){
            throw new InvalidParameterExceptions ("The radius of the circle must be greater than 0");
        }
        this.r = r;
    }

    @Override 
    public double area(){
        return Math.PI * r * r;
    }

    @Override 
    public double perimeter(){
        return 2 * Math.PI * r;
    }

    public double getR(){
        return r;
    }
    
    @Override 
    public String toString(){
        return "Circle with radius " + r + 
        "\nArea: " + area() + 
        "\nPerimeter: " + perimeter() + 
        "\n--------------\n";
    }

    @Override 
    public String toStringNew(){
        return "Circle " + r;
    }

}
