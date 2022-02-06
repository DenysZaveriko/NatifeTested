package com.example.natifetested.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.natifetested.R;

public class FullActivity extends AppCompatActivity {
private ImageView fullImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);

        fullImage = findViewById(R.id.fullImage);

        Intent receiver = getIntent();
        String sourceUrl = receiver.getStringExtra("imageUrl");

        Glide.with(this).load(sourceUrl).into(fullImage);
    }

    public void Close(View view) {
        Intent MainActivity = new Intent(this, com.example.natifetested.activity.MainActivity.class);
        startActivity(MainActivity);
    }
}