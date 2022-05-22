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

public class Meme_Activity extends AppCompatActivity {

    String imagelink;
    Intent intent;
    Intent chooser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);
        load_memes();
    }
    public void load_memes(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://meme-api.herokuapp.com/gimme";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            imagelink=response.getString("url");
                            Log.d("check","is good"+response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Log.d("apphome","everything is good "+response.getString("subreddit"));
                            ImageView iv=findViewById(R.id.meme_imageview);

                            Glide.with(Meme_Activity.this).load(imagelink).placeholder(R.drawable.rr).into(iv);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Error ","error occur");

                    }
                });

// Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest);

    }

    public void next_meme_loader(View view) {

        load_memes();
    }

    public void share_meme(View view) {
        intent= new Intent(Intent.ACTION_SEND);
        this.intent.setType("text/plain");
        this.intent.putExtra(Intent.EXTRA_TEXT,"Hey checkout this meme"+imagelink);
        chooser=Intent.createChooser(this.intent,"Share this meme using......");
        startActivity(chooser);

    }
}