package HTTPConnection;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by esmael256 on 9/23/2016.
 */

public class HttpDataRequest {
/*
 public String GetJSONDataFromAPI(String response_url)
    {
        HttpURLConnection conn=null;
        try {
            URL url = new URL(response_url);
             conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            String jsonString=null;
            System.out.println("JSON Reading from  "+response_url+" .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                jsonString=output;
            }
            return jsonString;
        } catch (Exception e) {
            try
            {
            System.out.println(""+conn.getResponseCode());
            }catch(Exception ex)
            {
              e.printStackTrace();
            }
            // TODO Auto-generated catch block
            System.out.println("JSONResponse#sendJSON exception : " + e.getMessage() );
            e.printStackTrace();
            return null;
        }
    }*/

     //Since the website am accessing only allows GET requests, idecided to use HttpGet, the above method can work well with Post
   // requests

    public String  GetJSONDataFromAPI(String url)
    {
        String result=null;
        HttpClient httpclient = new DefaultHttpClient();

        // Prepare a request object
        HttpGet httpget = new HttpGet(url);

        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            // Examine the response status
            Log.i("status",response.getStatusLine().toString());

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                 result= convertStreamToString(instream);
                System.out.println(result);
                // now you have the string representation of the HTML request
                instream.close();
            }

          return result;
        } catch (Exception e)
        {
         return null;
        }
    }

    private static String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String i use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



}
