package com.example.presta.publicidadexample.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.common.entities.Publicidad;
import com.example.presta.publicidadexample.common.helpers.UnitsHelper;
import com.example.presta.publicidadexample.rest.get.ApiGetAdapter;
import com.example.presta.publicidadexample.rest.get.jsonModel.PublicidadResponse;
import com.squareup.picasso.Picasso;

import java.util.Random;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class PromocionDetalleActivity extends AppCompatActivity {

    TextView publicidadTitulo;
    TextView publicidadDescripcion;
    ImageView publicidadImagen;
    TextView publicidadVigencia;
    TextView publicidadCondiciones;

    String imgURL;
    Integer id;
    String urlCompartir;
    Float imgProp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocion_detalle);

        setToolbar();// Añadir action bar

        publicidadTitulo = (TextView) this.findViewById(R.id.txt_titulo);
        publicidadDescripcion = (TextView) this.findViewById(R.id.txt_descripcion);
        publicidadImagen = (ImageView) this.findViewById(R.id.img_publicidad);
        publicidadVigencia = (TextView) this.findViewById(R.id.txt_valides);
        publicidadCondiciones = (TextView) this.findViewById(R.id.txt_condiciones);


        Bundle b = getIntent().getExtras();
        imgURL = (String) b.getCharSequence("imgURL");
        id = b.getInt("id");
        imgProp = b.getFloat("imgProp");

        setImg(imgURL, imgProp);

        requestPublicidades();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detalle, menu);
        return true;
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
            case R.id.action_share:
                compartir();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void compartir() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_subject));
        sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_msj) + urlCompartir);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.share_title)));
    }


    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setTitulo(String titulo) {
        publicidadTitulo.setText(titulo);
    }

    public void setVigencia(String vigencia) {
        publicidadVigencia.setText("Valido hasta el " + vigencia);
    }

    public void setCondiciones(String condiciones) {
        publicidadCondiciones.setText(condiciones);
    }

    public void setDescripcion(String descripcion) {
        publicidadDescripcion.setText(descripcion);
    }

    public void setImg(String url, Float proporcion) {
        // Esto es para fijar el alto de la imagen al alto que va a tener cuando se descargue la img.
        Integer ancho = Math.round((UnitsHelper.getScreenWidthDp(this)));
        Integer alto = Math.round(proporcion * ancho);

        // Generación aleatoria del color de fondo del placeholder
        Random rnd = new Random();
        int color = Color.argb(100, rnd.nextInt(150), rnd.nextInt(150), rnd.nextInt(256));
        publicidadImagen.setBackgroundColor(color);

        publicidadImagen.setMinimumHeight(alto);

        imgURL = url;
        if (url != null) {
            Picasso.with(this)
                    .load(url)
                    .resize(ancho, alto)
                    .onlyScaleDown()
                    .into(publicidadImagen);
        } else {
            Picasso.with(this)
                    .load(R.drawable.img_placeholder)
                    .into(publicidadImagen);
        }
    }

    private void requestPublicidades() {
        ApiGetAdapter.getById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PublicidadResponse>() {
                    @Override
                    public void onNext(PublicidadResponse publicidadResponse) {
                        cargarPublicidad(publicidadResponse.getPublicidad());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    private void cargarPublicidad(Publicidad publicidad) {
        setVigencia(publicidad.getVigenciaHasta());
        setCondiciones(publicidad.getCondiciones());
        setTitulo(publicidad.getTitulo());
        setDescripcion(publicidad.getDescripcion());
        urlCompartir = publicidad.getUrlCompartir();
    }

}
