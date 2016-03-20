package com.example.presta.publicidadexample.ui.activities;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.rest.model.Publicidad;
import com.example.presta.publicidadexample.rest.PublicidadesAdapter;
import com.example.presta.publicidadexample.rest.model.PublicidadResponse;
import com.example.presta.publicidadexample.ui.adapter.PublicidadAdapter;
import com.squareup.picasso.Picasso;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class PromocionDetalleActivity extends AppCompatActivity {

    TextView publicidadTitulo;
    TextView publicidadDescripcion;
    ImageView publicidadImagen;
    TextView publicidadVigencia;
    TextView publicidadCondiciones;

    String titulo;
    String descripcion;
    String imgURL;
    Integer id;

    private PublicidadAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocion_detalle);


        publicidadTitulo = (TextView) this.findViewById(R.id.txt_titulo);
        publicidadDescripcion = (TextView) this.findViewById(R.id.txt_descripcion);
        publicidadImagen = (ImageView) this.findViewById(R.id.img_publicidad);
        publicidadVigencia = (TextView) this.findViewById(R.id.txt_valides);
        publicidadCondiciones = (TextView) this.findViewById(R.id.txt_condiciones);


        Bundle b = getIntent().getExtras();
        titulo = (String) b.getCharSequence("titulo");
        descripcion = (String) b.getCharSequence("descripcion");
        imgURL = (String) b.getCharSequence("imgURL");
        id = b.getInt("id");


        setTitulo(titulo);
        setDescripcion(descripcion);
        setImg(imgURL);

        adapter = new PublicidadAdapter(this);

        requestPublicidades();
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

    public void setImg(String url) {
        imgURL = url;
        if (url != null) {
            Picasso.with(this)
                    .load(url)
                    .placeholder(R.drawable.img_placeholder)
                    .into(publicidadImagen);
        } else {
            Picasso.with(this)
                    .load(R.drawable.img_placeholder)
                    .into(publicidadImagen);
        }
    }

    private void requestPublicidades() {
        PublicidadesAdapter.getById(id)
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

    private String getUuid(){
        return ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
    }

    private void cargarPublicidad(Publicidad publicidad) {
        setVigencia(publicidad.getVigenciaHasta());
        setCondiciones(publicidad.getCondiciones());
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
        return super.onOptionsItemSelected(item);
    }

}
