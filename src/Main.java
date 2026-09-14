public class Main {
    public static void main(String[] args){
        System.out.println("1 zadanie" );
        WholeNumber number = new WholeNumber();

        System.out.println("1)" );
        number.MinMax();

        System.out.println("2)" );
        number.Summ();

        System.out.println("3)" );
        number.LongInt();

        System.out.println("4)" );
        number.Division();

        System.out.println("5)" );
        number.LongToInt();

        System.out.println("6)" );
        number.CharArithmetic();

        System.out.println("7)" );
        int a = Integer.MAX_VALUE;
        int b = 676767;
        number.SumInt(a, b);

        int c = 200;
        int d = 357;
        number.SumInt(c, d);

        int e = Integer.MIN_VALUE;
        int f = -9999;
        number.SumInt(e, f);
    }
    
}
