public class QuadraticEquation {
    public static void quadratic(double a, double b, double c){
        System.out.println("Your equation: ");
        System.out.println( a + "· x² + " + b +"· x + " + c + " = 0");

        double dis = (b * b) - (4 * a * c);

        if (dis > 0){
            System.out.println("Root 1: "+ (-b - Math.sqrt(dis)) / (2 * a));
            System.out.println("Root 2: "+(-b + Math.sqrt(dis)) / (2 * a));
        } else if (dis == 0){
            System.out.println("Root: " + (-b / (2 * a)));
        }
        else {
            System.out.println("There are no real roots");
        }
    }
}
