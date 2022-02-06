package com.example.natifetested.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.natifetested.R;
import com.example.natifetested.recycleservice.DataAdapter;
import com.example.natifetested.recycleservice.DataModel;
import com.example.natifetested.recycleservice.SpaceItemDecoration;
import com.example.natifetested.volleyservice.Singl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    ArrayList<DataModel> dataList = new ArrayList<>();
    DataAdapter adapter;

    public final static String API_KEY = "nXSkWFMIIjn1axUPzow5WBMo28DzbLnW";
    public final static String BASE_URL = "https://api.giphy.com/v1/gifs/trending?api_key=";


    String url = BASE_URL+API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.setHasFixedSize(true);

        //Получаем данные
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("data");

                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject object = dataArray.getJSONObject(i);

                        JSONObject object1 = object.getJSONObject("images");
                        JSONObject object2 = object1.getJSONObject("downsized_medium");

                        String sourceUrl = object2.getString("url");
                       // Log.d("url: ", ""+sourceUrl);
                        dataList.add(new DataModel(sourceUrl));
                    }

                    adapter = new DataAdapter(MainActivity.this, dataList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(MainActivity.this::OnItemClick);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error"+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Singl.getInstance(this).addToRequestQueue(objectRequest);
    }
    @Override
    public void OnItemClick(int pos) {
        Intent fullView = new Intent(this, FullActivity.class);
        DataModel clickedItem = dataList.get(pos);

        fullView.putExtra("imageUrl", clickedItem.getImageURL());
        startActivity(fullView);
    }
}