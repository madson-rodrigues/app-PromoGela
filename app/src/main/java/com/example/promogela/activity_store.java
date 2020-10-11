package com.example.promogela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;

public class activity_store extends AppCompatActivity{

    TextView storeName;
    TextView description;
    ListView list;
    Store store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Intent intent = getIntent();
        storeName = findViewById(R.id.storeName);
        description = findViewById(R.id.description);
        list = findViewById(R.id.promoList);
        //store = (Store) intent.getSerializableExtra("stores");

        //TODO to add the link to the google maps, using the store that is already sett
        storeName.setText(intent.getStringExtra("storeName"));
        description.setText(intent.getStringExtra("description"));
    }

    public void openGoogleMaps(View view) {
        Uri gmmIntentUri = Uri.parse("geo:-23.564175,-46.6617916");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}