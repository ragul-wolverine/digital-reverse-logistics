package com.example.root.packit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class bg extends AsyncTask <String,Void,String> {

    AlertDialog dialog;
    Context context;
    public bg(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Detail Status");
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if(s.contains("Register successful"))
        {
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),Final.class);
            context.startActivity(intent_name); }
    }

    @Override
    protected String doInBackground(String... voids) {
        String result ="";

        String cat= voids[0];
        String br = voids[1];
        String sn= voids[2];
        String q = voids[3];
        String pid = voids[4];
        String dd = voids[5];
        String user = voids[6];

        String connstr = "http://192.168.43.130/data.php";

        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);


            OutputStream ops = http.getOutputStream();
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(cat,"UTF-8")
                    +"&&"+URLEncoder.encode("brand","UTF-8")+"="+URLEncoder.encode(br,"UTF-8")
                    +"&&"+URLEncoder.encode("serial_number","UTF-8")+"="+URLEncoder.encode(sn,"UTF-8")
                    +"&&"+URLEncoder.encode("quantity","UTF-8")+"="+URLEncoder.encode(q,"UTF-8")
                    +"&&"+URLEncoder.encode("product_id","UTF-8")+"="+URLEncoder.encode(pid,"UTF-8")
                    +"&&"+URLEncoder.encode("date_of_deliver","UTF-8")+"="+URLEncoder.encode(dd,"UTF-8")
                    +"&&"+URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(user,"UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line = "";
            while((line = reader.readLine()) != null)
            {
                result += line;

            }

            reader.close();
            ips.close();
            http.disconnect();
            return result;


        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result =e.getMessage();
        }


        return result;
    }
}
