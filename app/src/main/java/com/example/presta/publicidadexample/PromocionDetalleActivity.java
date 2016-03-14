package com.example.presta.publicidadexample;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PromocionDetalleActivity extends AppCompatActivity {

    TextView publicidadTitulo;
    TextView publicidadDescripcion;
    ImageView publicidadImagen;

    String titulo;
    String descripcion;
    String imgURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocion_detalle);


        publicidadTitulo = (TextView) this.findViewById(R.id.txt_titulo);
        publicidadDescripcion = (TextView) this.findViewById(R.id.txt_descripcion);
        publicidadImagen = (ImageView) this.findViewById(R.id.img_publicidad);


        Bundle b = getIntent().getExtras();
        titulo = (String) b.getCharSequence("titulo");
        descripcion = (String) b.getCharSequence("descripcion");
        imgURL = (String) b.getCharSequence("imgURL");
        int position = b.getInt("position");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            publicidadImagen.setTransitionName("shareImg" + position);

        setTitulo(titulo);
        setDescripcion(descripcion);
        setImg(imgURL);
    }


    public void setTitulo(String titulo) {
        publicidadTitulo.setText(titulo);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
