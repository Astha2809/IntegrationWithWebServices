package com.example.integrationwithwebservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Adapter;

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
import java.util.ArrayList;
import java.util.List;

public class HttpListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public ArrayList<Post> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_list);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(arrayList, HttpListActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        FetchData fetchData = new FetchData();
        fetchData.execute();

    }

    public class FetchData extends AsyncTask<Void, Void, Void> {
        public static final String URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json";
        String data = "", dataparsed = "", singleparse = "", finaldata = "";
        String json_string = "";
        ArrayList<Post> arrayList = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                java.net.URL url = new URL(URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("posts");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    String name = jsonObject1.getString("name");
                    String message = jsonObject1.getString("message");
                    String profile = jsonObject1.getString("profileImage");
                    arrayList.add(new Post(name, message, profile));

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new MyAdapter(arrayList, HttpListActivity.this);
            recyclerView.setAdapter(adapter);
        }
    }
}
