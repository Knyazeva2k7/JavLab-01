package geometry2d;

import exceptions.Exceptions;

public class Circle implements Figure{
    private final double r;

    public Circle(double r){
        if (r > 0){
            this.r = r;
        }else {
            throw new Exceptions ("The radius of the circle must be greater than 0");
        }
    }

    @Override 
    public double area(){
        return Math.PI * r * r;
    }

    @Override 
    public double perimeter(){
        return 2 * Math.PI * r;
    }

    @Override 
    public String toString(){
        return "Circle with radius " + r + 
        "\nArea: " + area() + 
        "\nPerimeter: " + perimeter() + 
        "\n--------------\n";
    }

}
