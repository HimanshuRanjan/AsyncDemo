package com.example.himanshu.asyncdemo;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String str="https://www.iiitd.ac.in/about";
    String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Backgroud().execute();
            }
        });
        if(savedInstanceState != null)
        {
            result =savedInstanceState.get("value").toString();
            TextView tv=(TextView)findViewById(R.id.textView);
            tv.setText(result);
        }
    }
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        TextView tv=(TextView)findViewById(R.id.textView);
        outState.putString("value",tv.getText().toString());
    }
    public  class Backgroud extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected String doInBackground(String... strings) {

                try {
                    org.jsoup.nodes.Document doc=Jsoup.connect(str).get();
                    System.out.println(doc);
                    result=doc.title().toString();



                }

            catch (Exception e) {
                    e.printStackTrace();
                }

             return null;
        }
        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            TextView tv=(TextView)findViewById(R.id.textView);
            tv.setMovementMethod(new ScrollingMovementMethod());
            tv.setText(result);
        }
    }
}
