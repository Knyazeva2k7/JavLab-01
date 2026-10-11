 import java.util.List;
 import java.util.ArrayList;
 import java.util.Iterator;


public class PrimesGenerator implements Iterable<Integer>{
    private final int n;
    private final List<Integer> num;

    public PrimesGenerator(int n){
        this.n = n;
        this.num = new ArrayList<>();

        int c2= 2;
        int c = 0;
        while (c < n){
            if (isPrime(c2)){
                c++;
                num.add(c2);
            }
            c2++;
        }
    }

    private static boolean isPrime(int n){
        if (n < 2)
            return false;
        for (int i = 2; i * i <= n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
    @Override 
    public Iterator<Integer> iterator(){
        return num.iterator();
    }
    public List<Integer> getNum(){
        return num;
    }
}