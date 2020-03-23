package com.example.integrationwithwebservices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private Button glideButton;
    private Button httpbutton;
    private Button httplistbutton;
    private Button retrofitlistbutton;

    String url = "https://image.freepik.com/free-photo/image-human-brain_99433-298.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        glideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadWithGlide();


            }
        });
        httpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute(url);

            }
        });
        httplistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent http_intent = new Intent(MainActivity.this, HttpListActivity.class);
                startActivity(http_intent);
            }
        });
        retrofitlistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retrofit_intent = new Intent(MainActivity.this, RetrofitListActivity.class);
                startActivity(retrofit_intent);
            }
        });
    }


    private void initUi() {
        glideButton = findViewById(R.id.glidebutton);
        httpbutton = findViewById(R.id.httpbutton);
        httplistbutton = findViewById(R.id.httplist);
        retrofitlistbutton = findViewById(R.id.retrofitlist);


    }

    private void downLoadWithGlide() {
        Glide.with(this).load(url).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
                linearLayout.setBackground(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });


    }

    @SuppressLint("LongLogTag")
    private Bitmap downloadUrl(String strUrl) throws IOException {
        Bitmap bitmap = null;
        InputStream iStream = null;
        try {
            URL url = new URL(strUrl);
            /** Creating an http connection to communcate with url */
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            /** Connecting to url */
            urlConnection.connect();

            /** Reading data from url */
            iStream = urlConnection.getInputStream();

            /** Creating a bitmap from the stream returned from the url */
            bitmap = BitmapFactory.decodeStream(iStream);

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
        }
        return bitmap;
    }

    private class DownloadTask extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url) {
            Bitmap bitmap = null;
            try {
                bitmap = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), result);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
            linearLayout.setBackground(bitmapDrawable);
        }
    }
}
