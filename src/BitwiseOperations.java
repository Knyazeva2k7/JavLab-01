public class BitwiseOperations {
    public static void run(){
        Operators();
        Parity(11, 12);
        isPowerOfTwo(-1);
        isPowerOfTwo(3);
        isPowerOfTwo(8);
        unitCounter(011101);
        exchange(5, 11);
    }

    private static void Operators(){
        System.out.println("exercise 3.1---------------------");
        int a = 11;
        int c = -11;
        int b = 3;
        String binaryA = Integer.toBinaryString(a);
        String binaryB = Integer.toBinaryString(b);
        String binaryC = Integer.toBinaryString(c);


        System.out.println(binaryA + " & " + binaryB + ": " + (a & b));//побитовое и
        System.out.println(binaryA + " | " + binaryB + ": "  + (a | b));//побитовое или
        System.out.println(binaryA + " ^ " + binaryB + ": "  + (a ^ b));//XOR
        System.out.println(" ~ " + binaryA + ": "  + (~a));//инверсия
        System.out.println(binaryA + " << " + b + ": "  + (a << b));//сдвиг влево (уножение на 2^n. выведет 88)
        System.out.println(binaryA + " >> " + b + ": "  + (a >> b));//свдиг справо (деление на 2^n с округлением вниз. выведет 1)
        System.out.println(binaryC + " >> " + b + ": "  + (c >> b));
        System.out.println(binaryA + " >>> " + b + ": "  + (a >>> b));//сдвиг вправо (логический сдвиг)
        System.out.println(binaryC + " >>> " + b + ": "  + (c >>> b));//разница между последними двумя операциями в том,
        //  что >> добавляет биты слева в соответствии с изначальным знаком числа, а >>> всегда нули
    }

    private static void Parity(int a, int b){
        System.out.println("exercise 3.2---------------------");

        if ((a & 1) == 0 && (b & 1) == 0){
            System.out.println("a and b are Even");
        } else if ((a & 1) != 0 && (b & 1) != 0){
            System.out.println("a and b are Odd");
        }else{
            if ((a & 1) == 0 && (b & 1) != 0){
                System.out.println("a is Even. b is Odd");
            } else {
                System.out.println("a is Odd. b is Even");
            }
        }
    }

    private static boolean isPowerOfTwo(int a){
        System.out.println("exercise 3.3---------------------");

        if (a <= 0){
            System.out.println("Number <= 0");
            return false;
        }

        if ((a & (a - 1)) == 0){
            System.out.println("Number " + a + " is a power of 2");
            return true;
        } else {
            System.out.println("Number " + a + " is not a power of 2");
            return false;
        }
    }

    private static void unitCounter(int x){
        System.out.println("exercise 3.4---------------------");

        int count = 0;
        while (x != 0){
            count += (x & 1);
            x = x >>> 1;
        }

        System.out.println("Number of units: " + count);
    }

    private static void exchange(int a, int b){
        System.out.println("exercise 3.5---------------------");

        String binA = Integer.toBinaryString(a);//0101
        String binB = Integer.toBinaryString(b);//1011

        int x = Integer.parseInt(binA, 2);//0101
        int y = Integer.parseInt(binB, 2);//1011

        x = x ^ y;//1110
                  //1011
        y = x ^ y;//0101 = 5
                  //1110               
        x = x ^ y;//1011 = 11


        System.out.println("x:" + x);
        System.out.println("y:" + y);    
    }  
}
