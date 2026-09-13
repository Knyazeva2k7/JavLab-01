public class TextTasks {
    public static String reverse(String input){
        if (input == null){
            System.out.println("Error");
            return input;
        }
        String res = "";
        for (int i = input.length() - 1; i >= 0; i--){
            res += input.charAt(i);
        }
        return res;
    }
}
