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

    Intent intent;
    TextView storeName;
    TextView description;
    ListView list;
    Store store;
    FeedFragment.MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        intent = getIntent();
        storeName = findViewById(R.id.storeName);
        description = findViewById(R.id.description);
        list = findViewById(R.id.promoList);
        //store = (Store) intent.getSerializableExtra("stores");
        storeName.setText(intent.getStringExtra("storeName"));
        description.setText(intent.getStringExtra("description"));
        myAdapter = (FeedFragment.MyAdapter) intent.getSerializableExtra("adapter");
    }

    public void openGoogleMaps(View view) {
        String latitude = intent.getStringExtra("latitude");
        String longitude = intent.getStringExtra("longitude");
        Uri gmmIntentUri = Uri.parse("geo:"+ latitude + "," + longitude + "?z=16?q=" + latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        //TODO put a marker on the map and configure the zoom of the camera
        startActivity(mapIntent);
    }
}