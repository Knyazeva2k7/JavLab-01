import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;


public class MapInverter {
    public static Map<String, Collection<Integer>> invert(Map<Integer, String> map){
        Map<String, Collection<Integer>> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry: map.entrySet()){
            Integer num = entry.getKey();
            String word = entry.getValue();

            if (!res.containsKey(word)){
                Collection<Integer> list = new ArrayList<>();
                list.add(num);
                res.put(word, list);
            }
            else {
                Collection<Integer> list = res.get(word);
                list.add(num);
            }
        }
        return res;
    }
}
