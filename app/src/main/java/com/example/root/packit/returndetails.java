package com.example.root.packit;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class returndetails extends AppCompatActivity {

        ListView listView;
        ArrayAdapter<String> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_returndetails);


            listView = (ListView) findViewById(R.id.list);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            listView.setAdapter(adapter);
            new Connection().execute();
        }

        class Connection extends AsyncTask<String,String,String> {

            @Override
            protected String doInBackground(String... params) {
                String result = "";
                String host ="http://192.168.43.130/returns.php";
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI(host));
                    HttpResponse response = client.execute(request);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    StringBuffer stringBuffer = new StringBuffer("");

                    String line = "";
                    while ((line = reader.readLine()) != null){
                        stringBuffer.append(line);
                        break;
                    }
                    reader.close();
                    result = stringBuffer.toString();
                }
                catch (Exception e){
                    return new String("There exception:" + e.getMessage());
                }

                return result;
            }

            @Override
            protected void onPostExecute(String result){
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    int success = jsonResult.getInt("success");
                    if(success == 1){
                        JSONArray returns = jsonResult.getJSONArray("returns");
                        for(int i =0; i<returns.length(); i++){
                            JSONObject rtn = returns.getJSONObject(i);
                            int serial_number = rtn.getInt("serial_number");
                            int product_id = rtn.getInt("product_id");
                            int quantity = rtn.getInt("quantity");
                            String type_of_damage = rtn.getString("type_of_damage");
                            String line =serial_number + "-" + product_id + "-" + quantity + "-" + type_of_damage;
                            adapter.add(line);
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"There is no data",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
}


