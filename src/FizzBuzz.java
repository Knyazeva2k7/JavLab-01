public class FizzBuzz {
    public static void fizzbuzz(){
        for (int i = 1; i < 501; i++) {
            String element = String.valueOf(i);

            if (i % 5 == 0){
                if (i % 7 == 0)
                    element = "fizzbuzz";
                else
                    element = "fizz";
            } else if (i % 7 == 0){
                element = "buzz";
            }
            System.out.println(element);
        }
    }

}
