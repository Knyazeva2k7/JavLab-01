import java.util.HashMap;
import java.util.Map;

public class WordFrequency {
    public static void WordFreq(String text){
        String[] words = text.toLowerCase().split(" ");
        Map<String, Integer> map = new HashMap<>(); //умеет находить значения по ключу 
        for (String word : words){

            if (!map.containsKey(word)){
                map.put(word, 1);
            }
            else {
                int currentCount = map.get(word);
                map.put(word, currentCount + 1);
            }
        }
        System.out.println(map);
    }
}
