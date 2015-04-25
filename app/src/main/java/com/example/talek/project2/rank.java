package com.example.talek.project2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;


public class rank extends ActionBarActivity {

    ArrayList<Map<String, String>> data;
    SimpleAdapter adapter;
    Handler h;
    Handler handler;

 //   String f="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);


        LoadNumberTask l = new LoadNumberTask();

        l.execute();
        System.out.println("lLl");





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class LoadNumberTask extends AsyncTask<String, Void, Boolean> {

        int fast;
        int thor;
        int taken;

        @Override
        protected Boolean doInBackground(String... params) {
            BufferedReader reader;
            StringBuilder buffer = new StringBuilder();
            String line;


            try {
                URL u = new URL("http://ict.siit.tu.ac.th/~u5522773787/its333/fetch.php");
                HttpURLConnection h = (HttpURLConnection) u.openConnection();
                h.setDoInput(true);
                h.connect();

                int response = h.getResponseCode();
                if (response == 200) {
                    reader = new BufferedReader(new InputStreamReader(h.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    Log.e("LoadMessageTask", buffer.toString());

                    //Parsing JSON and displaying messages
                    JSONObject json = new JSONObject(buffer.toString());
                    JSONArray jmsg = json.getJSONArray("msg");

                        JSONObject jmessage = jmsg.getJSONObject(0);
                     //   String br = jmessage.getString("branch");
                        fast = jmessage.getInt("Fast7");
                        thor = jmessage.getInt("Thor");
                        taken = jmessage.getInt("Taken");

                           Log.e("intFast7",Integer.toString(fast));

//                        f = Integer.toString(fast);
//                        System.out.println(f);

                    //    item.put("Branch", br);
//                        item.put("Fast7", fast);
//                        item.put("Thor", thor);
//                        item.put("Taken", taken);
//                        data.add(0, item);
                        System.out.println("Hellooooooooooooooo");


                    return true;

                }

            } catch (MalformedURLException e) {
                Log.e("LoadMessageTask", "Invalid URL");
            } catch (IOException e) {
                Log.e("LoadMessageTask", "I/O Exception");
            } catch (JSONException e) {
                Log.e("LoadMessageTask", "Invalid JSON");
            }
            return false;


        }
        protected void onPostExecute(Boolean result){
            TextView tv1,tv2,tv3;


            tv1 = (TextView)findViewById(R.id.nm1);
            tv2 = (TextView)findViewById(R.id.nm2);
            tv3 = (TextView)findViewById(R.id.nm3);


            if (result) {

                 if(fast>=thor && thor>=taken) {
                     tv1.setText(String.format("Fast = %d%%",fast));
                     tv2.setText(String.format("thor = %d%%",thor));
                     tv3.setText(String.format("taken = %d%%",taken));
                 }else if(fast>=thor && taken>=thor){
                     tv1.setText(String.format("Fast = %d%%",fast));
                     tv2.setText(String.format("taken = %d%%",taken));
                     tv3.setText(String.format("thor = %d%%",thor));
                 }

//
//
            }

        }


    }
}