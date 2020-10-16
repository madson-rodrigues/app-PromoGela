package com.example.promogela;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ArrayList<Integer> images;

    private ArrayList<String> brands;

    private ArrayList<String> descriptions;

    private ArrayList<String> storeDescriptions;

    private ArrayList<String> prices;

    private ArrayList<String> stores_names_to_feed;

    ArrayList<Store> stores_array;

    List<ListView> list_for_the_activity_store;

    TabLayout tabLayout;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<>(Arrays.asList(R.drawable.bohemia_can, R.drawable.heineken_long,R.drawable.stella_long_neck, R.drawable.devassa_can, R.drawable.corona_long_neck, R.drawable.heineken_bottle));
        brands = new ArrayList<>(Arrays.asList("Bohemia", "Heineken", "Stella Artois", "Devassa", "Corona", "Heineken"));
        descriptions = new ArrayList<>(Arrays.asList("Lata 350ml", "Long Neck 330ml", "Long Neck 330ml", "Lata 350ml", "Long Neck 330ml", "Garrafa 600ml"));
        prices = new ArrayList<>(Arrays.asList("R$ 1,79", "R$ 4,10", "R$ 3,89", "R$ 2,39", "R$ 4,29", "R$ 6,29"));
        storeDescriptions = new ArrayList<>(Arrays.asList("Distribuidora", "Mercado", "Distribuidora", "Mercado", "Distibuidora", "Distribuidora"));
        stores_names_to_feed = new ArrayList<>(Arrays.asList("Rei da Gela", "Mercadinho São Paulo", "Produtos Lucena", "Supermercado Bom Jesus", "Distibuidora de Bebidas do Dilson", "Rei da Gela"));

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        list_for_the_activity_store = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Mapa");
        arrayList.add("Feed");
        arrayList.add("Perfil");

        prepareViewPager(viewPager, arrayList);

        prepareTabLayout();
        //Dynamically swiping the tabs <<<<<<<<<<<<<<<<<<
        //tabLayout.getTabAt(2).select();
    }

    private void prepareTabLayout() {

        tabLayout.setupWithViewPager(viewPager);
        //setting the tab icons
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_mapa);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_feed);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_perfil);
        //setting the tab icon colors, in order to start the app with the right colors
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.my_darker_gray), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.my_darker_gray), PorterDuff.Mode.SRC_IN);
        //changing dynamically the icon colors, when it's selected
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for(int i=0; i<tabLayout.getTabCount(); i++) {
                    if(tabLayout.getTabAt(i).isSelected())
                        tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                    else{
                        tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(R.color.my_darker_gray), PorterDuff.Mode.SRC_IN);
                    }
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        FeedFragment feed_fragment = new FeedFragment();
        MapsFragment maps_fragment = new MapsFragment();

        StoresFragment stores_fragment = new StoresFragment();

        //setting and adding the fragments to the adapter

        //setting maps_fragment
        stores_array = new ArrayList();
        updateStores(stores_array);
        maps_fragment.setStores(stores_array);
        adapter.addFragment(maps_fragment, arrayList.get(0));

        //setting feed_fragment
        feed_fragment.setArguments(stores_array, images, brands, descriptions, prices, stores_names_to_feed, storeDescriptions);
        adapter.addFragment(feed_fragment, "Feed");


        //setting StoresFragment
        stores_fragment.setArguments(stores_array);
        adapter.addFragment(stores_fragment, arrayList.get(2));

        viewPager.setAdapter(adapter);

    }

    private void updateStores(ArrayList<Store> stores) {

        Store store = new Store("Supermercado Bom Jesus", new LatLng(-5.850012, -35.254002), "Mercado","R. Paracati - Planalto, Natal - RN, 59073-100");
        stores.add(store);
        store = new Store("Mercadinho São paulo", new LatLng(-5.847530, -35.249089), "Mercado", "R. Paracati, 96 - Planalto, Natal - RN, 59073-100" );
        stores.add(store);
        store = new Store("Carrefour", new LatLng(-5.844684, -35.210559), "Hiper Mercado", "02 - BR-101, s/n - Lagoa Nova, Natal - RN, 59063-904");
        stores.add(store);
        store = new Store("Produtos Lucena", new LatLng(-5.851161, -35.210746), "Distribuidora", "Rua Agnaldo Gurgel Junior, 10 - Candelária, Natal - RN, 59066-030");
        stores.add(store);
        store = new Store("Rei da Gela", new LatLng(-5.856431, -35.236892), "Distribuidora", "Av. dos Xavantes, 7694 - Cidade Satelite, Natal - RN, 59068-380");
        stores.add(store);
        store = new Store("Distribuidora de Bebidas do Dilson", new LatLng(-5.769212, -35.203158), "Distribuidora", "Travessa Irmã Vitória, 49 - Rocas, Natal - RN, 59010-680");
        stores.add(store);
    }

    private class MainAdapter extends FragmentPagerAdapter{

        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public void addFragment (Fragment fragment,String title){
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);

        }
    }
}