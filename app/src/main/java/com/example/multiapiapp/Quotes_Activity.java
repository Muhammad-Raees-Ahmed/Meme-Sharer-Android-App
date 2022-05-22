package com.example.multiapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Quotes_Activity extends AppCompatActivity {

    String imagelinkq;
    Intent intent;
    Intent chooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        load_quotes();
    }
    public void load_quotes() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://meme-api.herokuapp.com/gimme";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()  {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            imagelinkq = response.getString("url");
                            Log.d("check", "is good" + response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Log.d("apphome", "everything is good " + response.getString("subreddit"));
                            ImageView ivpq = findViewById(R.id.quo_imageviewq);

                            Glide.with(Quotes_Activity.this).load(imagelinkq).placeholder(R.drawable.rr).into(ivpq);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Error ", "error occur");

                    }
                });
// Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest);
    }


    public void share_meme(View view) {
        intent= new Intent(Intent.ACTION_SEND);
        this.intent.setType("text/plain");
        this.intent.putExtra(Intent.EXTRA_TEXT,"Hey checkout this meme"+imagelinkq);
        chooser=Intent.createChooser(this.intent,"Share this meme using......");
        startActivity(chooser);

    }

    public void next_meme_loader(View view) {
        load_quotes();
    }
}