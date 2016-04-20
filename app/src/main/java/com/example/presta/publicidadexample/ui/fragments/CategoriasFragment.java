package com.example.presta.publicidadexample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.Categoria;
import com.example.presta.publicidadexample.common.entities.HomeItem;
import com.example.presta.publicidadexample.common.entities.Promocion;
import com.example.presta.publicidadexample.common.enums.CategoriaItemTypeEnum;
import com.example.presta.publicidadexample.common.enums.ClaseHomeItemEnum;
import com.example.presta.publicidadexample.ui.recyclers.adapters.CategoriasRecyclerAdapter;
import com.example.presta.publicidadexample.ui.recyclers.adapters.HomeRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Presta on 19/04/2016.
 */
public class CategoriasFragment extends Fragment {

    //region "-- ATRIBUTOS --"

    private RecyclerView mRecycler;
    private CategoriasRecyclerAdapter adapter;
    private boolean requestInicialRealizado = false;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    //endregion

    //region "-- CONSTRUCTOR --"

    public CategoriasFragment() {
        // Required empty public constructor
    }

    //endregion

    //region "-- OVERRIDE --"

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CategoriasRecyclerAdapter(getActivity(), CategoriaItemTypeEnum.VERTICAL);
        //  Log.i("PASOPORACA", "HOMEonCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  Log.i("PASOPORACA", "HOMEonCreateView");
        View root = inflater.inflate(R.layout.fragment_categorias, container, false);

        mRecycler = (RecyclerView) root.findViewById(R.id.recycler_categorias_vertical);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        // Hace el setup del RecyclerView
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(adapter);

        // Consulta las publicidades a la API
        if (!requestInicialRealizado) {
            requestInicial();
            requestInicialRealizado = true;
        }

        return root;
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        // mDemoSlider.stopAutoCycle();
        super.onStop();
        Log.i("PASOPORACA", "HOMEonCreateView");
    }

    //endregion

    //region "-- PRIVATE METHODS --"

    private void requestInicial() {

        ArrayList<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("Hogar", "http://www.grupobolanos.com/images/fotos/cosas-del-hogar-las-palmas-03.jpg"));
        categorias.add(new Categoria("Electro", "http://www.mcintoshlabs.com/us/CollectionImage/reference_home_theater_avgalleria_xl.jpg"));
        categorias.add(new Categoria("Pescaderia", "http://www.elarco.es/upload/sample_08.jpg"));
        categorias.add(new Categoria("Mascotas", "https://ugc.kn3.net/i/origin/http://gpi-blog.s3.amazonaws.com/wp-content/uploads/2014/10/mascotas.jpg"));
        categorias.add(new Categoria("Vinos", "http://www.hipoglucidos.com/alvaro/bootstrap/bodega/bodega-de-los-abetos.jpg"));
        categorias.add(new Categoria("Congelados", "http://www.labrandero.es/wp-content/uploads/2013/09/congelados-2.png"));

        adapter.addAll(categorias);
    }

    //endregion
}
