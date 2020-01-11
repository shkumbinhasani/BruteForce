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
        long starttime = System.currentTimeMillis();
        for(long i = 0; i < combinations; i++){

            long mbetja = i;
            String password = "";
            for(int j = (size-1); j>=0 ; j--){
                int charat = (int)(mbetja/Math.pow(characters.length(),j));
                mbetja = (long) (i%Math.pow(characters.length(),j));
                password += (characters.charAt(charat));
            }
            boolean tested = false;
            //System.out.println(password);
            while(!tested){
                if(countActiveThreads() <= 100){
                    Thread th = new Tester(password, "http://localhost/login/login.php?password="+password);
                    th.start();
                    threads.add(th);
                    tested = true;
                }else{
                    break;
                }
            }

            if(isFound){
                System.out.println((System.currentTimeMillis()-starttime)/1000+" sekonda per ta gjetur passwordin "+password);
                System.exit(0);
            }

        }
    }
    private int countActiveThreads(){
        int activeThreads=0;
        for (int i = 0; i < threads.size(); i++) {
            if(!threads.get(i).isAlive()){
                threads.remove(i);
            }else{
                activeThreads++;
            }
        }
        return activeThreads;
    }
    public static void main(String[] args) {
        new Bruter("abcdefghijklmnopqrstuvwxyz", 4);
    }
}
