package com.example.presta.publicidadexample.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.HomeItem;
import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.example.presta.publicidadexample.common.enums.ClaseHomeItemEnum;
import com.example.presta.publicidadexample.rest.get.ApiGetAdapter;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadResponse;
import com.example.presta.publicidadexample.ui.recyclers.adapters.HomeRecyclerAdapter;
import com.example.presta.publicidadexample.ui.recyclers.adapters.PublicidadRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Presta on 19/04/2016.
 */
public class CategoriaActivity extends AppCompatActivity {

    ImageView categoriaImagen;
    TextView titulo;
    String categoria;
    String urlImg;
    private RecyclerView mRecycler;
    private HomeRecyclerAdapter adapter;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        categoriaImagen = (ImageView) findViewById(R.id.img_categ);
        titulo = (TextView) findViewById(R.id.txt_categ_titulo);

        setToolbar();// Añadir action bar

        Bundle b = getIntent().getExtras();
        categoria = (String) b.getCharSequence("categoria");
        titulo.setText(categoria);
        urlImg = (String) b.getCharSequence("urlImg");

        adapter = new HomeRecyclerAdapter(this);

        mRecycler = (RecyclerView) findViewById(R.id.recycler_categoria_ofertas);

        final LinearLayoutManager manager = new LinearLayoutManager(this);

        // Hace el setup del RecyclerView
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(adapter);

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestInicial();
        Log.i("Categoria", categoria);
        getSupportActionBar().setTitle(categoria);
        setImg(urlImg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            switch (item.getItemId()) {
                // Respond to the action bar's Up/Home button
                case android.R.id.home:
                    supportFinishAfterTransition();
                    return true;
            }

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //region "-- PRIVATE METHODS --"

    private void setToolbar() {
        // Añadir la Toolbar
        toolbar = (Toolbar) findViewById(R.id.categoria_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void requestInicial() {

        // Cargar datos dummy, en la posta hay que hacer el request.

        ArrayList<HomeItem> items;
        items = new ArrayList<>();
/*
        ArrayList<Promocion> promociones = new ArrayList<>();

        promociones.add(new Promocion(1, "promocion 1", "desde", "url", "xxyyuu", "https://2.bp.blogspot.com/-PvB_s3-uZWU/VuMBs-4p0pI/AAAAAAAAAy0/2Ce5OAvQucU6GCCX0fwunhMATFBDf4BQg/s1600/Coto%2B18%2Bcuotas.png", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 2", "desde", "url", "xxyyuu", "https://4.bp.blogspot.com/-r4TlKFpOQTc/VuMCbNfkvHI/AAAAAAAAAy4/i42Mb2AXIm0bF2cj5PTtDw6a9ENAOxAOw/s1600/Coto%2B12cuotas.png", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://www.eldiario24.com/d24ar/fotos/uploads/editorial/2013/02/08/imagenes/41597_Coto-Promocion-.jpg", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://fandeldescuento.com.ar/wp-content/uploads/2015/12/Cxampphtdocsfddwp-contentuploads20151242064.jpg?491409", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://www.promosargentina.com/wp-content/uploads/2013/09/LUNES4.jpg", "blabla", "desde", "titulo", (float)1));
        promociones.add(new Promocion(1, "promocion 3", "desde", "url", "xxyyuu", "http://fandeldescuento.com.ar/wp-content/uploads/2016/03/Cxampphtdocsfddwp-contentuploads20160345426-215x300.jpg?e30b2f", "blabla", "desde", "titulo", (float)1));

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
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 1", "description 1", "desde", "hasta", "blabla", "http://s23.postimg.org/6hlf0ib2z/image.jpg", "aa", (float) 375 / (float) 502)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/yvqukdymz/image.jpg", "aa", (float) 342 / (float) 320)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/4i4t93grf/fsa.jpg", "aa", (float) 428 / (float) 400)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/nypivmbvf/gfd.jpg", "aa", (float) 351 / (float) 328)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/qerc9gty3/gfdf.jpg", "aa", (float) 414 / (float) 290)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/rfw6cc0bf/image.jpg", "aa", (float) 396 / (float) 412)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/djny0g42j/hgf.jpg", "aa", (float) 278 / (float) 328)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/rxmqeg0ij/image.jpg", "aa", (float) 261 / (float) 407)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/aoautl02j/image.jpg", "aa", (float) 313 / (float) 411)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/cinpbbn2z/kbn.jpg", "aa", (float) 428 / (float) 388)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/rtxibxkez/sda.jpg", "aa", (float) 307 / (float) 371)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/lf32t032z/sdf.jpg", "aa", (float) 228 / (float) 355)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/d0iur6cnv/sdfs.jpg", "aa", (float) 312 / (float) 260)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/5095gfl3v/Sin_t_tulo_2.jpg", "aa", (float) 305 / (float) 389)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/tzrt6fnvf/Sin_t_tulo_1.jpg", "aa", (float) 428 / (float) 380)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/u2bot9riz/Sin_t_tulo_3.jpg", "aa", (float) 171 / (float) 334)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/pugwkiq3f/Sin_t_tulo_4.jpg", "aa", (float) 337 / (float) 436)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/hy10q140r/Sin_t_tulo_6.jpg", "aa", (float) 792 / (float) 348)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/3qbc1drbv/Sin_t_tulo_7.jpg", "aa", (float) 622 / (float) 312)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/3mhil4luj/Sin_t_tulo_8.jpg", "aa", (float) 334 / (float) 408)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/z3no8dod7/Sin_t_tulo_9.jpg", "aa", (float) 308 / (float) 366)));
        items.add(new HomeItem(ClaseHomeItemEnum.PUBLICIDAD.ordinal(), new Publicidad(1, "publicidad 2", "description 2", "desde", "hasta", "blabla", "http://s23.postimg.org/jyrkav063/image.jpg", "aa", (float) 217 / (float) 244)));

        adapter.addAll(items);

    }

    //endregion


    public void setImg(String url) {
        urlImg = url;
        if (url != null) {
            Picasso.with(this)
                    .load(url)
                    .into(categoriaImagen);
        } else {
            Picasso.with(this)
                    .load(R.drawable.img_placeholder)
                    .into(categoriaImagen);
        }
    }


}
