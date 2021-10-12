package com.example.pts_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity  {
  private String option;
   private Button buttonone,buttontwo;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView option  = findViewById(R.id.option) ;
         buttonone = findViewById(R.id.buttonone);
         buttontwo = findViewById(R.id.buttontwo);



        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , API_Activity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });




        buttontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,FavoriteActivity.class);
                startActivity(intent);
            }
        });
    }
}