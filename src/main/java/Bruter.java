import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Bruter {
    public BlockingQueue queue = new ArrayBlockingQueue(1000);

    public Bruter(String characters, int size){
        long combinations = (long) Math.pow(characters.length(),size);
        System.out.println(combinations);
        for(long i = 1; i <= combinations; i++){
            long mbetja = i;
            for(int j = (size-1); j>=0 ; j--){
                double charat = Math.ceil(mbetja/Math.pow(10,j));
                mbetja = (long) (i%Math.pow(10,j));
                System.out.print((int)charat);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Bruter("0123456789", 4);
    }
}
