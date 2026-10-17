import java.util.Scanner;
import geometry2d.Circle;
import geometry2d.Rectangle;
import geometry3d.Cylinder;
import exceptions.Exceptions;


public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int exerciseNumber;
        do {
            System.out.println("Enter number of exercise: ");

            if (!scanner.hasNextInt()){
                System.out.println("Error. This is a letter");
                scanner.next();
                exerciseNumber = -1;
                continue;
            }
            exerciseNumber = scanner.nextInt();
            if (exerciseNumber < 0 || exerciseNumber > 5){
                System.out.println("Error. Out of range");
                continue;
            }
        
        
        switch (exerciseNumber){
            case 0 -> {
                System.out.println("Exit");
            }
            case 1 -> {
                System.out.println("exersize 1 and 2------------------");
                Circle cir = new Circle(1.2);
                System.out.println(cir);

                Rectangle rec = new Rectangle(3.7, 6.4);
                System.out.println(rec);

                Cylinder cyl_1 = new Cylinder(rec, 2.9);
                Cylinder cyl_2 = new Cylinder(cir, 7.3);
                System.out.println(cyl_1);
                System.out.println(cyl_2);

                try {
                    Circle cir_0 = new Circle(-8.9);
                } catch (Exception e){
                    System.out.println("Exception: " + e.getMessage());
                }
            }

            default -> {
                System.out.println("Error");
            }
        } 
        
    }while (exerciseNumber != 0);
    scanner.close();
    }
}