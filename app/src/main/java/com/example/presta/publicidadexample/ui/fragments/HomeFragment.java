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
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.HomeItem;
import com.example.presta.publicidadexample.common.entities.Producto;
import com.example.presta.publicidadexample.common.entities.Promocion;
import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.example.presta.publicidadexample.common.enums.ClaseHomeItemEnum;
import com.example.presta.publicidadexample.ui.recyclers.adapters.HomeRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Presta on 05/04/2016.
 */
public class HomeFragment extends Fragment {

    //region "-- ATRIBUTOS --"

    private RecyclerView mRecycler;
    private HomeRecyclerAdapter adapter;
    private boolean requestInicialRealizado = false;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

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
    }

    //endregion

    //region "-- PRIVATE METHODS --"

    private void requestInicial() {

        // Cargar datos dummy, en la posta hay que hacer el request.

        ArrayList<HomeItem> items;
        items = new ArrayList<>();

        ArrayList<Promocion> promociones = new ArrayList<>();

        promociones.add(new Promocion(1, "promocion 1", "desde", "url", "xxyyuu", "http://www.krea-publicidad.com/krea-publicidad/img/promocion-tarjetas-de-presentacion-facebook-krea-publicidad-en-ecuador-web-en-ecuador.png", "blabla", "desde", "titulo"));
        promociones.add(new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://freddo.com.ar/wp-content/uploads/2015/02/santander.png", "blabla", "desde", "titulo"));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://es.club-onlyou.com/var/klepierre_es/storage/images/meridiano/ofertas/promocion-tarjeta-movieyelmo/7936297-1-esl-ES/Promocion-tarjeta-Movieyelmo_781_331.jpg", "blabla", "desde", "titulo"));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://cazaofertas.com.ar/wp-content/uploads/2015/06/Cinemacenter-tarjeta-nevada-2x1.jpg", "blabla", "desde", "titulo"));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://cazaofertas.com.ar/wp-content/uploads/2015/09/Cinema-adrogue-lunes-martes-precio-miercoles-sube.jpg", "blabla", "desde", "titulo"));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "https://www.superchangomas.com.ar/wp-content/uploads/2015/11/Banner-Jueves_CH-01.jpg", "blabla", "desde", "titulo"));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCIONES.ordinal(), promociones));


        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 1", "desde", "url", "xxyyuu", "https://s-media-cache-ak0.pinimg.com/736x/53/83/b3/5383b3a88af02bfc066bb6ef46c70fd8.jpg", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        // Integer id, String imagen, String descripcion, String nombre, String urlCompartir, Double precio
      /*  items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
        items.add(new HomeItem(ClaseHomeItemEnum.PRODUCTO.ordinal(), new Producto(1, "http://peruf5.com/zapatos/documentos/productos/zapatilla-de-lona-tigre-urbanas-originales.jpg", "blabla", "zapatilla", "utl", 20.5)));
*/
       /* items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));


        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));


        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));


        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));


        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://cdn5.publicidadenlanube.es/wp-content/uploads/2015/02/Maestrosdelapublicidad.jpg", "aa")));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://comunikndo.com/wp-content/uploads/2013/09/publicidad-en-la-nube.jpg", "aa")));
*/

        adapter.addAll(items);

    }

    //endregion
}
