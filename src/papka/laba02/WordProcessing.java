package papka.laba02;
public class WordProcessing {
    public static void run(){
        Palindrome("Do geese see God?");
        Palindrome("I'm a palindrom");
        ReverseOfWords("cat eat mouse");
        Counter("What det3rm1nes th3 fate 0f humanity in th1s world?");
        Ceasar("I love Java", 3);
        TheLongestWord("I completed the fourth assignment");
    }
    private static void Palindrome(String input){
        System.out.println("exercise 4.1---------------------");

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
    private static void ReverseOfWords(String input){
        System.out.println("exercise 4.2---------------------");

        System.out.println("Input: " + input);

        String res = "";
        String[] words = input.split(" ");
        
        for (int i = words.length - 1; i >= 0 ; i-- ){
            res += words[i];

            if (i > 0){
                res += " ";
            }
        }
        System.out.println(res);
    }

    private static void Counter(String input){
        System.out.println("exercise 4.3---------------------");

        int vowelsCount = 0;// гласные
        int consonantsCount = 0;// согласные
        int digitsCount = 0;     
        int spacesCount = 0; 

        System.out.println(input);

        input = input.toLowerCase();
        int len = input.length() - 1;
        for (int i = 0; i <= len; i++ ){
            char part = input.charAt(i);
            
            if (part == ' '){
                spacesCount ++;
            } 
            
            else if (Character.isDigit(part)){
                digitsCount++;
            } 
            
            else if (part >= 'a' && part <= 'z'){
                
                if (part == 'a' | part == 'e' | part == 'i' | part == 'o' | part == 'y' | part == 'u'){
                    vowelsCount++;
                } else{
                    consonantsCount++;
                }
            }

        }

        System.out.println("Vowels count: " + vowelsCount + 
        "\nConsonants count: " + consonantsCount + 
        "\nDigit count: " + digitsCount + 
        "\nSpaces count: " + spacesCount);
    }

    private static void Ceasar(String input, int k){
        System.out.println("exercise 4.4---------------------");

        String res = "";
        System.out.println(input);

        for(int i = 0; i < input.length()-1; i++){
            char part = input.charAt(i);

            if (part >= 'A' && part <= 'Z'){
                int ind = part - 'A';//
                int newInd = (ind + k) % 26; //циклит алфавит

                if (newInd < 0){
                    newInd +=26;
                }
                res += (char) ('A'+ newInd);
            }
            else if (part >= 'a' && part <= 'z'){
                int ind = part - 'a';//
                int newInd = (ind + k) % 26; //циклит алфавит

                if (newInd < 0){
                    newInd +=26;
                }
                res += (char) ('a'+ newInd);
            }
            else {
                res += part;
            }
        }
         System.out.println(res);
    }

    private static void TheLongestWord(String input){
        System.out.println("exercise 4.5---------------------");

        String longestWord = "";
        String[] words = input.split(" ");
        
        for (int i = 0; i < words.length; i++){
            if (words[i].length() > longestWord.length()){
                longestWord = words[i];
            }
        }
        System.out.println("Longest word: " + longestWord + "\nLong: " + longestWord.length());

    }
}
