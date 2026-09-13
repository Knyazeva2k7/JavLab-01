public class Palindromes {
    public static void palindrome(String input){
        //String input = scanner.nextLine();
        int left = 0;
        int right = input.length() - 1;
        boolean isPalindrome= true;

        while (left < right){
            char leftChar = input.charAt(left);
            char rightChar = input.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
                continue;
            }

            if (!Character.isLetterOrDigit(rightChar)) {
                right--;
                continue;
            }

            if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)){
                isPalindrome = false;
                break;
            }
            left++; right--;
        }
        System.out.println(isPalindrome);

    }
}
