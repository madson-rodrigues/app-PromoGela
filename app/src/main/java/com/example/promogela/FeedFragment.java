package com.example.promogela;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedFragment extends Fragment{

    private ArrayList<Integer> images;

    private ArrayList<String> brands;

    private ArrayList<String> descriptions;

    private ArrayList<String> storeDescriptions;

    private ArrayList<String> prices;

    private ArrayList<String> stores;

    private ArrayList<Store> geo_localization_stores;

    public FeedFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setArguments(ArrayList<Store> rStoresLatlng, ArrayList<Integer> rImages, ArrayList<String> rBrands, ArrayList<String> rDescriptions, ArrayList<String> rPrices,
                             ArrayList<String> rStores, ArrayList<String> rStoreDescriptions) {
        this.geo_localization_stores = rStoresLatlng;
        this.images = rImages;
        this.brands = rBrands;
        this.descriptions = rDescriptions;
        this.prices = rPrices;
        this.stores = rStores;
        this.storeDescriptions = rStoreDescriptions;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ListView PromosList = (ListView)view.findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(getContext(),images, brands, descriptions, prices, stores);

        PromosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getContext(), activity_store.class);
                //contents that wil be used in the list of promotions in the activity_store
                ArrayList<Integer> images_to_activity_store_row = new ArrayList<>();
                ArrayList<String> brands_to_activity_store_row = new ArrayList<>();
                ArrayList<String> descriptions_to_activity_store_row = new ArrayList<>();
                ArrayList<String> prices_to_activity_store_row = new ArrayList<>();
                ArrayList<String> stores_to_activity_store_row = new ArrayList<>();


                //setting the attributes that will be used to create the store activity
                intent.putExtra("storeName", stores.get(position));
                intent.putExtra("description", storeDescriptions.get(position));
                intent.putExtra("store", stores.get(position));
                MyAdapter adapterStorePromos = new MyAdapter(getContext(),images, brands, descriptions, prices, stores);

                //intent.putExtra("adapter", adapterStorePromos);
                //send the row contents
                for(int i=0; i < stores.size(); i++){
                    if(stores.get(position).equals(stores.get(i))){
                        images_to_activity_store_row.add(images.get(i));
                        brands_to_activity_store_row.add(brands.get(i));
                        descriptions_to_activity_store_row.add(descriptions.get(i));
                        prices_to_activity_store_row.add(prices.get(i));
                        stores_to_activity_store_row.add(stores.get(i));
                    }
                }
                intent.putExtra("images",images_to_activity_store_row);
                intent.putExtra("brands",brands_to_activity_store_row);
                intent.putExtra("descriptions",descriptions_to_activity_store_row);
                intent.putExtra("prices",prices_to_activity_store_row);
                intent.putExtra("stores",stores_to_activity_store_row);
                //send the latitude and longitude of the store to the activity_store
                for(Store i: geo_localization_stores){
                    if(i.getName().equals(stores.get(position))){
                        LatLng latLng = i.getLatLng();
                        intent.putExtra("latitude", String.valueOf(latLng.latitude) );
                        intent.putExtra("longitude", String.valueOf(latLng.longitude));
                        Log.i("teste", "entrou");
                        break;
                    }
                }

                //intent.putExtra("stores",(Serializable)geo_localization_stores);
                //intent.putExtra("stores", geo_localization_stores);
                Log.i("teste", Integer.toString(geo_localization_stores.size()));
                startActivity(intent);
            }
        });
        PromosList.setAdapter(adapter);
        return view;
    }





    static class MyAdapter extends ArrayAdapter<String> implements Serializable {

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
