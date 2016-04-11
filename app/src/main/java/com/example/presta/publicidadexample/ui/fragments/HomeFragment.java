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

        promociones.add(new Promocion(1, "promocion 1", "desde", "url", "xxyyuu", "http://www.krea-publicidad.com/krea-publicidad/img/promocion-tarjetas-de-presentacion-facebook-krea-publicidad-en-ecuador-web-en-ecuador.png", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://freddo.com.ar/wp-content/uploads/2015/02/santander.png", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://es.club-onlyou.com/var/klepierre_es/storage/images/meridiano/ofertas/promocion-tarjeta-movieyelmo/7936297-1-esl-ES/Promocion-tarjeta-Movieyelmo_781_331.jpg", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://cazaofertas.com.ar/wp-content/uploads/2015/06/Cinemacenter-tarjeta-nevada-2x1.jpg", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://cazaofertas.com.ar/wp-content/uploads/2015/09/Cinema-adrogue-lunes-martes-precio-miercoles-sube.jpg", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "https://www.superchangomas.com.ar/wp-content/uploads/2015/11/Banner-Jueves_CH-01.jpg", "blabla", "desde", "titulo", (float)1));

        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCIONES.ordinal(), promociones));


        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://s23.postimg.org/6hlf0ib2z/image.jpg", "aa", (float)375 / (float)502)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/yvqukdymz/image.jpg", "aa", (float)342 / (float)320 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/4i4t93grf/fsa.jpg", "aa", (float)428 / (float)400 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/nypivmbvf/gfd.jpg", "aa", (float)351 / (float)328 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/qerc9gty3/gfdf.jpg", "aa", (float)414 / (float)290 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/rfw6cc0bf/image.jpg", "aa", (float)396 / (float)412 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/djny0g42j/hgf.jpg", "aa", (float)278 / (float)328 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/rxmqeg0ij/image.jpg", "aa", (float)261 / (float)407 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/aoautl02j/image.jpg", "aa", (float)313 / (float)411 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/cinpbbn2z/kbn.jpg", "aa", (float)428 / (float)388 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/rtxibxkez/sda.jpg", "aa", (float)307 / (float)371 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/lf32t032z/sdf.jpg", "aa", (float)228 / (float)355 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/d0iur6cnv/sdfs.jpg", "aa", (float)312 / (float)260 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/5095gfl3v/Sin_t_tulo_2.jpg", "aa", (float)305 / (float)389 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/tzrt6fnvf/Sin_t_tulo_1.jpg", "aa", (float)428 / (float)380 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/u2bot9riz/Sin_t_tulo_3.jpg", "aa", (float)171 / (float)334 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/pugwkiq3f/Sin_t_tulo_4.jpg", "aa", (float)337 / (float)436 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/hy10q140r/Sin_t_tulo_6.jpg", "aa", (float)792 / (float)348 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/3qbc1drbv/Sin_t_tulo_7.jpg", "aa", (float)622 / (float)312 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/3mhil4luj/Sin_t_tulo_8.jpg", "aa", (float)334 / (float)408 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/z3no8dod7/Sin_t_tulo_9.jpg", "aa", (float)308 / (float)366 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/jyrkav063/image.jpg", "aa", (float)217 / (float)244 )));



        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 1", "desde", "url", "xxyyuu", "https://s-media-cache-ak0.pinimg.com/736x/53/83/b3/5383b3a88af02bfc066bb6ef46c70fd8.jpg", "blabla", "desde", "titulo", (float)552/ (float)736 )));
        items.add(new HomeItem(ClaseHomeItemEnum.PROMOCION.ordinal(), new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "http://www.grupoarco.com.mx/wp-content/uploads/promocion.png", "blabla", "desde", "titulo", (float)300 /(float)540 )));

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
