import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
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

            if (exerciseNumber < 0 || exerciseNumber > 7 || exerciseNumber == 6){
                System.out.println("Error. Out of range");
                continue;
            }

            switch (exerciseNumber) {
            case 0 -> {
                System.out.println("Exit");
            }
            case 1 -> {
                WholeNumber.run();
            }
            case 2-> {
                Arithmetic.run();
            }
            case 3 -> {
                BitwiseOperations.run();
            }
            case 4 -> {
                WordProcessing.run();
            }
            case 5 -> {
                ArraysTest.run();
            }
            case 7 -> {
                Methods.run();
            }
            
            default -> {
                System.out.println("Error");
            }
        }    
    } while (exerciseNumber != 0);
    scanner.close();
}  
}


