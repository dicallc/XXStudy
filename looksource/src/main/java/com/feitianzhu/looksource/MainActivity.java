package com.feitianzhu.looksource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import volley.RequestQueue;
import volley.Response;
import volley.VolleyError;
import volley.toolbox.StringRequest;
import volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RequestQueue mQueue = Volley.newRequestQueue(this);
    StringRequest stringRequest =
        new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
          @Override public void onResponse(String response) {
            Log.d("TAG", response);
          }
        }, new Response.ErrorListener() {
          @Override public void onErrorResponse(VolleyError error) {
            Log.e("TAG", error.getMessage(), error);
          }
        });
    mQueue.add(stringRequest);
  }
}
