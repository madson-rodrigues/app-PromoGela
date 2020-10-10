package com.example.promogela;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
        brands = new ArrayList<>(Arrays.asList("Bohemia", "Heineken"));
        descriptions = new ArrayList<>(Arrays.asList("Lata 330ml", "Garrafa 660ml"));
        prices = new ArrayList<String>(Arrays.asList("1,89", "6,99"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ListView PromosList = (ListView)view.findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(getContext(), brands, descriptions, prices);

       PromosList.setAdapter(adapter);
        return view;
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;

        int rImages[];

        private ArrayList<String> rBrands;

        private ArrayList<String> rDescriptions;

        private ArrayList<String> rPrices;

        public MyAdapter(@NonNull Context context, ArrayList<String> brands, ArrayList<String> descriptions, ArrayList<String> prices) {
            super(context,R.layout.row, R.id.brand, brands);
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
            TextView brand = row.findViewById(R.id.brand);

            brand.setText(rBrands.get(position));
            //TODO fill the other arguments, descriptions, prices...


            return row;
        }
    }

}
