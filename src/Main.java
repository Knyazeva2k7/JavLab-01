public class Main {

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Error");
            return;
        }

        int exerciseNumber = Integer.parseInt(args[0]);

        switch (exerciseNumber) {
            case 1 -> {
                new WholeNumber().run();
            }
            case 2-> {
                new Arithmetic().run();
            }
            case 3 -> {
                new BitwiseOperations().run();
            }
            case 4 -> {
                new WordProcessing().run();
            }
            
            default -> {
                System.out.println("Error");
            }
        }
    }
}        

