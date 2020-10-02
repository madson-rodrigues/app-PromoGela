package com.example.promogela;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedFragment extends Fragment {

    private ArrayList arrTemp = new ArrayList();

    private ArrayList array = new ArrayList();

    public FeedFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ListView listaDepromos = (ListView)view.findViewById(R.id.lista);
        List<String> itens = new ArrayList<String>();
        itens = Arrays.asList(new String[]{"asd", "abd", "ass"});
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, itens );
        listaDepromos.setAdapter(adapter);
        return view;
    }


}
