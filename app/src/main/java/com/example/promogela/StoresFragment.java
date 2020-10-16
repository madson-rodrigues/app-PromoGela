package com.example.promogela;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class StoresFragment extends Fragment {

    public StoresFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stores, container, false);
        ListView listView = (ListView) view.findViewById(R.id.storeList);

        return view;
    }

    static class MyAdapter extends ArrayAdapter<String> {

        public Context context;

        private ArrayList<Integer> rImages;

        private ArrayList<String> rBrands;

        private ArrayList<String> rDescriptions;

        private ArrayList<String> rPrices;

        private ArrayList<String> rStores;

        public MyAdapter(@NonNull Context context, ArrayList<Integer> images, ArrayList<String> brands, ArrayList<String> descriptions, ArrayList<String> prices, ArrayList<String> stores) {
            super(context,R.layout.row, R.id.brand, brands);
            this.rImages = images;
            this.context = context;
            this.rBrands = brands;
            this.rDescriptions = descriptions;
            this.rPrices = prices;
            this.rStores = stores;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView image = row.findViewById(R.id.image);
            TextView brand = row.findViewById(R.id.brand);
            TextView description = row.findViewById(R.id.description);
            TextView price = row.findViewById(R.id.price);
            TextView store = row.findViewById(R.id.store);

            image.setImageResource(rImages.get(position));
            brand.setText(rBrands.get(position));
            description.setText(rDescriptions.get(position));
            price.setText(rPrices.get(position));
            store.setText(rStores.get(position));

            return row;
        }

    }
}