package com.example.promogela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class activity_store extends AppCompatActivity{

    Intent intent;
    TextView storeName;
    TextView description;
    ListView listView;
    Store store;
    FeedFragment.MyAdapter myAdapter;
    ArrayList<Integer> images;
    ArrayList<String> brands;
    ArrayList<String> descriptions;
    ArrayList<String> prices;
    ArrayList<String> stores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        intent = getIntent();
        storeName = findViewById(R.id.storeName);
        description = findViewById(R.id.description);
        listView = findViewById(R.id.promoList);
        //store = (Store) intent.getSerializableExtra("stores");
        storeName.setText(intent.getStringExtra("storeName"));
        description.setText(intent.getStringExtra("description"));
        //myAdapter = (FeedFragment.MyAdapter) intent.getSerializableExtra("adapter");

        images = (ArrayList<Integer>) intent.getSerializableExtra("images");
        brands = (ArrayList<String>) intent.getSerializableExtra("brands");
        descriptions = (ArrayList<String>) intent.getSerializableExtra("descriptions");
        prices = (ArrayList<String>) intent.getSerializableExtra("prices");
        stores = (ArrayList<String>) intent.getSerializableExtra("stores");

        myAdapter = new FeedFragment.MyAdapter(getApplicationContext(),images, brands, descriptions, prices, stores);
        listView.setAdapter(myAdapter);

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