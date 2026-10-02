import java.util.*;


public class CollectionsHandler{
    public static void run(){
        Integer[] myArray = CollectionsHandler.createArray(6);
        List<Integer> myList = CollectionsHandler.createList(myArray);
        CollectionsHandler.sortAndShuffle(myList);
        List<Integer> myList2 = new ArrayList<>(Arrays.asList(2, 47, 2, 68, 90, 91, 14, 68, 2));
        CollectionsHandler.listItems(myList2);
        CollectionsHandler.fromListToArray(myList);
        CollectionsHandler.countItems(myList2);

    }
    private static Integer[] createArray(int n){
        System.out.println("exercise 1.1-------------------\n");
    
        Random rand = new Random();

        Integer[] array = new Integer[n];

        for (int i = 0; i < n; i++){
            array[i] = rand.nextInt(101);
        }

        System.out.println("Array: " + Arrays.toString(array));
        return array;
    }

    private static List<Integer> createList(Integer[] array){
         System.out.println("exercise 1.2-------------------\n");

         List<Integer> list = new ArrayList<>(Arrays.asList(array));
         System.out.println("List: " + list);
         return list;
    }

    private static void sortAndShuffle(List<Integer> list){
        System.out.println("exercise 1.3-------------------\n");
        Collections.sort(list);
        System.out.println("Ascending sort: " + list);

        System.out.println("exercise 1.4-------------------\n");
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Reverse sort: " + list);

        System.out.println("exercise 1.5-------------------\n");
        Collections.shuffle(list);
        System.out.println("Shuffle list: " + list);

        System.out.println("exercise 1.6-------------------\n");
        Collections.rotate(list, 1);
        System.out.println("Shift by 1: " + list);
    }
    private static void listItems(List<Integer> list){
        List<Integer> uniqueList = new ArrayList<>();
        List<Integer> duplicateList = new ArrayList<>();

        System.out.println("exercise 1.7-------------------\n");

       for (int i = 0; i < list.size(); i++){
            int num = list.get(i);
            if (Collections.frequency(list, num)==1 && !uniqueList.contains(num)){
                uniqueList.add(num);   
            }
       }
        System.out.println("Unique elements: " + uniqueList);

        System.out.println("exercise 1.8-------------------\n");

       for (int i = 0; i < list.size(); i++){
            int num = list.get(i);
            if (Collections.frequency(list, num) > 1 && !duplicateList.contains(num)){
                duplicateList.add(num);   
            }
       }
        
        System.out.println("Duplicate elements: "  + duplicateList);
    }

    private static void fromListToArray(List<Integer> list){
        System.out.println("exercise 1.9-------------------\n");
        Integer[] array = new Integer[list.size()];
        for (int i = 0 ; i < list.size(); i++){
            array[i] = list.get(i);
        }
        System.out.println("Array: " + Arrays.toString(array));
    }
    private static void countItems(List<Integer> list){
        System.out.println("exercise 1.10-------------------\n");

        List<Integer> checkNum = new ArrayList<>();
        for (int i =0; i < list.size(); i++  ){
            int num = list.get(i);

            if (!checkNum.contains(num)){
                int count = 0;
                for (int j = 0; j < list.size(); j++){
                    if (list.get(j).equals(num)){
                        count++;
                    }
                }
                 System.out.println("Number " + num + " appears " + count + " times");
                checkNum.add(num);
                } 
        }
    }
    


    
}
