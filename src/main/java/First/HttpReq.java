package First;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class HttpReq {
    public static void sendGet(String httpUrl) throws Exception {

        String url = httpUrl;

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header


        HttpResponse response = client.execute(request);
        System.out.println(Thread.currentThread().getName());
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

    }
}
