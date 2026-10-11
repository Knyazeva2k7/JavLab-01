import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;


public class Main{
    public static void main(String[] args){
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
            if (exerciseNumber < 0 || exerciseNumber > 5){
                System.out.println("Error. Out of range");
                continue;
            }

            switch (exerciseNumber) {
            case 0 -> {
                System.out.println("Exit");
            }
            case 1 -> {
                CollectionsHandler.run();
            }
            case 2-> {
                PrimesGeneratorTest.runTest();
            }
            case 3 -> {
                List<Human> arrHum = new ArrayList<>();
                arrHum.add(new Human("MIHAIL", "KLESHNEV", 20 ));
                arrHum.add(new Human("MARIA", "RESHETKA", 32 ));
                arrHum.add(new Human("DENIS", "PECHIGIN", 20));
                arrHum.add(new Human("IVAN", "ZOLO", 51 ));
                arrHum.add(new Human("IVAN", "IVANOV", 51 ));

                System.out.println("exercise 3.2-------------------\n");
                HashSet<Human> hashSet = new HashSet<>(arrHum);
                System.out.println(hashSet);

                System.out.println("exercise 3.3-------------------\n");
                LinkedHashSet<Human> linkedHashSet = new LinkedHashSet<>(arrHum);
                System.out.println(linkedHashSet);

                System.out.println("exercise 3.4-------------------\n");
                TreeSet<Human> treeSet = new TreeSet<>(arrHum);
                System.out.println(treeSet);

                System.out.println("exercise 3.5-------------------\n");
                TreeSet<Human> treeSetByLastName = new TreeSet<>(new HumanComparatorByLastName());
                treeSetByLastName.addAll(arrHum);
                System.out.println(treeSetByLastName);


               /*LinkedHashSet сохраняет порядок добавления, потому что внутри себя он связывает элементы цепочкой (двусвязным списком)
                TreeSet автоматически сортирует элементы. oн берет метод compareTo из Human. сначала он расставит всех по возрасту, а если возраст совпадет, он отсортирует их по фамилии.
                 HashSet ориентируется только на скорость. ему важен хеш-код, поэтому элементы распределяются по ячейкам памяти так, как удобно компьютеру.
                */ 
            }
            case 4 -> {
                WordFrequency.WordFreq("applE apple orAnge watermelon melon banana MONKEY monkey");
            }
            case 5 ->{
                Map<Integer, String> map = new HashMap<>();
                map.put(1, "one");
                map.put(2, "two");
                map.put(3, "three");
                map.put(4, "two");
                map.put(5, "one");
                map.put(6, "two");
                Map<String, Collection<Integer>> invertedMap = MapInverter.invert(map);
                System.out.println(invertedMap);

            }
            
            default -> {
                System.out.println("Error");
            }
        }    
    } while (exerciseNumber != 0);
    scanner.close();
    }
}


    
    

