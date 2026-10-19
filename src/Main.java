import java.util.Scanner;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import geometry3d.Cylinder;
import storage.TextFigureStorage;
import exceptions.*;
import java.util.List;
import java.io.IOException;



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
            case 2 -> {
                 System.out.println("exersize 4------------------");
                 TextFigureStorage storage = new TextFigureStorage();
                List<Figure> figures = List.of(new Circle(5.6), 
                    new Rectangle(4.7, 7.0), 
                    new Circle(2.0));

            java.nio.file.Path filePath = java.nio.file.Paths.get("figures.txt");

            try {

                storage.write(filePath, figures);
                System.out.println("The figures have been recorded");

                java.nio.file.Files.write(
                    filePath, 
                    List.of("Circle gav", "Rectangle 2.0 3.0"), 
                    java.nio.file.StandardOpenOption.APPEND
                );

                System.out.println("Reading file with defectes");
                List<Figure> loadedFigures = storage.read(filePath);
            
                System.out.println("loaded figures in the list");
                for (Figure f : loadedFigures) {
                    System.out.println(f); 
                }

            } catch (IOException e) {
                System.err.println("Error input-output: " + e.getMessage());
            }
            catch (CustomFormatExceptions e) {
                System.err.println("Format error during reading: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Other error during reading: " + e.getMessage());
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