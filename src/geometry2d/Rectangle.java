package geometry2d;
import exceptions.Exceptions;

public class Rectangle implements Figure{
    private final double a;
    private final double b;

    public Rectangle(double a, double b){
        if (a > 0 && b > 0){
            this.a = a;
            this.b = b;
        }else{
            throw new Exceptions ("The sides of the rectangle must be greater than 0");
        }
    }
    @Override 
    public double area(){
        return a * b;
    }
    @Override 
    public double perimeter(){
        return (a + b) * 2;
    }

    @Override 
    public String toString(){
        return "Rectangle with sides " + a + ", " + b +
         "\nArea: " + area() +
          "\nPerimeter: " + perimeter() + 
          "\n--------------\n";
    }
}
