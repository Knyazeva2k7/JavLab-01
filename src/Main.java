import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the command: ");

        String command = scanner.next();

        switch (command) {
            case "fizzbuzz" -> FizzBuzz.fizzbuzz();
            case "reverse" -> {
                System.out.print("Enter a word: ");
                String input = scanner.next();
                String result = TextTasks.reverse(input);
                System.out.println(result);
            }
            case "quadratic" -> {
                System.out.println("Enter 3 real coefficients: ");
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                double c = scanner.nextDouble();
                QuadraticEquation.quadratic(a, b, c);
            }

            case "series" -> SeriesCalculator.series();

            case "palindrome" -> {
                scanner.nextLine();
                System.out.print("Enter palindrome: ");
                String input = scanner.nextLine();
                Palindromes.palindrome(input);
            }

            default -> System.out.print("fizzbuzz\n" +
                    "reverse <string>\n" +
                    "quadratic <a> <b> <c>\n" +
                    "series\n" +
                    "palindrome <string>");
        }
        scanner.close();

    }
}
