package geometry3d;
import exceptions.InvalidParameterExceptions;
import geometry2d.Figure;

public class Cylinder {
    private final double h;
    private final Figure base;

    public Cylinder(Figure base, double h){
        if (h <= 0){
            throw new InvalidParameterExceptions("The height of the cylinder must be greater than 0");
        }

        if (base == null){
            throw new InvalidParameterExceptions("Base is null");
        } 

        this.base = base;
        this.h = h;
    }

    public double volume(){
        return base.area() * h;
    }

    public double getH(){
        return h;
    }

    public Figure getBase(){
        return base;
    }

    @Override 
    public String toString(){
        return "Cylinder with height " + h + 
        "\nVolume: " + volume() +  
        "\nBase type: " + base.toString() +
         "\n--------------\n";
    }
}
