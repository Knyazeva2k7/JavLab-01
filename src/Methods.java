public class Methods {
    public static void run(){
        print(3);
        print(1.7);
        print("java");
        print(new int[]{1, 2, 3, 4, 5});
        Varargs(0);
        Varargs(new int[]{1, 6, 8, 67});
        PowerOfNumR(2, 0);
        PowerOfNumR(5, 1);
        PowerOfNumR(2, 10);
        PowerOfNumI(2, 0);
        PowerOfNumI(5, 1);
        PowerOfNumI(2, 10);
    }
    private static void print(int n){
        System.out.println("Int: " + n);
    }
    private static void print(String n){
        System.out.println("String: " + n);
    }
    private static void print(double n){
        System.out.println("Double: " + n);
    }
    private static void print(int[] num){
        System.out.println("Array: ");
        for (int n: num) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
    
    private static int Varargs(int... varargs){
        System.out.println("exercise 7.2---------------------");

        int res  = 0;
        for (int n : varargs){
            res += n;
        }
        return res;
    }
    //способ с циклом будет быстрее так как в нем мы используем обычный цикл и две переменные, 
    // а в первом способе выделяется память под каждую новую рекурсию, соответственно лимит на степень будет меньше
    private static int PowerOfNumR(int num, int pow){
        System.out.println("exercise 7.3---------------------");

        if (pow < 0){
            System.out.println("Error. Power must be greater than 0");
            return 0;
        }
        if (pow == 0){
            return 1;
        }
        return PowerOfNumR(num, pow - 1) * num;
    }
    private static int PowerOfNumI(int num, int pow){
    System.out.println("exercise 7.3---------------------");

        if (pow < 0){
            System.out.println("Error. Power must be greater than 0");
            return 0;
        }
        int res = 1;

        for (int i = 0; i < pow; i++){
            res *= num;
        }
        return res;
    } 
}
