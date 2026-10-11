import java.util.Iterator;

public class PrimesGeneratorTest {
    public static void runTest(){
        int N = 10;
        PrimesGenerator gen = new PrimesGenerator(N);

        System.out.println("exercise 2.0-------------------\n");
        System.out.println("Primes numbers: ");
        Iterator<Integer> iter = gen.iterator();
        while (iter.hasNext()){
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}
