package com.example.presta.publicidadexample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.rest.get.ApiGetAdapter;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadesResponse;
import com.example.presta.publicidadexample.ui.adapter.PublicidadRecyclerAdapter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Presta on 10/03/2016.
 */
public class DestacadosFragment extends Fragment {

    //region "-- ATRIBUTOS --"

    private RecyclerView mRecycler;
    private PublicidadRecyclerAdapter adapter;

    //endregion

    //region "-- CONSTRUCTOR --"

    public DestacadosFragment() {
        // Required empty public constructor
    }

    //endregion

    //region "-- OVERRIDE --"

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PublicidadRecyclerAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_destacados, container, false);

        mRecycler = (RecyclerView) root.findViewById(R.id.list_publicidades);

        // Hace el setup del RecyclerView
        setupList();

        // Consulta las publicidades a la API
        requestInicialPublicidades();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //endregion

    //region "-- PRIVATE METHODS --"

    private void setupList() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(adapter);
        // mRecycler.addItemDecoration(new ItemDividerDecoration(getActivity()));
    }

    private void requestInicialPublicidades() {
        ApiGetAdapter.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PublicidadesResponse>() {
                    @Override
                    public void onNext(PublicidadesResponse publicidadesResponse) {
                        adapter.addAll(publicidadesResponse.getPublicidades());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    //endregion
}
