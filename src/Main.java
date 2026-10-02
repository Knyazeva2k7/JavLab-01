import java.util.Scanner;

public class Main{
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
            if (exerciseNumber < 0 || exerciseNumber > 5|| exerciseNumber == 2){
                System.out.println("Error. Out of range");
                continue;
            }

            switch (exerciseNumber) {
            case 0 -> {
                System.out.println("Exit");
            }
            case 1 -> {
                CollectionsHandler.run();
            }
            case 3-> {
                
            }
            
            
            default -> {
                System.out.println("Error");
            }
        }    
    } while (exerciseNumber != 0);
    scanner.close();
    }
}


    
    

