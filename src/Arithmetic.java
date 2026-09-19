public class Arithmetic{
    public void run(){
        Sum();
        Sum01();
        Comparison(0.1 + 0.2, 0.3, 0.00001);
        GetInfinity();
        Comparison27();
        FloatVSDouble();
    }
    public void Sum(){
        System.out.println("exercise 2.1---------------------");

        double a = 0.1;
        double b = 0.2;
        double c = a + b;
        System.out.printf("Sum of 0.1 + 0.2 = " + c + "\n");//числа с плавающей точкой нельзя точно записать в двоичном коде. 
        // это типа как 1/3 = 0,(3)
    }

    public void Sum01(){
        System.out.println("exercise 2.2---------------------");

        double rez = 0.0;
        for (int count = 0; count < 10; count++){
            rez += 0.1;
        }

        System.out.println(rez == 1.0);//числа типа double невозможно точно представить в двоичном виде, 
        // поэтому при сложении эта погрешность остается и не равляется ровному числу
    }
    public void Comparison (double a, double b, double epsilon){
        System.out.println("exercise 2.3---------------------");
        
        if (Math.abs(a - b) < epsilon){
            System.out.println("The numbers are equal");
        }
        else 
            System.out.println("the numbers are not equal");
    }

    public void GetInfinity(){
        System.out.println("exercise 2.4---------------------");

        double a = 1.0;
        double b = -1.0;
        double c = (a / 0.0 * 0.0);
        System.out.println("Infinity: "  + a / 0.0);
        System.out.println("-Infinity: " + b / 0.0);
        System.out.println("Non: " + (a / 0.0) * 0.0);
        System.out.println("Non != Non: " + (c == Double.NaN));
    }

    public void Comparison27(){
        System.out.println("exercise 2.5---------------------");

        double a = 2.7;
        double b = -2.7;

        System.out.println("+");
        System.out.println("(int): " + ((int)a));
        System.out.println("Math.round: " + Math.round(a));
        System.out.println("Math.floor: " + Math.floor(a));
        System.out.println("Math.ceil: " + Math.ceil(a));

        System.out.println("-");
        System.out.println("(int): " + (int)(b));
        System.out.println("Math.round: " + Math.round(b));
        System.out.println("Math.floor: " + Math.floor(b));
        System.out.println("Math.ceil: " + Math.ceil(b));
    }

    public void FloatVSDouble(){
        System.out.println("exercise 2.6---------------------");

        double res1 = 10.0 / 3.0;
        float res2 = 10.0f / 3.0f;
        System.out.println("Comparison: " + res1 + " and " + res2);
    }

}