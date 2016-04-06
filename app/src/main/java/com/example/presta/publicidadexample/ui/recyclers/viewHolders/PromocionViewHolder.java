package com.example.presta.publicidadexample.ui.recyclers.viewHolders;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.publicidadexample.R;
import com.example.presta.publicidadexample.ui.activities.PromocionDetalleActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Presta on 05/04/2016.
 */
public class PromocionViewHolder extends RecyclerView.ViewHolder {

    final Context context;
    TextView publicidadTitulo;
    TextView publicidadDescripcion;
    ImageView publicidadImagen;
    String imgURL;
    Integer id;

    public PromocionViewHolder(final View itemView, final Context context) {
        super(itemView);

        this.context = context;
        publicidadTitulo = (TextView) itemView.findViewById(R.id.txt_titulo);
        publicidadDescripcion = (TextView) itemView.findViewById(R.id.txt_descripcion);
        publicidadImagen = (ImageView) itemView.findViewById(R.id.img_publicidad);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(itemView.getContext(), PromocionDetalleActivity.class);

                Bundle param = new Bundle();
                param.putCharSequence("titulo", publicidadTitulo.getText());
                param.putCharSequence("descripcion", publicidadDescripcion.getText());
                param.putCharSequence("imgURL", imgURL);
                param.putInt("id", id);

                intent.putExtras(param);

                // Si la SDK >= 21 puedo usar animacion para la trancisión entre las vistas.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, publicidadImagen, "shareImg");
                    itemView.getContext().startActivity(intent, options.toBundle());
                } else {
                    itemView.getContext().startActivity(intent);
                }

            }
        });

    }

    public void setTitulo(String titulo) {
        publicidadTitulo.setText(titulo);
    }

    public void setId(Integer idParam) {
        id = idParam;
    }

    public void setDescripcion(String descripcion) {
        publicidadDescripcion.setText(descripcion);
    }

    public void setImg(String url) {
        imgURL = url;
        if (url != null) {
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.img_placeholder)
                    .into(publicidadImagen);
        } else {
            Picasso.with(context)
                    .load(R.drawable.img_placeholder)
                    .into(publicidadImagen);
        }
    }

}