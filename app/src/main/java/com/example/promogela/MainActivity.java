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

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Feed");
        arrayList.add("Mapa");
        arrayList.add("Perfil");

        prepareViewPager(viewPager, arrayList);

        tabLayout.setupWithViewPager(viewPager);
        //setting the tab icons
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_feed);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_mapa);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_perfil);
        //setting the tab icon colors, in order to start the app with the right colors
        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for(int i=0; i<tabLayout.getTabCount(); i++) {
                    if(tabLayout.getTabAt(i).isSelected())
                        tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                    else{
                        tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);
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

        MainFragment fragment = new MainFragment();

        for(int i=0; i<arrayList.size(); i++){
            //init bundle
            Bundle bundle = new Bundle();

            bundle.putString("title", arrayList.get(i));

            fragment.setArguments(bundle);

            adapter.addFragment(fragment, arrayList.get(i));

            fragment = new MainFragment();
        }

        viewPager.setAdapter(adapter);

    }

    private class MainAdapter extends FragmentPagerAdapter {

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