public class WholeNumber{
    public void MinMax(){
        System.out.println("Byte: " + Byte.MIN_VALUE + ", " + Byte.MAX_VALUE); //т.к. первый бит всегда 0, итого получается положительных чисел всего 2^7-1 
        // (01111111 (максимальное положит. число) = 127). а отрицательных 2^7 (10000000 = 128).
        System.out.println("Short: " + Short.MIN_VALUE + ", " + Short.MAX_VALUE); //занимает 16 бит, из которых 1 - под знак, остальные 15 под число. 
        // отсюда 2^15-1 положительных, 2^15 отрицательных.
        System.out.println("Int: " + Integer.MIN_VALUE + ", " + Integer.MAX_VALUE); //занимает 32 бита, из которых 1 - под знак, остальные 31 под число. 
        // отсюда 2^31-1 положительны,2^31 отрицательных 
        System.out.println("Long: " + Long.MIN_VALUE + ", " + Long.MAX_VALUE); //занимает 64 бита, из которых 1 - под знак, остальные 63 под число. 
        // отсюда 2^63-1 положительны,2^63 отрицательных 
    }

    public void Summ(){
        int res = (int) 1 + Integer.MAX_VALUE;
        System.out.println(res);//число ушло в минус, так как произошло превышение максимального числа
    }

    public void LongInt(){
        long resLong = (long) Integer.MAX_VALUE * 2;
        int resInt = Integer.MAX_VALUE * 2;
        System.out.println("Long - " + resLong + "\nInt - " + resInt);//тип инт не может физически сохранить в себе такое большое число
    }

    public void Division(){
        double a = 5.0 / 2.0; //деление целочисленное
        double b = -5.0 / 2.0;
        double c = 5.0 % 2.0; //остаток от деления
        double d = -5.0 % 2.0;// остаток отрицательный, потому что делимое отрицательно
        System.out.println("/ "+ a + "\n-/ " + b + "\n% " + c + "\n-% " + d);
    }

    public void LongToInt(){
        long bigNum = (long) Integer.MAX_VALUE + 1000000;//правильное значение
        int a = (int) bigNum;//не поместился миллиончик сверху ушли в минус
        System.out.println("Long: " + bigNum + "\nRezult in int: " + a);
    }

    public void CharArithmetic(){
        char letter = 'A';
        char nextLetter = (char) (letter + 1);
        System.out.println("Letter: " + letter + "\nNext: " + nextLetter);

        char char1 = '1';//49
        char char2 = '4';//52

        int summInt = char1 + char2;
        char summChar = (char) (char1 + char2);

        System.out.println("Sum of " + char1 + ", " + char2);
        System.out.println("Int sum: " + summInt);
        System.out.println("Char sum: " + summChar);
    }

    public boolean SumInt(int a, int b){
        int summ = a + b;
        if (a > 0 & b > 0 & summ < 0){
            System.out.println("Overflow -");
            return true; 
        } 

        if (a < 0 & b < 0 & summ >=0) {
            System.out.println("Overflow +");
            return true;
        }
        System.out.println("No Overflow");
        return false;
    }
}