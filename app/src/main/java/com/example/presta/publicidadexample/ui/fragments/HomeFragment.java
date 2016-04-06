package com.example.presta.publicidadexample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.HomeItem;
import com.example.presta.publicidadexample.common.entities.Producto;
import com.example.presta.publicidadexample.common.entities.Promocion;
import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.example.presta.publicidadexample.common.enums.ClaseHomeItemEnum;
import com.example.presta.publicidadexample.ui.recyclers.adapters.HomeRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Presta on 05/04/2016.
 */
public class HomeFragment extends Fragment {

    //region "-- ATRIBUTOS --"

    private RecyclerView mRecycler;
    private HomeRecyclerAdapter adapter;
    private boolean requestInicialRealizado = false;

    //endregion

    //region "-- CONSTRUCTOR --"

    public HomeFragment() {
        // Required empty public constructor
    }

    //endregion

    //region "-- OVERRIDE --"

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeRecyclerAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mRecycler = (RecyclerView) root.findViewById(R.id.recycler_home);

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) != ClaseHomeItemEnum.PRODUCTO.ordinal() ? manager.getSpanCount() : 1;
            }
        });

        // Hace el setup del RecyclerView
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(adapter);

        // Consulta las publicidades a la API
        if (!requestInicialRealizado) {
            requestInicialPublicidades();
            requestInicialRealizado = true;
        }

        return root;
    }

    //endregion

    //region "-- PRIVATE METHODS --"

    private void requestInicialPublicidades() {

        // Cargar datos dummy, en la posta hay que hacer el request.

        ArrayList<HomeItem> items;
        items = new ArrayList<>();

        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla",  "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 1", "desde", "url", "xxyyuu", "http://www.educadictos.com/wp-content/uploads/2014/05/Promocion.gif", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        // Integer id, String imagen, String descripcion, String nombre, String urlCompartir, Double precio
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla",  "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla",  "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));

        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla",  "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));


        adapter.addAll(items);

    }

    //endregion
}
