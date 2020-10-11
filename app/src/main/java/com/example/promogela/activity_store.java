package com.example.promogela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class activity_store extends AppCompatActivity {

    TextView storeName;
    TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Intent intent = getIntent();
        storeName = findViewById(R.id.storeName);
        description = findViewById(R.id.description);

        storeName.setText(intent.getStringExtra("storeName"));
        description.setText(intent.getStringExtra("description"));
    }
}