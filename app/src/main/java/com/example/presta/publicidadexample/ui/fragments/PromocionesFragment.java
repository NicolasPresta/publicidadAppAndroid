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
import com.example.presta.publicidadexample.common.entities.HomeItem;
import com.example.presta.publicidadexample.common.entities.Promocion;
import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.example.presta.publicidadexample.common.enums.ClaseHomeItemEnum;
import com.example.presta.publicidadexample.ui.recyclers.adapters.HomeRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Presta on 19/04/2016.
 */
public class PromocionesFragment extends Fragment {

    //region "-- ATRIBUTOS --"

    private RecyclerView mRecycler;
    private HomeRecyclerAdapter adapter;
    private boolean requestInicialRealizado = false;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    //endregion

    //region "-- CONSTRUCTOR --"

    public PromocionesFragment() {
        // Required empty public constructor
    }

    //endregion

    //region "-- OVERRIDE --"

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeRecyclerAdapter(getActivity());
        //  Log.i("PASOPORACA", "HOMEonCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  Log.i("PASOPORACA", "HOMEonCreateView");
        View root = inflater.inflate(R.layout.fragment_promociones, container, false);

        mRecycler = (RecyclerView) root.findViewById(R.id.recycler_promociones);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        // Hace el setup del RecyclerView
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(adapter);

        mRecycler.addOnScrollListener(new RecyclerView. OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = manager.getChildCount();
                    totalItemCount = manager.getItemCount();
                    pastVisiblesItems = manager.findFirstVisibleItemPosition();

                    if (loading) {

                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            //loading = false;
                            requestInicial();
                        }
                    }
                }
            }
        });

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

        // Cargar datos dummy, en la posta hay que hacer el request.

        ArrayList<HomeItem> items;
        items = new ArrayList<>();
/*
        ArrayList<Promocion> promociones = new ArrayList<>();


        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCIONES.ordinal(), promociones));


        ArrayList<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("Hogar", "http://www.grupobolanos.com/images/fotos/cosas-del-hogar-las-palmas-03.jpg"));
        categorias.add(new Categoria("Electro", "http://www.mcintoshlabs.com/us/CollectionImage/reference_home_theater_avgalleria_xl.jpg"));
        categorias.add(new Categoria("Pescaderia", "http://www.elarco.es/upload/sample_08.jpg"));
        categorias.add(new Categoria("Mascotas", "https://ugc.kn3.net/i/origin/http://gpi-blog.s3.amazonaws.com/wp-content/uploads/2014/10/mascotas.jpg"));
        categorias.add(new Categoria("Vinos", "http://www.hipoglucidos.com/alvaro/bootstrap/bodega/bodega-de-los-abetos.jpg"));
        categorias.add(new Categoria("Congelados", "http://www.labrandero.es/wp-content/uploads/2013/09/congelados-2.png"));
        items.add(new HomeItem(ClaseHomeItemEnum.CATEGORIAS.ordinal(), categorias));
*/

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 1", "desde", "url", "xxyyuu", "https://2.bp.blogspot.com/-PvB_s3-uZWU/VuMBs-4p0pI/AAAAAAAAAy0/2Ce5OAvQucU6GCCX0fwunhMATFBDf4BQg/s1600/Coto%2B18%2Bcuotas.png", "blabla", "desde", "titulo", (float)1)));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "https://4.bp.blogspot.com/-r4TlKFpOQTc/VuMCbNfkvHI/AAAAAAAAAy4/i42Mb2AXIm0bF2cj5PTtDw6a9ENAOxAOw/s1600/Coto%2B12cuotas.png", "blabla", "desde", "titulo", (float)1)));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://www.eldiario24.com/d24ar/fotos/uploads/editorial/2013/02/08/imagenes/41597_Coto-Promocion-.jpg", "blabla", "desde", "titulo", (float)1)));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://fandeldescuento.com.ar/wp-content/uploads/2015/12/Cxampphtdocsfddwp-contentuploads20151242064.jpg?491409", "blabla", "desde", "titulo", (float)1)));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://www.promosargentina.com/wp-content/uploads/2013/09/LUNES4.jpg", "blabla", "desde", "titulo", (float)1)));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://fandeldescuento.com.ar/wp-content/uploads/2016/03/Cxampphtdocsfddwp-contentuploads20160345426-215x300.jpg?e30b2f", "blabla", "desde", "titulo", (float)1)));

        adapter.addAll(items);

    }

    //endregion
}
