package com.example.university_application;

import static android.app.ProgressDialog.show;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView universityName;
    TextView country;
    Button button;
    Context context;
    MainActivity mainActivity;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        universityName=itemView.findViewById(R.id.universityId);
        country=itemView.findViewById(R.id.countryId);
        button=itemView.findViewById(R.id.websiteButtonId);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("yoo", "onClick: i am clicked");
//              //  openUrl("https://www.example.com");
//            }
//        });
//        website=itemView.findViewById(R.id.websiteId);

    }

}
