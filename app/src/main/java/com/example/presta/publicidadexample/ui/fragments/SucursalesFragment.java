package com.example.presta.publicidadexample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.ui.adapter.PublicidadRecyclerAdapter;

/**
 * Created by Presta on 30/03/2016.
 */
public class SucursalesFragment extends Fragment {

    //region "-- CONSTRUCTOR --"

    public SucursalesFragment() {
        // Required empty public constructor
    }

    //endregion

    //region "-- OVERRIDE --"

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sucursales, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //endregion

}
