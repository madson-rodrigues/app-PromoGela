package com.example.promogela;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedFragment extends Fragment {

    int images[];

    private ArrayList<String> brands;

    private ArrayList<String> descriptions;

    private ArrayList<String> prices;

    public FeedFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO to look for a better way to save it, maybe in a class
        images = new int[]{R.drawable.bohemia_can, R.drawable.heineken_long,R.drawable.stella_long_neck, R.drawable.devassa_can, R.drawable.corona_long_neck, R.drawable.heineken_bottle};
        brands = new ArrayList<>(Arrays.asList("Bohemia", "Heineken", "Stella Artois", "Devassa", "Corona", "Heinken"));
        descriptions = new ArrayList<>(Arrays.asList("Lata 350ml", "Long Neck 330ml", "Long Neck 330ml", "Lata 350ml", "Long Neck 330ml", "Garrafa 600ml"));
        prices = new ArrayList<>(Arrays.asList("R$ 1,79", "R$ 4,10", "R$ 3,89", "R$ 2,39", "R$ 4,29", "R$ 6,29"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ListView PromosList = (ListView)view.findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(getContext(),images, brands, descriptions, prices);
        //TODO set the click listener
        PromosList.setAdapter(adapter);
        return view;
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;

        int rImages[];

        private ArrayList<String> rBrands;

        private ArrayList<String> rDescriptions;

        private ArrayList<String> rPrices;

        public MyAdapter(@NonNull Context context, int[] images, ArrayList<String> brands, ArrayList<String> descriptions, ArrayList<String> prices) {
            super(context,R.layout.row, R.id.brand, brands);
            this.rImages = images;
            this.context = context;
            this.rBrands = brands;
            this.rDescriptions = descriptions;
            this.rPrices = prices;
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

            image.setImageResource(rImages[position]);
            brand.setText(rBrands.get(position));
            description.setText(rDescriptions.get(position));
            price.setText(rPrices.get(position));



            return row;
        }
    }

}
