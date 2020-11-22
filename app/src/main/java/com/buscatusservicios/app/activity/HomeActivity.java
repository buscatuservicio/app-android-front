package com.buscatusservicios.app.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.buscatusservicios.app.R;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      //  recyclerViewServices = findViewById(R.id.recyclerViewServices);
    }
}