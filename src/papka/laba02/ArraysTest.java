package papka.laba02;
import java.util.Arrays;

public class ArraysTest {
    public static void run(){
        MkArray(7);
        MinMaxFind();
        Sort();
        Comparison();
    }
    private static void MkArray(int n){
        System.out.println("exercise 5.1---------------------");

        int[] arr = new int[n];

        for(int i = 0; i < arr.length; i++){
            arr[i]= i+1;
        }

        System.out.println("Your array: ");

        for (int num : arr){
            System.out.print(num + " ");
        }
        System.out.print("\n");
    }
    private static void MinMaxFind(){
        System.out.println("exercise 5.2---------------------");

        int[] arr = {-1, 2, 3, 56, 67, 0};
        int max = 0;
        int min = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            } else if (arr[i] < min){
                min = arr[i];
            }
        }
        System.out.println("Max: " + max + "\nMin: " + min);
    }

    private static void Sort(){
        System.out.println("exercise 5.3---------------------");
        //вставка
         int[] arr = {-1, 2, 3, 4, 67, 0};

         for (int i = 0; i < arr.length; i++){
            int part = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > part){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = part;
         }
         for (int num : arr){
            System.out.print(num + " ");
        }
        System.out.print("\n");
    }

    private static void Comparison(){
        System.out.println("exercise 5.3---------------------");

        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {7, 8, 9, 10, 11, 12};
        int[] arr3 = {1, 2, 3, 4, 5, 6};
        int[] arr4 = arr1;

        System.out.println("arr1 == arr2: " + (arr1 == arr2));//сравнивает ссылки
        System.out.println("arr1 == arr4: " + (arr1 == arr4));
        System.out.println("arr1.equals(arr2): " + (arr1.equals(arr2)));//сравнивает ссылки
        System.out.println("arr1.equals(arr1): " + (arr1.equals(arr1)));
        System.out.println("Arrays.equals(arr1, arr2): " + (Arrays.equals(arr1, arr2)));
        System.out.println("Arrays.equals(arr1, arr3): " + (Arrays.equals(arr1, arr3)));//сравнивает содержимое массива и их количество

    }


}
