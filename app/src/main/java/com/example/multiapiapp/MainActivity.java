package com.example.multiapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void meme_handler(View view) {
        // control meme handler
        Toast.makeText(this, "Top Selected ", Toast.LENGTH_SHORT).show();
        CardView cv_meme=(CardView) findViewById(R.id.cd_meme);
        Intent intent=new Intent(this,Meme_Activity.class);
        startActivity(intent);

    }

    public void quotes_handler(View view) {
        // control quotes handler
        Toast.makeText(this, "Pro Selected ", Toast.LENGTH_SHORT).show();
        CardView cv_quote=(CardView) findViewById(R.id.cd_quotes);
        Intent intent=new Intent(this,Quotes_Activity.class);
        startActivity(intent);
    }

    public void picture_handler(View view) {
        // control pic handler
        Toast.makeText(this, "New Selected ", Toast.LENGTH_SHORT).show();
        CardView cv_pic=(CardView) findViewById(R.id.cd_pictures);
        Intent intent=new Intent(this,Picture_Activity.class);
        startActivity(intent);
    }

    public void about_us_handler(View view) {
        // control about us
        Toast.makeText(this, "About Us ", Toast.LENGTH_SHORT).show();
        CardView cv_aus=(CardView) findViewById(R.id.cd_about_us);
        Intent intent=new Intent(this,About_Us.class);
        startActivity(intent);

    }

    public void feed_btn(View view) {
        Toast.makeText(this, "FeedBack Form", Toast.LENGTH_SHORT).show();
        Button btn=(Button) findViewById(R.id.btn_feed_back);
        Intent intent=new Intent(this,FeedBack.class);
        startActivity(intent);
    }
}


