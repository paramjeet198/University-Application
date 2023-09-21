package com.example.university_application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private Context context;
    List<Item> items;


    public MyAdapter(Context applicationContext, List<Item> items) {
        this.context = applicationContext;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.universityName.setText(items.get(position).getUniversityId());
        holder.country.setText(items.get(position).getCountry());

        holder.button.setOnClickListener(view -> {
            Log.d(" yashshvi ", "onClick: i am komal");

//                 holder.button.setText("komal");
//                 holder.button.setText(items.get(position).getMyurl());
            String myurl = items.get(position).getMyurl();
//                 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myurl));
//                 // Add FLAG_ACTIVITY_NEW_TASK flag
//                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                 // Start the intent using the adapter's context
//                 context.startActivity(intent);

//                 Intent intent = new Intent(this, WebActivity.class);
            Intent intent = new Intent(view.getContext(), WebActivity.class);

            // Pass the URL as an extra to the new activity
            intent.putExtra("URL", myurl);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Start the WebActivity
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
