package com.mrm.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DescActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        ImageView filmPoster = findViewById(R.id.descPoster);
        TextView name = findViewById(R.id.filmName);
        TextView marvelFilmName = findViewById(R.id.marvelFilmName);

        String url = getIntent().getStringExtra("filmPoster");

        filmPoster.setImageBitmap(makeBitmapFromURL(url));
        name.setText(getIntent().getStringExtra("desc"));

        marvelFilmName.setText(getIntent().getStringExtra("desc"));
    }

    private static Bitmap makeBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream i = connection.getInputStream();
            return BitmapFactory.decodeStream(i);
        } catch (IOException e) {
            return null;
        }
    }
}