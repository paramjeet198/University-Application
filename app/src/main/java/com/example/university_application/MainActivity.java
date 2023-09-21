package com.example.university_application;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.university_application.api.ApiResponseModel;
import com.example.university_application.api.MyApiHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView data;
    RecyclerView recyclerView;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hiiiiiiiiiiii");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent serviceIntent = new Intent(this, MyforegroundService.class);
//        ContextCompat.startForegroundService(this, serviceIntent);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        Intent serviceIntent = new Intent(this, DataRefreshForegroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        }

        recyclerView = findViewById(R.id.recyclerView);
        items = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();

//         data=findViewById(R.id.textview);

//        Log.v("V", "Hi");
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                Request.Method.GET,   // HTTP method (GET, POST, etc.)
//                url,                  // URL of the JSON data source
//                null,                 // Optional request body (for POST requests)
//                response -> {
//                    try {
//                        for (int i = 0; i < 2; i++) {
//                            JSONObject item = response.getJSONObject(i);
////                                data.setText(item.toString());
//                            Log.d("API Data", "item @@" + item);
//                            String itemName = item.getString("name");
//                            String country = item.getString("country");
//                            JSONArray website = item.getJSONArray("web_pages");
//
//                            Log.d("API Data", "Item Name: " + itemName);
//                            Log.d("API Data", "Item country: " + country);
//                            Log.d("API Data", "Item country: " + website.get(0));
//
//                            items.add(new Item(itemName, country, (String) website.get(0)));
//
//                        }
//                        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                },
//                error -> Log.d("Error: ", "Err************" + error.getMessage())
//        );
//        Volley.newRequestQueue(this).add(jsonArrayRequest);


    }

    private void loadData() {
        MyApiHelper.fetchDataFromApi(this, new MyApiHelper.MyApiCallback() {
            @Override
            public void onSuccess(List<ApiResponseModel> dataList) {

                for (int i = 0; i < 4; i++) {
                    ApiResponseModel item = dataList.get(i);
                    Log.d("ApiResponse", item.toString());
                    items.add(new Item(item.getName(), item.getCountry(), item.getWeb_pages().get(0)));
                }
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
            }

            @Override
            public void onError(VolleyError error) {
                Log.e("Api Error: ", error.getMessage());
            }
        });

    }
}