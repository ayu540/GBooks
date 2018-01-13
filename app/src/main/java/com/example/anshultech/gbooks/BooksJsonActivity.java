package com.example.anshultech.gbooks;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by AnshulTech on 07-Jan-18.
 */

public class BooksJsonActivity extends AppCompatActivity {

    //get the activity name in LOG_TAG
    private static final String LOG_TAG = BooksJsonActivity.class.getSimpleName();

    private BooksJsonActivity()
    {
    }

    public static ArrayList<Booksgetter_setter> extractJsondata (String SIMPLE_CONNECTION_STRING) throws IOException {

        ArrayList<Booksgetter_setter> booksarray =  new ArrayList<>();
        //created a global URL variable to be used to set connection
        URL url = null;

        try {
            url = new URL(SIMPLE_CONNECTION_STRING);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //building connection
        String jsoninputdata  = "" ;
        HttpsURLConnection seturlconnection = null;
        InputStream setinputstrem = null;

        try {
            seturlconnection = (HttpsURLConnection) url.openConnection();
            seturlconnection.setReadTimeout(1000);
            seturlconnection.setConnectTimeout(5000);
            seturlconnection.connect();                    //if no error then connection build up at this line


            setinputstrem = seturlconnection.getInputStream(); //get stream data in input stream to be processed

            StringBuilder output = new StringBuilder();

            if(setinputstrem != null) {
                InputStreamReader readinput = new InputStreamReader(setinputstrem, Charset.forName("UTF-8"));  //read the input stream into universal reading format
                BufferedReader readbuffereddata = new BufferedReader(readinput);
                String stringdata = readbuffereddata.readLine();
                while (stringdata != null) {
                    output.append(stringdata);
                    stringdata = readbuffereddata.readLine();
                }
                jsoninputdata = output.toString();  // when data reading and appending completed so passed the data in string variable

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (seturlconnection!=null) {
                seturlconnection.disconnect();
            }
            if (setinputstrem!=null){
                setinputstrem.close(); // for this we need to catch exception that need to declared in method declaration
            }
        }

        //started parsing data in JSON
        try {
            JSONObject jsonroot = new JSONObject(jsoninputdata);
            JSONArray myjsonarray = jsonroot.getJSONArray("items");

            for(int i=0; i<myjsonarray.length();i++) {

                JSONObject volumeinfojsonarray = myjsonarray.getJSONObject(i);
                JSONObject volumeinfoobject = volumeinfojsonarray.getJSONObject("volumeInfo");
                String mytitlejson = volumeinfoobject.optString("title");
                JSONArray myauthorarray = volumeinfoobject.getJSONArray("authors");
                String finalauthorname = myauthorarray.getString(0);
                booksarray.add(new Booksgetter_setter(mytitlejson, finalauthorname)); // add the data in booksarray and passed from getter setter class
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return booksarray;
    }
}
